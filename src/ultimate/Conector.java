package ultimate;

import java.io.IOException;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Sound;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTCommConnector;
import lejos.nxt.remote.RemoteNXT;

public class Conector {
	
	public Conector() {}
	
	public RemoteNXT conectar(String nome) {
		try {
			LCD.drawString("conectando ...", 0, 0);
			NXTCommConnector conector = Bluetooth.getConnector();
			RemoteNXT remoteNXT = new RemoteNXT(nome, conector); 
			LCD.clear();
			LCD.drawString("Conectado", 0, 0);
			Sound.twoBeeps();
			return remoteNXT;
		}catch(IOException ioe) {
			LCD.clear();
			LCD.drawString("Conn Failed", 0, 0);
			Button.waitForAnyPress();
			System.exit(1);
		}
		return null;
	}

}
