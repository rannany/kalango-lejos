package ultimate;

import comportamentos.ComportamentoNav;
import comportamentos.ComportamentoTrans;
import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.nxt.TachoMotorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.nxt.remote.RemoteNXT;
import lejos.robotics.Touch;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import tarefas.TarefaNavegar;
import tarefas.TarefaTransporte;
import  lejos.nxt.SensorConstants;

public class Start {

	public static void main(String[] args) {
		
		Conector conector = new  Conector();

		RemoteNXT nxt = conector.conectar("NXT");

		Navegador nav = new Navegador(
				Motor.A, 
				Motor.C,
				SensorPort.S1, 
				SensorPort.S3, 
				SensorPort.S4);
		
		Garra garra = new Garra(nxt.S2, nxt.S3, nxt.B);

		Transportador transp = new Transportador(
				new UltrasonicSensor(SensorPort.S2),
				garra,
				nav.piloto, 
				new UltrasonicSensor(nxt.S1), 
				new UltrasonicSensor(nxt.S4)
				);
		
		TouchSensor ts = new TouchSensor(nxt.S3);
		
		
		//Cacamba cacamba = new Cacamba(nxt.A, nxt.C);
		
		//cacamba.desembarque();
		garra.fecha(30);
		Thread  tarefaTransp = new Thread(new TarefaTransporte(transp));
		tarefaTransp.start();
		
		//garra.pega();
		
		/*
		 * 
		
		Behavior compNav = new ComportamentoNav(nav);
		
		Behavior compTran = new ComportamentoTrans(transp, garra);
		
		Behavior[] comportamentos = {compTran, compNav};
		
		Arbitrator abr = new Arbitrator(comportamentos);
		
		abr.start();
		
		 TarefaNavegar tarefaNavegar = new TarefaNavegar(nav, "Navegação");
		try {
			tarefaNavegar.t.join();
			tarefaTransp.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 */

	}
}
