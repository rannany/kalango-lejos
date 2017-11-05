package ultimate;

import java.io.Serializable;

public class Caminho implements Serializable{
	public int nivel;
	public String direcao;
	public Caminho(int nivel, String direcao) {
		this.nivel = nivel;
		this.direcao = direcao;
	}
}
