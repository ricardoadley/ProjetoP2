package sistema;

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
	 * Lista com dados que conferem com a busca do usuario
	 */
	private String[] encontradas;
	/**
	 * Armazena os resultados da busca pelo termo
	 */
	private String resultadoDaBusca = "";
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
	}

	/**
	 * Organiza todos os dados referentes da busca no sistema em uma unica variavel
	 * 
	 * @param termo,
	 *            o termo que sera buscado
	 * @return retorna uma string contendo o resultado da busca pelo termo em todo o
	 *         sistema
	 */
	public String dadosDaBusca(String termo) {
		resultadoDaBusca = controlePesquisa.procuraPalavra(termo) + controlePesquisador.procuraPalavra(termo)
				+ controlePesquisa.procuraPalavraProblema(termo) + controlePesquisa.procuraPalavraObjetivo(termo)
				+ controleAtividade.procuraPalavra(termo);
		return resultadoDaBusca;

	}

	/**
	 * Retorna uma representacao em string do resultado da busca
	 * 
	 * @param termo,
	 *            o termo que sera pesquisado
	 * @return uma representacao em string da pesquisa pelo termo no sistema
	 */
	public String retornaEncontradas(String termo) {
		String retorno = dadosDaBusca(termo);
		if (retorno.length() == 0) {
			return "";
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
		encontradas = dadosDaBusca(termo).split("\\|");
		String retorno = "";
		if (numeroDoResultado < 0) {
			throw new IllegalArgumentException("Numero do resultado nao pode ser negativo");
		}
		if (numeroDoResultado > encontradas.length) {
			throw new IllegalArgumentException("Entidade nao encontrada.");
		}
		retorno = encontradas[numeroDoResultado - 1];
		return retorno.substring(1, retorno.length() - 1);
	}

	/**
	 * Retorna a quantidade de resultados que foram encontrados ao pesquisar nas
	 * entidades pelo termo informado
	 * 
	 * @return a quantidade de resultados encontrados
	 */
	public int retornaQuantidadeDeResultados(String termo) {
		encontradas = dadosDaBusca(termo).split("\\|");
		int retorno;
		if (encontradas.length - 1 == 0) {
			throw new IllegalArgumentException("Nenhum resultado encontrado");
		}
		retorno = encontradas.length - 1;
		return retorno;
	}

}
