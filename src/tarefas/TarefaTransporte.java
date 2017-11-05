package tarefas;

import ultimate.Transportador;

public class TarefaTransporte implements Runnable {
	
	Transportador transportador;
	
	public TarefaTransporte(Transportador transportador) {
		this.transportador = transportador;
	}
	@Override
	public void run() {
		transportador.radar();
	}	
}
