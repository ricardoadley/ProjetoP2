package sistema;

import java.util.ArrayList;
import java.util.List;

public class BuscadorPalavra{
	private static List<String> encontradas;

	public BuscadorPalavra() {
		encontradas = new ArrayList<>();
	}
	public static String procuraPalavra(String palavra, String frase) {
		Verificador.verificaEntrada(palavra, "Campo termo nao pode ser nulo ou vazio.");;
		if(frase.toLowerCase().contains(palavra.toLowerCase())) {
			return frase;
		}else {
			return "";
		}
	}
	public static String procuraPalavraEmPesquisador(String palavra, String frase, String email) {
		Verificador.verificaEntrada(palavra, "Campo termo nao pode ser nulo ou vazio.");;
		if(frase.toLowerCase().contains(palavra.toLowerCase())) {
			return email +": "+frase;
		}else {
			return "";
		}
	}
	public static String procuraPalavraEmPesquisa(String palavra, String frase, int tcampo) {
		Verificador.verificaEntrada(palavra, "Campo termo nao pode ser nulo ou vazio.");;
		if(frase.toLowerCase().contains(palavra.toLowerCase())) {
			return frase.substring(0,frase.length() - tcampo);
		}else {
			return "";
		}
	}
	public static void adicionaEncontrado(String frase) {
		if(!frase.equals("")) {
			encontradas.add(frase);
		}
	}

	public String retornaEncontradas() {
		String retorno = "";
		for(int i=0;i<encontradas.size();i++) {
				retorno = retorno + encontradas.get(i)+" | ";
		}
		encontradas.clear();
		return retorno.substring(0,retorno.length()-3);
		
	}
	public String retornaEncontradasNumeroResultado(int numeroDoResultado) {
		String retorno="";
		if(numeroDoResultado < 0) {
			throw new IllegalArgumentException("Numero do resultado nao pode ser negativo");
		}
		if(numeroDoResultado > encontradas.size()) {
			throw new IllegalArgumentException("Entidade nao encontrada.");
		}
		retorno = encontradas.get(numeroDoResultado);
		encontradas.clear();
		return retorno;
	}
	public int retornaQuantidadeDeResultados() {
		int retorno;
		retorno = encontradas.size();
		encontradas.clear();
		return retorno;
	}

}
