package ultimate;
/*
 * -2 = Esquerda
 * -1 = Direita
 *  0 = Vermelho
 *  1 = Verde
 *  2 = Azul
 *  3 = Amarelo
 *  4 = Magenta
 *  5 = Laranja
 *  6 = Branco
 *  7 = Preto
 *  8 = Rosa 
 *  9 = Cinza
 *  10 = Cinza claro
 *  11 = Cinza escuro 
 *  12 = Ciano
 * 
*/

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Mapeador {
	public ArrayList<Caminho> mapaPercurso = new ArrayList<Caminho>();
	private Scanner scan;
	public int nivel = 0;
	public boolean emLoop = true;
	public int tmp = 0;
	
	public Mapeador() {
		mapaPercurso.add(new Caminho(0,"inicio"));
	}

	public void mapeia() {
		scan = new Scanner(System.in);
		String resposta, direcaoCorreta;
		System.out.println("NIVEL= " + nivel);
		while (emLoop) {
			nivel++;
			System.out.println("indo ...");
			System.out.println("encontrou cor");
			System.out.println("vai pra que lado?");
			resposta = scan.nextLine();
			System.out.println("é a direção certa?");
			direcaoCorreta = scan.nextLine();
			System.out.println(direcaoCorreta);
			if (direcaoCorreta.equals("s")) {
				mapaPercurso.add(new Caminho(nivel, resposta));
				mapeia();
			} else if (direcaoCorreta.equals("n")) {
				System.out.println("fail");
				nivel--;
				return;
			} else if (direcaoCorreta.equals("fim")) {
				emLoop = false;
				return;
			}
		}
	}

	public ArrayList<Caminho> chamaMapa() {
		for (Caminho s : this.mapaPercurso) {
			System.out.println(s.nivel + " " + s.direcao);
		}
		return this.mapaPercurso;
	}

	public void gravaMapa(ArrayList<String> mapa) throws Throwable {
		for (String s : mapa) {
			FileOutputStream fos = new FileOutputStream("t.tmp");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(s);
			oos.close();
		}
	}
}
