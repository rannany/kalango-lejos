package tarefas;

import ultimate.Navegador;

public class TarefaNavegar implements Runnable{

	private Navegador nav;
	private String nome;
	public Thread t;

	public TarefaNavegar(Navegador nav, String nome) {
		this.nav = nav;
		this.nome = nome;
		t = new Thread(this, nome);
		t.start();
	}
	@Override
	public void run() {
		nav.explorar();	
	}
}
