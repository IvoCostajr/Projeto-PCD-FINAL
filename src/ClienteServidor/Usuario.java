package ClienteServidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Usuario extends Thread {
	private static final int PORTA = 8080;
	private Socket conexao;
	private static String meuNome;

	public Usuario(Socket socket) {
		this.conexao = socket;
	}

	public static void main(String args[]) {
		try {
			Socket socket = new Socket("127.0.0.1", PORTA);
			PrintStream saida = new PrintStream(socket.getOutputStream());
			BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

			System.out.print("Digite seu nome: ");
			meuNome = teclado.readLine();
			saida.println(meuNome.toUpperCase());

			Thread thread = new Usuario(socket);
			thread.start();
			String msg;

			while (true) {
				System.out.print("Enviar: ");
				msg = teclado.readLine();
				saida.println(msg);
			}
		} catch (IOException e) {
			System.out.println("Falha na Conexao... .. ." + " IOException: " + e);
		}
	}

	public void run() {
		try {
			BufferedReader entrada = new BufferedReader(new InputStreamReader(this.conexao.getInputStream()));
			String msg;
			while (true) {
				msg = entrada.readLine();

				if (msg == null) {
					System.out.println("Conexão encerrada!");
					System.exit(0);
				}

				System.out.println();
				System.out.println(msg);
				System.out.print("Enviar: ");
			}
		} catch (IOException e) {
			System.out.println("Ocorreu uma Falha... .. ." + " IOException: " + e);
		}
	}
}