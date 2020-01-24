package ClienteServidor;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import javax.imageio.ImageIO;

import blockingQueue.Cliente;
import blockingQueue.Tarefa;
import blockingQueue.TesteFindImage;
import blockingQueue.Trabalhador;

public class Servidor extends Thread {
	private static final int PORTA = 8080;
	private static final int MAX_SIZE = 1;

	private static Vector<PrintStream> usuarios;
	private static List<String> listaDeNomeDosUsuarios = new ArrayList<String>();
	private static ServerSocket server;
	private Socket conexao;
	private String nomeUsuario;
	private String localDePesquisa;
	private String localLogo;

	private static BlockingQueue<Tarefa> listaDeTarefa = new LinkedBlockingQueue<Tarefa>();
	private LinkedList<Trabalhador> listaDeTrabalhador= new LinkedList<Trabalhador>();
	private static ExecutorService produtor = Executors.newFixedThreadPool(MAX_SIZE);
	private static ExecutorService consumidor = Executors.newFixedThreadPool(MAX_SIZE);
	private int angulo = 0;

	public Servidor(Socket socket) {
		this.conexao = socket;
	}

	public boolean adicionarNomeUsuario(String novoNome) {
		System.out.println(listaDeNomeDosUsuarios);
		for (int i = 0; i < listaDeNomeDosUsuarios.size(); i++) {
			if (listaDeNomeDosUsuarios.get(i).equals(novoNome))
				return true;
		}
		listaDeNomeDosUsuarios.add(novoNome);
		return false;
	}

	public void removeNomeUsuario(String oldName) {
		for (int i = 0; i < listaDeNomeDosUsuarios.size(); i++) {
			if (listaDeNomeDosUsuarios.get(i).equals(oldName))
				listaDeNomeDosUsuarios.remove(oldName);
		}
	}

	public void enviarParaUsuarios(PrintStream saida, String acao, String msg) throws IOException {
		Enumeration<PrintStream> e = usuarios.elements();
		while (e.hasMoreElements()) {
			PrintStream chat = (PrintStream) e.nextElement();
			if (chat != saida) {
				chat.println("Nome do usuario: " + this.nomeUsuario + ", Acao: " + acao + ", Conteudo: " + msg);
			}

		}
	}

	public void correcaoDeNome(String nomeUsuario, PrintStream saida) throws IOException {
		if (adicionarNomeUsuario(this.nomeUsuario)) {
			saida.println("Este nome ja existe! Conecte novamente com outro Nome.");
			usuarios.add(saida);
			this.conexao.close();

		} else if (this.nomeUsuario == null) {
			saida.println("Nome de usuario nao pode ser nulo! Conecte Novamente com outro Nome.");
			usuarios.add(saida);
			this.conexao.close();

		} else {
			System.out.println(this.nomeUsuario + " : Conectado ao Servidor!");
		}
	}

	/*public synchronized Tarefa criarTarefa(BufferedReader entrada, PrintStream saida) throws IOException {
		saida.println("local de pesquisa?");

		String localDePesquisa = entrada.readLine();

		saida.println("local do logo?");
		String localLogo = " ";

		saida.println("local do download?");
		String caminhoDownload = " ";

		saida.println("qual o angulo?");
		String angulo = " ";

		saida.println("qual forma de pesquisa?");
		String formaDePesquisa = " ";

		return new Tarefa(localDePesquisa, localLogo, caminhoDownload, angulo, formaDePesquisa);
	}*/

	public synchronized void criarCliente() throws IOException {
		System.out.print("O usuario: " + nomeUsuario + " Iniciou a criacao de um cliente!\n");
		 Cliente cliente = new Cliente();
		 
		 cliente.setVisible(true);
		Tarefa addList = cliente.getTarefa();
		System.out.println("aqui" + cliente.getLocalDePesquisa());
		try {
			listaDeTarefa.put(addList);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(listaDeTarefa);
		System.out.println("\n");
	}
	public void addListImage(String localDePesquisa,String localLogo,String caminhoSalvar,int angulo) throws IOException {
		String img = "";
		File f = new File(localDePesquisa);
		File l= new File(localLogo);
		for (File file : f.listFiles()) {
			img = f + "/" + file.getName();
			System.out.println(img);
			System.out.println(l.toString());
			BufferedImage find = ImageIO.read(new File(l.toString() + "/Superman.png"));
			BufferedImage image = ImageIO.read(new File(img));
			String temp = caminhoSalvar;
			caminhoSalvar += file.getName();
			if(!file.getName().equals(".DS_Store")) {
				TesteFindImage.Vixi(find, image, caminhoSalvar,angulo);
				caminhoSalvar = temp;
			
			}else {
				System.out.println(file.toString());
			}
				
			
		}

		//for (File a : f.listFiles()) {
		//	dlm.addElement(a.getName());
		//}

		//listaDeImagem.setModel(dlm);
		//listaDePesquisa.setModel(dlm);

	}

	public synchronized void criarTrabalhador(BlockingQueue<Tarefa> listaDeTarefa,String nomeUsuario,int angulo) throws IOException, InterruptedException {
		

		
		listaDeTrabalhador.add(new Trabalhador(nomeUsuario,angulo));
		System.out.println("Trabalhador criado");
		while(true) {
		System.out.println("consumido : "+listaDeTarefa.take());
		
		}
	}

	public static void main(String args[]) {
		usuarios = new Vector<PrintStream>();
		try {
			server = new ServerSocket(PORTA);
			System.out.println("Servidor rodando na porta: " + PORTA);
			while (true) {
				Socket conexao = server.accept();
				Thread t = new Servidor(conexao);
				t.start();
			}
		} catch (IOException e) {
			System.out.println("IOException: " + e);
		}
	}

	public void run() {
		try {
			BufferedReader entrada = new BufferedReader(new InputStreamReader(this.conexao.getInputStream()));
			PrintStream saida = new PrintStream(this.conexao.getOutputStream());

			this.nomeUsuario = entrada.readLine();
			correcaoDeNome(nomeUsuario, saida);
			usuarios.add(saida);

			String msg = entrada.readLine();

			while (msg != null && !(msg.trim().equals(""))) {
				if (msg.equals("cliente")) {
				 criarCliente();
					
				} else if (msg.equals("trabalhador")) {
					criarTrabalhador(listaDeTarefa,nomeUsuario,angulo);
					
					
				}
				enviarParaUsuarios(saida, " escreveu: ", msg);
				msg = entrada.readLine();
				

				//produtor.shutdown();
				//consumidor.shutdown();
			}

			System.out.println(this.nomeUsuario + " saiu do bate-papo!");
			enviarParaUsuarios(saida, " saiu", " do bate-papo!");

			removeNomeUsuario(this.nomeUsuario);
			usuarios.remove(saida);
			this.conexao.close();

		} catch (IOException e) {
			System.out.println("Falha na Conexao... .. ." + " IOException: " + e);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}