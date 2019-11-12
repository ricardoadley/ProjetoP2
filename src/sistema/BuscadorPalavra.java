package sistema;

import java.util.List;

import atividades.ControladorAtividade;
import pesquisa.PesquisaController;
import pesquisador.PesquisadorController;

/**
 * Armazena e faz a busca por termos em todas as entidades do programa
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
	 * construtor do objeto
	 */
	private ControladorAtividade controleAtividade = new ControladorAtividade();;
	private PesquisaController controlePesquisa = new PesquisaController(controleAtividade);
	private PesquisadorController controlePesquisador = new PesquisadorController();

	public BuscadorPalavra() {
	//	encontradas = new ArrayList<String>();

		// encontradas.clear();
	}

	private String fazABusca(String termo) {
		String oquetem = "";
//		tudao = controleAtividade.procuraPalavra(termo) +controlePesquisa.procuraPalavraObjetivo(termo) +controlePesquisa.procuraPalavra(termo)+
//				controlePesquisa.procuraPalavraProblema(termo)+controlePesquisador.procuraPalavra(termo);
		//encontradas.addAll(controleAtividade.procuraPalavra(termo));
//		encontradas.addAll(controlePesquisa.procuraPalavraObjetivo(termo));
//		encontradas.addAll(controlePesquisa.procuraPalavra(termo));
//		encontradas.addAll(controlePesquisa.procuraPalavraProblema(termo));
//		encontradas.addAll(controlePesquisador.procuraPalavra(termo));
		oquetem = controleAtividade.procuraPalavra(termo) + controlePesquisa.procuraPalavraObjetivo(termo) + controlePesquisa.procuraPalavra(termo)+
				controlePesquisa.procuraPalavraProblema(termo) + controlePesquisador.procuraPalavra(termo);	
		return oquetem;
		
	}
	/**
	 * Retorna todas as frases encontradas com o termo pesquisado pelo usuario
	 * 
	 * @return a represrntacao em string de todas as frases encontradas na busca
	 *         pelo termo informado
	 */
	public String retornaEncontradas(String termo) {
		encontradas = fazABusca(termo).split("");
		String retorno = "";
		if (encontradas.length == 0) {
			return retorno;
		}
		for (int i = 0; i < encontradas.length; i++) {
			retorno = retorno + encontradas[i] + " | ";
		}
		return retorno;

	}

	/**
	 * Retorna um resultado especifico encontrado durante a pesquisa de acordo com a
	 * sua ordem no resultado
	 * 
	 * @param numeroDoResultado
	 *            , a ordem em que o resultado foi encontrado
	 * @return a string representando o resultado na posicao informada
	 */
//	public String retornaEncontradasNumeroResultado(String termo,int numeroDoResultado) {
//			fazABusca(termo);
//		String retorno = "";
//		if (numeroDoResultado < 0) {
//			throw new IllegalArgumentException("Numero do resultado nao pode ser negativo");
//		}
//		if (numeroDoResultado > encontradas.size()) {
//			throw new IllegalArgumentException("Entidade nao encontrada.");
//		}
//		retorno = encontradas.get(numeroDoResultado - 1);
//		return retorno;
//	}

	/**
	 * Retorna a quantidade de resultados que foram encontrados ao pesquisar nas
	 * entidades pelo termo informado
	 * 
	 * @return a quantidade de resultados encontrados
	 */
//	public int retornaQuantidadeDeResultados(String termo) {
//		fazABusca(termo);
//		int retorno;
//		if (encontradas.size() == 0) {
//			throw new IllegalArgumentException("Nenhum resultado encontrado");
//		}
//		retorno = encontradas.size();
//		return retorno;
//	}

}
