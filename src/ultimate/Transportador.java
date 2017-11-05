package ultimate;

import lejos.nxt.Button;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;

public class Transportador {
	
	public UltrasonicSensor distanciaCen;
	public UltrasonicSensor distanciaDireita;
	public UltrasonicSensor distanciaEsquerda;
	private DifferentialPilot piloto;
	private double catetoA, hip;
	private Garra garra;
	
	public Transportador (UltrasonicSensor distanciaCen, Garra garra, DifferentialPilot piloto, UltrasonicSensor distanciaDireita, UltrasonicSensor distanciaEsquerda) {
		this.distanciaCen = distanciaCen;
		this.distanciaDireita = distanciaDireita;
		this.distanciaEsquerda = distanciaEsquerda;
		this.garra = garra;
		this.piloto = piloto;
		this.distanciaCen.continuous();
		this.distanciaEsquerda.continuous();
		this.distanciaDireita.continuous();
		this.distanciaEsquerda.continuous();
		this.distanciaDireita.continuous();
	}
	
	private double pitagoras() {
		return Math.sqrt(Math.pow(hip, 2) - Math.pow(catetoA, 2));
	}
	
	public void radar() {
		int direita;
		int esquerda;
		do{
			direita = distanciaDireita.getDistance();
			esquerda = distanciaEsquerda.getDistance();
			if (direita < 30) {
				System.out.println("Direira: " + direita);
				catetoA = distanciaDireita.getDistance();
				pegaPessoa(90, 25);
			}else if (esquerda < 30) {
				System.out.println("Esquerda: " + esquerda);
				catetoA = distanciaEsquerda.getDistance();
				pegaPessoa(-90, 25);
			}
		}while(true);
	}
	
	private void encontra_pessoa(int sinal) {
		int rotacao = -1;
		if (sinal > 0)
			rotacao = -5;
		else
			rotacao = 5;
		piloto.setTravelSpeed(5);
		int count = 0;
		while(distanciaCen.getDistance() > 25) {
			piloto.arc(0, rotacao);
			count++;
		}
		hip = distanciaCen.getDistance();
		piloto.stop();
		Delay.msDelay(500);
		piloto.arc(0, -(rotacao * count));
		piloto.travel(pitagoras());
		System.out.println(pitagoras());
	}
	
	public void pegaPessoa(int graus, int distancia) {
		piloto.stop();
		Delay.msDelay(1000);
		encontra_pessoa(graus);
		piloto.arc(0, -1 * graus);
		piloto.stop();
		Delay.msDelay(500);
		piloto.setTravelSpeed(5);
		int distanciaPenssoa = distanciaCen.getDistance();
		System.out.println("Distancia: " + distanciaPenssoa);
		garra.abre(20);
		piloto.travel(distanciaPenssoa - 10);
		garra.fecha(30);
		piloto.travel(-1 * (distanciaPenssoa - 10));
		piloto.stop();
		Delay.msDelay(500);
		piloto.arc(0, (graus));
		/*
		do {
			piloto.travel(1);
			cont++;
		}while (distanciaCen.getDistance() > distancia);
		garra.abre(20);
		piloto.stop();
		Delay.msDelay(500);
		piloto.travel(-5);
		piloto.setTravelSpeed(10);
		 
		 */
		
	}

}
