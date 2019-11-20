package sistema;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

import atividades.Atividade;
import objetivo.Objetivo;
import pesquisa.Pesquisa;
import pesquisador.Pesquisador;
import problema.Problema;

public class SalvaSistema {

	public SalvaSistema() {

	}

	public static void gravarDados(Map mapa, String local) {
		try {
			FileOutputStream arquivo = new FileOutputStream(".\\saves\\" + local);
			ObjectOutputStream stream = new ObjectOutputStream(arquivo);
			stream.writeObject(mapa);
			stream.close();
			// arq.delete();
			arquivo.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static Map<String, Atividade> retornaDadoAtividades() {
		try {
			FileInputStream arquivo = new FileInputStream(".\\saves\\dadosAtividade.dat");
			ObjectInputStream stream = new ObjectInputStream(arquivo);
			// recupera o objeto
			Map<String, Atividade> mapaAtividades = (Map<String, Atividade>) stream.readObject();
			stream.close();
			arquivo.close();
			return mapaAtividades;
		} catch (Exception e) {

			e.printStackTrace();

		}
		return null;
	}

	public static Map<String, Objetivo> retornaDadoObjetivo() {
		try {
			FileInputStream arquivo = new FileInputStream(".\\saves\\dadosObjetivo.dat");
			ObjectInputStream stream = new ObjectInputStream(arquivo);
			// recupera o objeto
			Map<String, Objetivo> mapaObjetivos = (Map<String, Objetivo>) stream.readObject();
			stream.close();
			arquivo.close();
			return mapaObjetivos;
		} catch (Exception e) {

			e.printStackTrace();

		}
		return null;
	}

	public static Map<String, Pesquisa> retornaDadoPesquisa() {
		try {
			FileInputStream arquivo = new FileInputStream(".\\saves\\dadosPesquisa.dat");
			ObjectInputStream stream = new ObjectInputStream(arquivo);
			// recupera o objeto
			Map<String, Pesquisa> mapaPesquisa = (Map<String, Pesquisa>) stream.readObject();
			stream.close();
			arquivo.close();
			return mapaPesquisa;
		} catch (Exception e) {

			e.printStackTrace();

		}
		return null;
	}

	public static Map<String, Pesquisador> retornaDadoPesquisador() {
		try {
			FileInputStream arquivo = new FileInputStream(".\\saves\\dadosPesquisadores.dat");
			ObjectInputStream stream = new ObjectInputStream(arquivo);
			// recupera o objeto
			Map<String, Pesquisador> mapaPesquisador = (Map<String, Pesquisador>) stream.readObject();
			stream.close();
			arquivo.close();
			return mapaPesquisador;
		} catch (Exception e) {

			e.printStackTrace();

		}
		return null;
	}

	public static Map<String, Problema> retornaDadoProblema() {
		try {
			FileInputStream arquivo = new FileInputStream(".\\saves\\dadosProblemas.dat");
			ObjectInputStream stream = new ObjectInputStream(arquivo);
			// recupera o objeto
			Map<String, Problema> mapaProblemas = (Map<String, Problema>) stream.readObject();
			stream.close();
			arquivo.close();
			return mapaProblemas;
		} catch (Exception e) {

			e.printStackTrace();

		}
		return null;
	}
}
