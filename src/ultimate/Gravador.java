package ultimate;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;


public class Gravador {
	public Gravador() {

	}

	public void escrever(ArrayList<Caminho> entrada, String nomeArquivo) {
		FileOutputStream saida = null; // declare outside the try block
		File data = new File(nomeArquivo);
		ObjectOutputStream dataOut = null;

		try {
			saida = new FileOutputStream(data);
			dataOut = new ObjectOutputStream(saida);
			System.out.println("deu certo");
		} catch (IOException erro) {
			System.err.println("gravacao falhou");
			System.err.println(erro);
			System.exit(1);
		}

		try {

			dataOut.writeObject(entrada);

			saida.close(); // flush the buffer and write the file
			dataOut.close();
		} catch (IOException e) {
			System.out.println(e);
			System.err.println("Failed to write to output stream");
		}
	}

	// ==========================================================================

	public void ler(String nomeArquivo) {
		FileInputStream saida = null; // declare outside the try block
		File data = new File(nomeArquivo);
		ObjectInputStream dataIn = null;

		try {
			saida = new FileInputStream(data);
			dataIn = new ObjectInputStream(saida);
			System.out.println("deu certo");
		} catch (IOException erro) {
			System.err.println("gravacao falhou");
			System.err.println(erro);
			System.exit(1);
		}
		try {
			List<Caminho> retorno = (List<Caminho>) dataIn.readObject();
			saida.close();
			dataIn.close();
			for(Caminho c : retorno) {
			System.out.println(c.direcao);
			}
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
}