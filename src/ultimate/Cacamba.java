package ultimate;

import lejos.nxt.Motor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;

public class Cacamba {

	private DifferentialPilot cacamba;
	public static int passageiros;
	
	public Cacamba(RegulatedMotor portaEsquerda, RegulatedMotor portaDireita) {
		cacamba = new DifferentialPilot(2.8f, 25.0f, portaEsquerda, portaDireita, true);
	}
	
	public void desembarque() {
		cacamba.setTravelSpeed(2);
		cacamba.travel(2);
		Delay.msDelay(500);
		cacamba.travel(-2);
	}
	
}
