package ultimate;

import java.util.Random;

import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.I2CPort;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.Color;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;

public class Navegador {
	public DifferentialPilot piloto;
	//private UltrasonicSensor distanciaDireita, distanciaEsquerda, distanciaCen;
	private ColorSensor sensorCorEsq, sensorCorDir, sensorCorCen;
	//private double catetoA;
	//private Transportador transportador;
	
	public Navegador(
			RegulatedMotor motorDireita, 
			RegulatedMotor motorEsquerda, 
			SensorPort s1,
			SensorPort s3,
			SensorPort s2
			)
	{
		piloto = new DifferentialPilot(2.8f, 16.5f, motorEsquerda , motorDireita, true);
		sensorCorEsq = new ColorSensor(s1);
		sensorCorDir = new ColorSensor(s3);
		sensorCorCen = new ColorSensor(s2);
		//transportador = new Transportador(distanciaCen, piloto, distanciaDireita, distanciaEsquerda);
	}
	
	public boolean alinhar() {
		int corDireita, corEsquerda, corCentro;
		corDireita = sensorCorDir.getColorID();
		corEsquerda = sensorCorEsq.getColorID();
		corCentro = sensorCorCen.getColorID();
		piloto.setTravelSpeed(5);
		if((corCentro == corDireita)) {
			return true;
		}
		else if((corDireita != corEsquerda) && (corEsquerda != corCentro)) {
			piloto.arc(0, -5);
		} 
		else if((corDireita != corEsquerda) && (corDireita != corCentro)) {
			piloto.arc(0, 5);
		}
		return false;
	}
		
	private boolean validarCor(int corA, int corB, int corC) {
		if((corA == corB) && (corA == corC) && (corC == corB)) {
			return true;
		}
		return false;
	}
	
	private void mudarDirecao(int corEsquerda, int corDireita, int corCentro) {
		Random rand = new Random();
		int randGrau;
		corDireita = sensorCorDir.getColorID();
		corEsquerda = sensorCorEsq.getColorID();
		if(corDireita != Color.WHITE && corDireita != Color.WHITE) {
			piloto.travel(20);
			corDireita = sensorCorDir.getColorID();
			corEsquerda = sensorCorEsq.getColorID();
			corCentro = sensorCorCen.getColorID();
			while(!validarCor(corDireita, corEsquerda, corCentro)) {
				randGrau = rand.nextInt(90 - 20  + 1) + 20;
				piloto.arc(0, randGrau);
				corDireita = sensorCorDir.getColorID();
				corEsquerda = sensorCorEsq.getColorID();
				corCentro = sensorCorCen.getColorID();
				piloto.arc(0, -1 * randGrau);
			}
			if(corCentro == Color.BLACK) {
				piloto.arc(0, -180);
			}
			else {
				piloto.arc(0, 90);
			}
			do{
				piloto.forward();	
				corCentro = sensorCorCen.getColorID();
			}while(corCentro != Color.WHITE);
		}
	}
	public synchronized void explorar() {
		int corEsquerda = -1;
		int corDireita = -1;
		int corCentro = -1;
		piloto.setTravelSpeed(5);
		piloto.forward();
		alinhar();
		mudarDirecao(corEsquerda, corDireita, corCentro);
	}	
}
