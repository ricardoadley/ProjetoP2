package sistema;

import java.util.Map;

/**
 * Classe responsavel por verificar entradas do usuario e, caso necessario,
 * lancar as excecoes correspondentes.
 * 
 * @author josemng
 *
 */
public class Verificador {

	public Verificador() { 
	}

	public void verificaEntrada(String entrada, String aviso) {

		if (entrada == null) {
			throw new NullPointerException(aviso);
		}

		if (entrada.trim().isEmpty()) {
			throw new IllegalArgumentException(aviso);
		}

	}

	public void verificaFormatoNumerico(String entrada, String aviso) {

		try {
 
			int entradaInteiro = Integer.parseInt(entrada);

		} catch (NumberFormatException nfe) {

			throw new NumberFormatException(aviso);

		}

	}
	
	public void verificaEmail(String email, String aviso) {
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

	public void verificaFotoURL(String fotoURL, String aviso) {
		if (fotoURL.length() < 7) {
			throw new IllegalArgumentException(aviso);
		}
		String URL = fotoURL.substring(0, 8);
		if (!URL.equals("https://") && !URL.substring(0, 7).equals("http://")) {
		throw new IllegalArgumentException(aviso);
		}
	}
	
	public void existeChave(Map mapa, String chave, String aviso) {
		if (!mapa.containsKey(chave)) {
			throw new IllegalArgumentException(aviso);
		}
	}
}
