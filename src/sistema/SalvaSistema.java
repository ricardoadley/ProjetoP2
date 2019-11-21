package sistema;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Map;

import atividades.Atividade;
import objetivo.Objetivo;
import pesquisa.Pesquisa;
import pesquisador.Pesquisador;
import problema.Problema;

/**
 * Responsavel por executar a criacao dos arquivos de dados junto com a gravacao
 * e recuperacao dos dados do sistema
 * 
 * @author Ricardo A. S. Sena
 *
 */
public class SalvaSistema {
	/**
	 * construtor da classe
	 */
	public SalvaSistema() {

	}

	/**
	 * Executa a criacao e gravacao dos dados da entidade
	 * 
	 * @param mapa,
	 *            o mapa da entidade que sera gravado
	 * @param local,
	 *            o local (arquivo) em que sera gravado o mapa
	 */
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

	/**
	 * Retorna os dados da entidade atividade registrados no arquivo
	 * 
	 * @return o mapa contendo os dados registrados
	 */
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

	/**
	 * Retorna os dados da entidade objetivo registrados no arquivo
	 * 
	 * @return o mapa contendo os dados registrados
	 */
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

	/**
	 * Retorna os dados da entidade pesquisa registrados no arquivo
	 * 
	 * @return o mapa contendo os dados registrados
	 */
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

	/**
	 * Retorna os dados da entidade pesquisador registrados no arquivo
	 * 
	 * @return o mapa contendo os dados registrados
	 */
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

	/**
	 * Retorna os dados da entidade problema registrados no arquivo
	 * 
	 * @return o mapa contendo os dados registrados
	 */
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

	/**
	 * Registra em um arquivo o valor do id dos objetos criados na classe
	 * 
	 * @param valor
	 *            , a quantidade de objetos cadastrados
	 * @param caminho,
	 *            o local (arquivo) em que os dados seram registrados
	 */
	public static void gravaValorID(int valor, String caminho) {
		try {
			FileWriter arquivo = new FileWriter(".\\saves\\" + caminho);
			PrintWriter gravaArq = new PrintWriter(arquivo);
			gravaArq.print(valor);
			gravaArq.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * retorna a quantidade e ids registrados da entidade objetivo que foram
	 * previamente salvos
	 * 
	 * @return um inteiro representando a quantidade de ids registrados
	 */
	public static int retornaValorIDObjetivo() {
		int numerinho = 0;
		try {
			FileReader arq = new FileReader(".\\saves\\IDObjetivo.dat");
			BufferedReader lerArq = new BufferedReader(arq);
			numerinho = Integer.parseInt(lerArq.readLine());
			lerArq.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return numerinho;
	}

	/**
	 * retorna a quantidade e ids registrados da entidade atividade que foram
	 * previamente salvos
	 * 
	 * @return um inteiro representando a quantidade de ids registrados
	 */
	public static int retornaValorIDAtividade() {
		int numerinho = 0;
		try {
			FileReader arq = new FileReader(".\\saves\\IDAtividade.dat");
			BufferedReader lerArq = new BufferedReader(arq);
			numerinho = Integer.parseInt(lerArq.readLine());
			lerArq.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return numerinho;
	}

	/**
	 * retorna a quantidade e ids registrados da entidade problema que foram
	 * previamente salvos
	 * 
	 * @return um inteiro representando a quantidade de ids registrados
	 */
	public static int retornaValorIDProblema() {
		int numerinho = 0;
		try {
			FileReader arq = new FileReader(".\\saves\\IDProblema.dat");
			BufferedReader lerArq = new BufferedReader(arq);
			numerinho = Integer.parseInt(lerArq.readLine());
			lerArq.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return numerinho;
	}
}
