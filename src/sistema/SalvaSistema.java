package sistema;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;



public class SalvaSistema {
	static ObjectOutputStream objOutput;
	private static File arq;
	
	public SalvaSistema(String caminho) throws IOException {
		this.arq = new File(caminho);
		arq.createNewFile();
	}

	public static void gravarDado(Map mapa) {
		 objOutput = null;
		try {
			objOutput = new ObjectOutputStream(new FileOutputStream(arq,true));
			objOutput.writeObject(mapa);
			objOutput.close();
			// arq.delete();

		} catch (IOException erro) {
			System.out.printf("Erro: %s", erro.getMessage());
		}
	}

}
