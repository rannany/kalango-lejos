package comportamentos;

import lejos.nxt.Sound;
import lejos.robotics.subsumption.Behavior;
import lejos.util.Delay;
import ultimate.Garra;
import ultimate.Transportador;

public class ComportamentoTrans implements Behavior {
	
	private boolean suppressed;
	private Transportador transp;
	private Garra garra;
	
	public ComportamentoTrans(Transportador transp, Garra garra) {
		this.transp = transp;
		this.garra = garra;
	}

	@Override
	public boolean takeControl() {
		return garra.tCacamba.isPressed();
	}
	@Override
	public void action() {
		suppressed = false;
		System.out.println("Estrou");
		Sound.beep();
		while( !suppressed )
	        Thread.yield();
		System.out.println("Saiu");
		Delay.msDelay(500);
	}

	@Override
	public void suppress() {
		suppressed = true;	
	}
}
