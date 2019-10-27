package problema;

import java.util.HashMap;
import java.util.Map;

import sistema.Verificador;

/**
 * Classe responsável por controlar as informações dos problemas. Todo
 * problemaController possui um mapa de problemas e um codigo que gera uma
 * identificacao unica para os problemas.
 * 
 * @author josemng
 *
 */
public class ProblemaController {

	/**
	 * Mapa de problemas do tipo <String, Problema>
	 */
	Map<String, Problema> problemas;

	/**
	 * Codigo que gera uma identificacao unica para os problemas.
	 */
	private int code;

	/**
	 * Instancia da classe lancadora de excecoes.
	 */
	Verificador verificador;

	/**
	 * Constroi um objeto do tipo ProblemaController.
	 */
	public ProblemaController() {
		this.problemas = new HashMap<>();
		this.verificador = new Verificador();
		this.code = 1;
	}

	/**
	 * Adiciona um objeto do tipo Problema no mapa de problemas.
	 * 
	 * @param descricao descricao do problema
	 * @param viabilidade representacao quantitativa do quanto o problema e viavel
	 */
	public void cadastraProblema(String descricao, String viabilidade) {

		verificador.verificaEntrada(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		verificador.verificaEntrada(viabilidade, "Campo viabilidade nao pode ser nulo ou vazio.");
		verificador.verificaFormatoNumerico(viabilidade, "Valor invalido de viabilidade.");

		int viabilidadeInt = Integer.parseInt(viabilidade);
		if (viabilidadeInt < 1 || viabilidadeInt > 5) {
			throw new IllegalArgumentException("Valor invalido de viabilidade.");
		}

		String codigo = "P" + this.code;
		problemas.put(codigo, new Problema(descricao, viabilidadeInt, codigo));
		this.code++;
	}

	/**
	 * Remove um Problema do mapa de problemas.
	 * 
	 * @param codigo o codigo pelo qual o Problema e identificado unicamente
	 */
	public void apagarProblema(String codigo) {

		verificador.verificaEntrada(codigo, "Campo codigo nao pode ser nulo ou vazio.");

		if (!problemas.containsKey(codigo)) {
			throw new IllegalArgumentException("Problema nao encontrado");
		}

		problemas.remove(codigo);

	}

	/**
	 * Retorna a representacao em String de um Problema, no formato "codigo - descricao - viabilidade".
	 * 
	 * @param codigo o codigo pelo qual o Problema e identificado unicamente
	 * @return a representacao em String de um problema
	 */
	public String exibeProblema(String codigo) {

		if (!problemas.containsKey(codigo)) {
			throw new IllegalArgumentException("Problema nao encontrado");
		}

		return problemas.get(codigo).toString();

	}
}
