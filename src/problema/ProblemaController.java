package problema;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sistema.BuscadorPalavra;
import sistema.Verificador;

/**
 * Classe responsavel por controlar as informacoes dos problemas. Todo
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
	 * Constroi um objeto do tipo ProblemaController.
	 */
	public ProblemaController() {
		this.problemas = new HashMap<>();
		this.code = 1;
	}

	/**
	 * Adiciona um objeto do tipo Problema no mapa de problemas.
	 * 
	 * @param descricao
	 *            descricao do problema
	 * @param viabilidade
	 *            representacao quantitativa do quanto o problema e viavel
	 */
	public String cadastraProblema(String descricao, String viabilidade) {

		Verificador.verificaEntrada(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		Verificador.verificaEntrada(viabilidade, "Campo viabilidade nao pode ser nulo ou vazio.");
		Verificador.verificaFormatoNumerico(viabilidade, "Valor invalido de viabilidade.");

		int viabilidadeInt = Integer.parseInt(viabilidade);
		if (viabilidadeInt < 1 || viabilidadeInt > 5) {
			throw new IllegalArgumentException("Valor invalido de viabilidade.");
		}

		String codigo = "P" + this.code;
		problemas.put(codigo, new Problema(descricao, viabilidadeInt, codigo));
		this.code++;
		return codigo;
	}

	/**
	 * Remove um Problema do mapa de problemas.
	 * 
	 * @param codigo
	 *            o codigo pelo qual o Problema e identificado unicamente
	 */
	public void apagarProblema(String codigo) {

		Verificador.verificaEntrada(codigo, "Campo codigo nao pode ser nulo ou vazio.");

		if (!problemas.containsKey(codigo)) {
			throw new IllegalArgumentException("Problema nao encontrado");
		}

		problemas.remove(codigo);

	}

	/**
	 * Retorna a representacao em String de um Problema, no formato "codigo -
	 * descricao - viabilidade".
	 * 
	 * @param codigo
	 *            o codigo pelo qual o Problema e identificado unicamente
	 * @return a representacao em String de um problema
	 */
	public String exibeProblema(String codigo) {
		Verificador.verificaEntrada(codigo, "Campo codigo nao pode ser nulo ou vazio."); // add by RASS, it's not in
																							// specification
		if (!problemas.containsKey(codigo)) {
			throw new IllegalArgumentException("Problema nao encontrado");
		}

		return problemas.get(codigo).toString();

	}

	public Problema getProblema(String idProblema) {
		return this.problemas.get(idProblema);
	}

	/**
	 * procura, nos dados da entidade Problema, por um termo informado pelo usuario
	 * 
	 * @param palavra,
	 *            o termo, informado pelo usuario, que sera pesquisado nos dados da
	 *            entidade.
	 */
	public void procurarPalavra(String palavra) {
		Verificador.verificaEntrada(palavra, "Campo termo nao pode ser nulo ou vazio.");
		List<Problema> listaProblemas = new ArrayList<>(this.problemas.values());
		Collections.sort(listaProblemas, new ComparadorProblema());
		for (Problema problema : listaProblemas) {
			BuscadorPalavra.adicionaEncontrado(
					BuscadorPalavra.procuraPalavra(palavra, problema.getCodigo() + ": " + problema.getDescricao()));
		}
	}

}
