package ultimate;

import lejos.nxt.ADSensorPort;
import lejos.nxt.NXTMotor;
import lejos.nxt.TachoMotorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.remote.RemoteMotor;

public class Garra {
	public TouchSensor tGarra, tCacamba;
	private RemoteMotor motorGarra;
	
	public Garra(ADSensorPort portaFrontal, ADSensorPort portaCima, RemoteMotor portaMotor) {
		tGarra = new TouchSensor(portaFrontal);
		tCacamba = new TouchSensor(portaCima);
		motorGarra = portaMotor;	
	}
	public void abre(int pwr) {
		while(!tGarra.isPressed()) {
			motorGarra.setPower(pwr);
			motorGarra.backward();
		}
		motorGarra.stop();
	}
	public void fecha(int pwr) {
		while(!tCacamba.isPressed()) {
			motorGarra.setPower(pwr);
			motorGarra.forward();
		}
		motorGarra.stop();
		
	}
	public void pega () {
		abre(20);
		fecha(30);
	}
}
