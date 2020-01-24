package blockingQueue;

import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Trabalhador implements Runnable {
	private BlockingQueue<Tarefa> listaDeTarefas= new LinkedBlockingQueue<Tarefa>();
	private LinkedList<Trabalhador> listaDeTrabalhador= new LinkedList<Trabalhador>();
	private String nomeDoTrabalhador;
	private int angulo;

	public Trabalhador( String nome,int angulo) {
		
		this.nomeDoTrabalhador = nome;
		this.angulo= angulo;
		
	}
	

	public BlockingQueue<Tarefa> getListaDeTarefas() {
		return listaDeTarefas;
	}


	public void setListaDeTarefas(BlockingQueue<Tarefa> listaDeTarefas) {
		this.listaDeTarefas = listaDeTarefas;
	}


	public LinkedList<Trabalhador> getListaDeTrabalhador() {
		return listaDeTrabalhador;
	}


	public void setListaDeTrabalhador(LinkedList<Trabalhador> listaDeTrabalhador) {
		this.listaDeTrabalhador = listaDeTrabalhador;
	}


	public String getNomeDoTrabalhador() {
		return nomeDoTrabalhador;
	}


	public void setNomeDoTrabalhador(String nomeDoTrabalhador) {
		this.nomeDoTrabalhador = nomeDoTrabalhador;
	}


	public int getAngulo() {
		return angulo;
	}


	public void setAngulo(int angulo) {
		this.angulo = angulo;
	}


	

	

	@Override
	public void run() {
		new Trabalhador(nomeDoTrabalhador, angulo);
	}
}