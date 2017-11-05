package comportamentos;

import lejos.nxt.Sound;
import lejos.robotics.subsumption.Behavior;
import ultimate.Navegador;

public class ComportamentoNav implements Behavior {
	
	private boolean suppressed;
	private Navegador nav;
	
	public ComportamentoNav(Navegador nav) {
		this.nav = nav;
	}

	@Override
	public boolean takeControl() {
		return true;
	}

	@Override
	public void action() {
		suppressed = false;
		nav.piloto.forward();
		while(!suppressed) {
			Thread.yield();
		}
	}

	@Override
	public void suppress() {
		suppressed = true;
		nav.piloto.stop();
		Sound.beepSequence();
	}

}
