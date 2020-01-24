package blockingQueue;

import java.io.File;

public class Tarefa {
	private String localDePesquisa;
	private String localLogo;
	private String caminhoSalvar;
	private int angulo;


	

	public Tarefa(String localDePesquisa, String localLogo, String caminhoSalvar, int angulo) {
		this.localDePesquisa=localDePesquisa;
		this.localLogo = localLogo;
		this.caminhoSalvar= caminhoSalvar;
		this.angulo= angulo;
	}

	public String getLocalDePesquisa() {
		return localDePesquisa;
	}

	public void setLocalDePesquisa(String localDePesquisa) {
		this.localDePesquisa = localDePesquisa;
	}

	public String getLocalLogo() {
		return localLogo;
	}

	public void setLocalLogo(String localLogo) {
		this.localLogo = localLogo;
	}

	public String getCaminhoDownload() {
		return caminhoSalvar;
	}

	public void setCaminhoDownload(String caminhoSalvar) {
		this.caminhoSalvar= caminhoSalvar;
	}

	public int getAngulo() {
		return angulo;
	}

	public void setAngulo(int angulo) {
		this.angulo = angulo;
	}

	
	
}
