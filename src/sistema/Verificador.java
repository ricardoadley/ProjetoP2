package sistema;

import java.util.Map;

/**
 * Classe responsavel por verificar entradas do usuario e, caso necessario,
 * lancar as excecoes correspondentes.
 * 
 * @author josemng, Vinicius M. V. Varjao.
 *
 */ 
public class Verificador {
	/**
	 * constroi o objeto
	 */
	public Verificador() {  
	}
	/**
	 * Verifica se uma entrada informada pelo usuario eh valida
	 * @param entrada, o valor informada pelo usuario
	 * @param aviso, o aviso que deve ser retornado caso a entrada seja invalida
	 */
	public static void verificaEntrada(String entrada, String aviso) {

		if (entrada == null) {
			throw new NullPointerException(aviso);
		}

		if (entrada.trim().isEmpty()) {
			throw new IllegalArgumentException(aviso);
		}

	}
	/**
	 * Verifica se o valor informado pelo usuario eh um valor numerico valido
	 * @param entrada, a entrada informada pelo usuario
	 * @param aviso , o aviso que deve ser retornado caso a entrada seja invalida
	 */
	public static void verificaFormatoNumerico(String entrada, String aviso) {

		try {
 
			int entradaInteiro = Integer.parseInt(entrada);

		} catch (NumberFormatException nfe) {

			throw new NumberFormatException(aviso);

		}

	}
	/**
	 * Verifica se um email informado pelo usuario esta no formato valido
	 * @param email, o email informado que sera verificado
	 * @param aviso, o aviso que sera retornado caso o email seja invalido
	 */
	public static void verificaEmail(String email, String aviso) {
		String[] emailSplit = email.split("@");
		if (emailSplit.length != 2) {
			throw new IllegalArgumentException(aviso);
			}
		if (emailSplit[0].trim().isEmpty()) {
			throw new IllegalArgumentException(aviso);
		}
		if (emailSplit[1].trim().isEmpty()) {
			throw new IllegalArgumentException(aviso);
		}
	}
	/**
	 * Verifica se o URL de um foto informado pelo usuario eh valido
	 * @param fotoURL, o URL da foto informado pelo usuario
	 * @param aviso, o aviso que sera retornado caso a URL seja invalida
	 */
	public static void verificaFotoURL(String fotoURL, String aviso) {
		if (fotoURL.length() < 7) {
			throw new IllegalArgumentException(aviso);
		}
		String URL = fotoURL.substring(0, 8);
		if (!URL.equals("https://") && !URL.substring(0, 7).equals("http://")) {
		throw new IllegalArgumentException(aviso);
		}
	}
	/**
	 * Verifica se existe uma chave no mapa
	 * @param mapa, o mapa em que a chave sera pesquisada
	 * @param chave, a chave que sera pesquisada
	 * @param aviso, a mensagem que sera retornada caso a chave nao exista no mapa
	 */
	public static void existeChave(Map mapa, String chave, String aviso) {
		if (!mapa.containsKey(chave)) {
			throw new IllegalArgumentException(aviso);
		}
	}

}
