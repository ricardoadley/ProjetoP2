package sistema;

import java.util.Map;

import atividades.Item;
import pesquisa.Pesquisa;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Classe responsavel por verificar entradas do usuario e, caso necessario,
 * lancar as excecoes correspondentes.
 * 
 * @author Jose Matheus do Nascimento Gama, Vinicius M. V. Varjao.
 *
 */
public class Verificador {
	/**
	 * Construtor do Verificador.
	 */
	public Verificador() {
	}

	/**
	 * Verifica se uma entrada informada pelo usuario eh valida
	 * 
	 * @param entrada, o valor informada pelo usuario
	 * @param aviso,   o aviso que deve ser retornado caso a entrada seja invalida
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
	 * Verifica se um email informado pelo usuario esta no formato valido
	 * 
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
	 * 
	 * @param fotoURL, o URL da foto informado pelo usuario
	 * @param aviso,   o aviso que sera retornado caso a URL seja invalida
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
	 * 
	 * @param mapa,  o mapa em que a chave sera pesquisada
	 * @param chave, a chave que sera pesquisada
	 * @param aviso, a mensagem que sera retornada caso a chave nao exista no mapa
	 */
	public static void existeChave(Map mapa, Object chave, String aviso) {
		if (!mapa.containsKey(chave)) {
			throw new IllegalArgumentException(aviso);
		}
	}

	public static void existeChaveString(Map mapa, String chave, String aviso) {
		if (!mapa.containsKey(chave)) {
			throw new IllegalArgumentException(aviso);
		}
	}

	/**
	 * Verifica se uma pesquisa esta ativada
	 * 
	 * @param pesquisas mapa de pesquisas
	 * @param codigo    codigo da pesquisa a ser vrificada
	 * @param aviso     mensagem de erro a ser lanacada
	 */
	public static void verificaEhAtiva(Map<String, Pesquisa> pesquisas, String codigo, String aviso) {
		if (!pesquisas.get(codigo).isAtivada()) {
			throw new IllegalArgumentException(aviso);
		}
	}

	/**
	 * Verifica se um inteiro e positivo
	 * 
	 * @param inteiro inteiro a ser verificado
	 * @param aviso   mensagem de erro a ser lancada
	 */
	public static void verificaInteiroPositivo(int inteiro, String aviso) {
		if (inteiro < 1) {
			throw new IllegalArgumentException(aviso);
		} 
	}

	/**
	 * Verifica se o semestre e valido
	 * 
	 * @param novoValoro novo valor que sera verificado
	 * @param aviso      o aviso que deseja-se exibir no lancamento da excecao
	 */
	public static void verificaSemestre(String novoValor, String aviso) {
		int semestre = Integer.parseInt(novoValor);
		if (semestre < 1) {
			throw new IllegalArgumentException(aviso);
		}
	}

	/**
	 * Verifica se o IEA e valido, ou seja, se e maior ou igual a 0 e menor ou igual
	 * a 10.
	 * 
	 * @param iea   o valor que representa o IEA
	 * @param aviso o aviso que deseja-se exibir no lancamento da excecao
	 */
	public static void verificaIEA(double iea, String aviso) {
		if (iea < 0.0 || iea > 10.0) {
			throw new IllegalArgumentException(aviso);
		}
	}

	/**
	 * Verifica se a data e valida
	 * 
	 * @param data  a data
	 * @param aviso o aviso que deseja-se exibir no lancamento da excecao
	 */
	public static void verificaData(String data, String aviso) {
		if (isDateValid(data) != true) {
			throw new IllegalArgumentException(aviso);
		}
	}

	/**
	 * Verifica se o tipo e valido. Caso seja diferente de EXTERNO ou ESTUDANTE ou
	 * PROFESSOR, e lancada uma excecao.
	 * 
	 * @param tipo  a string do tipo
	 * @param aviso o aviso que deseja-se exibir no lancamento da excecao
	 */
	public static void verificaTipo(String tipo, String aviso) {
		if (!tipo.equals("EXTERNO") && !tipo.equals("ESTUDANTE") && !tipo.equals("PROFESSOR")) {
			throw new IllegalArgumentException(aviso);
		}
	}

	/**
	 * Verifica se a data é válida.
	 * 
	 * @param strDate a data
	 * @return true caso a data seja valida, false caso seja invalida
	 */
	private static boolean isDateValid(String strDate) {
		String dateFormat = "dd/MM/uuuu";

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat)
				.withResolverStyle(ResolverStyle.STRICT);
		try {
			LocalDate date = LocalDate.parse(strDate, dateTimeFormatter);
			return true;
		} catch (DateTimeParseException e) {
			return false;
		}
	}
}
