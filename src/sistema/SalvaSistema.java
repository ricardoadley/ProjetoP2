package sistema;


import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

public class SalvaSistema {

	public SalvaSistema() {

	}

	public static void gravarDadosAtividade(Map mapa, String local) {
		try {
			FileOutputStream arquivo = new FileOutputStream(".\\saves\\"+ local);
			ObjectOutputStream stream = new ObjectOutputStream(arquivo);
			stream.writeObject(mapa);
			stream.close();
			// arq.delete();
			arquivo.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	// public static void retornaDado() {
	// try {
	// FileInputStream restFile = new FileInputStream(arq);
	// ObjectInputStream stream = new ObjectInputStream(restFile);
	// // recupera o objeto
	// mapaPesquisa = stream.readObject();
	//// mapaPesquisador = stream.readObject();
	//// mapaProblema = stream.readObject();
	//// mapaAtividade = stream.readObject();
	//// mapaObjetivo = stream.readObject();
	//
	// stream.close();
	// restFile.close();
	// } catch (Exception e) {
	//
	// e.printStackTrace();
	//
	// }
	//
	// }

}
