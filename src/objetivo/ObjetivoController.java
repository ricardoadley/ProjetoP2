package objetivo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sistema.BuscadorPalavra;
import sistema.Verificador;

/**
 * Classe responsavel por controlar as informacoes dos objetivos. Todo
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
	 * Constroi um objeto do tipo ObjetivoController.
	 */
	public ObjetivoController() {
		this.objetivos = new HashMap<>();
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
	public String cadastraObjetivo(String tipo, String descricao, String aderencia, String viabilidade) {

		Verificador.verificaEntrada(tipo, "Campo tipo nao pode ser nulo ou vazio.");
		Verificador.verificaEntrada(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		Verificador.verificaEntrada(aderencia, "Campo aderencia nao pode ser nulo ou vazio.");
		Verificador.verificaEntrada(viabilidade, "Campo viabilidade nao pode ser nulo ou vazio.");

		Verificador.verificaFormatoNumerico(viabilidade, "Valor invalido de viabilidade.");
		Verificador.verificaFormatoNumerico(aderencia, "Valor invalido de aderencia");

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
		return codigo;
	}

	/**
	 * Remove um Objetivo do mapa de objetivos.
	 * 
	 * @param codigo
	 *            o codigo pelo qual o objetivo e identificado unicamente
	 */
	public void apagarObjetivo(String codigo) {

		Verificador.verificaEntrada(codigo, "Campo codigo nao pode ser nulo ou vazio.");

		if (!this.objetivos.containsKey(codigo)) {
			throw new IllegalArgumentException("Objetivo nao encontrado");
		}

		this.objetivos.remove(codigo);
	}

	/**
	 * Retorna a representacao em String de um Objetivo, no formato "codigo - tipo -
	 * descricao - valor(aderencia + viabilidade)".
	 * 
	 * @param codigo
	 *            o codigo pelo qual o objetivo e identificado unicamente
	 * @return a representacao em String de um Objetivo
	 */
	public String exibeObjetivo(String codigo) {

		Verificador.verificaEntrada(codigo, "Campo codigo nao pode ser nulo ou vazio.");

		if (!this.objetivos.containsKey(codigo)) {
			throw new IllegalArgumentException("Objetivo nao encontrado");
		}

		return this.objetivos.get(codigo).toString();

	}

	public Objetivo getObjetivo(String idObjetivo) {
		return this.objetivos.get(idObjetivo);
	}

	/**
	 * procura, nos dados da entidade objetivo, por um termo informado pelo usuario
	 * 
	 * @param palavra,
	 *            o termo, que sera pesquisado, informado pelo usuario.
	 */
	public void ProcurarPalavra(String palavra) {
		Verificador.verificaEntrada(palavra, "Campo termo nao pode ser nulo ou vazio.");
		List<Objetivo> listaObjetivos = new ArrayList<>(this.objetivos.values());
		Collections.sort(listaObjetivos, new ComparadorObjetivo());
		for (Objetivo objetivo : listaObjetivos) {
			BuscadorPalavra.adicionaEncontrado(
					BuscadorPalavra.procuraPalavra(palavra, objetivo.getCodigo() + ": " + objetivo.getDescricao()));
		}
	}

}
