package objetivo;

import java.util.HashMap;
import java.util.Map;

import sistema.Verificador;

/**
 * Classe responsável por controlar as informações dos objetivos. Todo
 * ObjetivoController possui um mapa de objetivos e um codigo que gera uma
 * identificacao unica para os objetivos.
 * 
 * @author josemng
 *
 */
public class ObjetivoController {

	/**
	 * Mapa de objetivos do tipo <String, Objetivo>
	 */
	private Map<String, Objetivo> objetivos;

	/**
	 * Codigo que gera uma identificacao unica para os objetivos.
	 */
	private int code;

	/**
	 * Instancia da classe lancadora de excexoes.
	 */
	private Verificador verificador;

	/**
	 * Constrói um objeto do tipo ObjetivoController.
	 */
	public ObjetivoController() {
		this.objetivos = new HashMap<>();
		this.verificador = new Verificador();
		this.code = 1;
	}

	/**
	 * Cadastra um objeto do tipo Objetivo no mapa de objetivos.
	 * 
	 * @param tipo
	 *            o tipo do objetivo, pode ser geral ou especifico
	 * @param descricao
	 *            a descricao do objetivo
	 * @param aderencia
	 *            representacao quantitativa do quanto o objetivo esta aderido a um
	 *            problema
	 * @param viabilidade
	 *            representacao quantitativa do quanto o objetivo e viavel
	 */
	public void cadastraObjetivo(String tipo, String descricao, String aderencia, String viabilidade) {

		verificador.verificaEntrada(tipo, "Campo tipo nao pode ser nulo ou vazio.");
		verificador.verificaEntrada(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		verificador.verificaEntrada(aderencia, "Campo aderencia nao pode ser nulo ou vazio.");
		verificador.verificaEntrada(viabilidade, "Campo viabilidade nao pode ser nulo ou vazio.");

		verificador.verificaFormatoNumerico(viabilidade, "Valor invalido de viabilidade.");
		verificador.verificaFormatoNumerico(aderencia, "Valor invalido de aderencia");

		if (!(tipo.equals("GERAL") || tipo.equals("ESPECIFICO"))) {
			throw new IllegalArgumentException("Valor invalido de tipo.");
		}

		int aderenciaInt = Integer.parseInt(aderencia);
		int viabilidadeInt = Integer.parseInt(viabilidade);

		if (aderenciaInt < 1 || aderenciaInt > 5) {
			throw new IllegalArgumentException("Valor invalido de aderencia");
		}

		if (viabilidadeInt < 1 || viabilidadeInt > 5) {
			throw new IllegalArgumentException("Valor invalido de viabilidade.");
		}

		String codigo = "O" + this.code;
		this.objetivos.put(codigo, new Objetivo(tipo, descricao, aderenciaInt, viabilidadeInt, codigo));
		this.code++;
	}

	/**
	 * Remove um Objetivo do mapa de objetivos.
	 * 
	 * @param codigo
	 *            o codigo pelo qual o objetivo e identificado unicamente
	 */
	public void apagarObjetivo(String codigo) {

		verificador.verificaEntrada(codigo, "Campo codigo nao pode ser nulo ou vazio.");

		if (!this.objetivos.containsKey(codigo)) {
			throw new IllegalArgumentException("Objetivo nao encontrado");
		}

		this.objetivos.remove(codigo);
	}

	/**
	 * Retorna a representação em String de um Objetivo, no formato "codigo - tipo
	 * - descricao - valor(aderencia + viabilidade)".
	 * 
	 * @param codigo
	 *            o codigo pelo qual o objetivo e identificado unicamente
	 * @return a representação em String de um Objetivo
	 */
	public String exibeObjetivo(String codigo) {

		verificador.verificaEntrada(codigo, "Campo codigo nao pode ser nulo ou vazio.");

		if (!this.objetivos.containsKey(codigo)) {
			throw new IllegalArgumentException("Objetivo nao encontrado");
		}

		return this.objetivos.get(codigo).toString();

	}

}
