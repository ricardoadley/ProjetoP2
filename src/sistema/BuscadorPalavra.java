package sistema;

import java.util.ArrayList;
import java.util.List;

import atividades.ControladorAtividade;
import pesquisa.PesquisaController;
import pesquisador.PesquisadorController;

/**
 * Armazena e controla as buscas por termos no sistema
 * 
 * @author Ricardo A. S. Sena
 *
 */
public class BuscadorPalavra {
	/**
	 * Armazena os resultados da busca pelo termo
	 */
	List<String> resultadosDaBusca;
	/**
	 * define o controladorAtividade
	 */
	private ControladorAtividade controleAtividade;
	/**
	 * define o PesquisaController
	 */
	private PesquisaController controlePesquisa;
	/**
	 * define o PesquisadorController
	 */
	private PesquisadorController controlePesquisador;

	/**
	 * Construtor do objeto Buscador, todo buscador eh iniciado com os controladores
	 * do sistema como parametro
	 * 
	 * @param cAtividade,
	 *            o controlador de atividade
	 * @param cPesquisa,
	 *            o controlador de pesquisa
	 * @param cPesquisador,
	 *            o controlador de pesquisador
	 */
	public BuscadorPalavra(ControladorAtividade cAtividade, PesquisaController cPesquisa,
			PesquisadorController cPesquisador) {
		this.controleAtividade = cAtividade;
		this.controlePesquisa = cPesquisa;
		this.controlePesquisador = cPesquisador;
		this.resultadosDaBusca = new ArrayList<String>();
	}

	/**
	 * Organiza todos os dados referentes da busca no sistema em uma unica variavel
	 * 
	 * @param termo,
	 *            o termo que sera buscado
	 * @return retorna uma string contendo o resultado da busca pelo termo em todo o
	 *         sistema
	 */
	private List<String> dadosDaBusca(String termo) {
		resultadosDaBusca.addAll(controlePesquisa.procuraPalavra(termo));
		resultadosDaBusca.addAll(controlePesquisador.procuraPalavra(termo));
		resultadosDaBusca.addAll(controlePesquisa.procuraPalavraProblema(termo));
		resultadosDaBusca.addAll(controlePesquisa.procuraPalavraObjetivo(termo));
		resultadosDaBusca.addAll(controleAtividade.procuraPalavra(termo));
		return resultadosDaBusca;

	}

	private void limpaResultados() {
		resultadosDaBusca.clear();
	}

	/**
	 * Retorna uma representacao em string do resultado da busca
	 * 
	 * @param termo,
	 *            o termo que sera pesquisado
	 * @return uma representacao em string da pesquisa pelo termo no sistema
	 */
	public String retornaEncontradas(String termo) {
		List<String> todosOsResultados = new ArrayList<String>();
		todosOsResultados.addAll(dadosDaBusca(termo));
		limpaResultados();
		String retorno = "";
		if (todosOsResultados.size() == 0) {
			return "";
		}
		for (int i = 0; i < todosOsResultados.size(); i++) {
			retorno = retorno + todosOsResultados.get(i) + " | ";
		}
		return retorno.substring(0, retorno.length() - 3);

	}

	/**
	 * Retorna um resultado especifico encontrado durante a pesquisa de acordo com a
	 * sua ordem no resultado
	 * 
	 * @param numeroDoResultado
	 *            , a ordem em que o resultado foi encontrado
	 * @return a string representando o resultado na posicao informada
	 */
	public String retornaEncontradasNumeroResultado(String termo, int numeroDoResultado) {
		// encontradas = dadosDaBusca(termo).split("\\|");
		List<String> todosOsResultados = new ArrayList<String>();
		todosOsResultados.addAll(dadosDaBusca(termo));
		limpaResultados();
		if (numeroDoResultado < 0) {
			throw new IllegalArgumentException("Numero do resultado nao pode ser negativo");
		}
		if (numeroDoResultado > todosOsResultados.size()) {
			throw new IllegalArgumentException("Entidade nao encontrada.");
		}
		return todosOsResultados.get(numeroDoResultado - 1);
	}

	/**
	 * Retorna a quantidade de resultados que foram encontrados ao pesquisar nas
	 * entidades pelo termo informado
	 * 
	 * @return a quantidade de resultados encontrados
	 */
	public int retornaQuantidadeDeResultados(String termo) {
		List<String> todosOsResultados = new ArrayList<String>();
		todosOsResultados.addAll(dadosDaBusca(termo));
		limpaResultados();
		if (todosOsResultados.size() == 0) {
			throw new IllegalArgumentException("Nenhum resultado encontrado");
		}
		return todosOsResultados.size();
	}

}