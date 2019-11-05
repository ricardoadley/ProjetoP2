package sistema;

import java.util.ArrayList;
import java.util.List;
/**
 * Armazena e faz a busca por termos em todas as entidades do programa
 * @author Ricardo A. S. Sena
 *
 */
public class BuscadorPalavra{
	/**
	 * Lista com dados que conferem com a busca do usuario
	 */
	private static List<String> encontradas;
/**
 * construtor do objeto 
 */
	public BuscadorPalavra() {
		encontradas = new ArrayList<>();
		encontradas.clear();
	}
	/**
	 * Faz a busca nos dados da entidade por um termo informado pelo usuario e retorna a string que corresponde a
	 * esses dados
	 * @param palavra , a palavra que esta sendo pesquisada
	 * @param frase , a frase em que a palavra sera pesquisada
	 * @return retorna a frase caso o termo esteja nela , caso nao esteja retorna uma string vazia
	 */
	public static String procuraPalavra(String palavra, String frase) {
		Verificador.verificaEntrada(palavra, "Campo termo nao pode ser nulo ou vazio.");;
		if(frase.toLowerCase().contains(palavra.toLowerCase())) {
			return frase;
		}else {
			return "";
		}
	}
	/**
	 * Procura nos dados da entidade pesquisador por um termo informado pelo usuario
	 * @param palavra , o termo que sera pesquisado
	 * @param frase , a frase em que o termo sera pesquisado
	 * @param email , o email do pesquisador em que esta sendo pesquisado a frase no momento
	 * @return retorna o email do pesquisado concatenado com a frase caso o termo seja encontrado, caso nao, retorna uma string vazia
	 */
	public static String procuraPalavraEmPesquisador(String palavra, String frase, String email) {
		Verificador.verificaEntrada(palavra, "Campo termo nao pode ser nulo ou vazio.");;
		if(frase.toLowerCase().contains(palavra.toLowerCase())) {
			return email +": "+frase;
		}else {
			return "";
		}
	}
	/**
	 * Adiciona a frase com o termo pesquisado encontrada durante as pesquisas nas entidades
	 * em uma lista de frases encontradas
	 * @param frase , a frase contendo o termo encontrado
	 */
	public static void adicionaEncontrado(String frase) {
		if(!frase.equals("")) {
			encontradas.add(frase);
		}
	}
	/**
	 * Retorna todas as frases encontradas com o termo pesquisado pelo usuario
	 * @return a represrntacao em string de todas as frases encontradas na busca pelo termo informado
	 */
	public String retornaEncontradas() {
		String retorno = "";
		for(int i=0;i<encontradas.size();i++) {
				retorno = retorno + encontradas.get(i)+" | ";
		}
		encontradas.clear();
		return retorno.substring(0,retorno.length()-3);
		
	}
	/**
	 * Retorna um resultado especifico encontrado durante a pesquisa de acordo com a sua ordem no resultado
	 * @param numeroDoResultado , a ordem em que o resultado foi encontrado
	 * @return a string representando o resultado na posicao informada
	 */
	public String retornaEncontradasNumeroResultado(int numeroDoResultado) {
		String retorno="";
		if(numeroDoResultado < 0) {
			encontradas.clear();
			throw new IllegalArgumentException("Numero do resultado nao pode ser negativo");
		}
		if(numeroDoResultado > encontradas.size()) {
			encontradas.clear();
			throw new IllegalArgumentException("Entidade nao encontrada.");
		}
		retorno = encontradas.get(numeroDoResultado-1);
		encontradas.clear();
		return retorno;
	}
	/**
	 * Retorna a quantidade de resultados que foram encontrados ao pesquisar nas entidades
	 * pelo termo informado
	 * @return a quantidade de resultados encontrados
	 */
	public int retornaQuantidadeDeResultados() {
		int retorno;
		if(encontradas.size() == 0) {
			throw new IllegalArgumentException("Nenhum resultado encontrado");
		}
		retorno = encontradas.size();
		//encontradas.clear();
		return retorno;
	}

}
