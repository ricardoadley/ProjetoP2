package sistema;

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

}
