package pesquisa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import objetivo.ObjetivoController;
import problema.ProblemaController;
import sistema.BuscadorPalavra;
import sistema.Verificador;

/**
 * 
 * @author Beatriz Truta
 *
 */
public class PesquisaController {
	/**
	 * mapa de pesquisas cadastradas no sistema
	 */
	public Map<String, Pesquisa> mapaPesquisas;
	/**
	 * identificador unico da pesquisa
	 */
	private int idPesquisa = 0;

	private ObjetivoController objetivoController;
	private ProblemaController problemaController;

	Verificador verificador = new Verificador();

	/**
	 * construtor da classe
	 */
	public PesquisaController() {
		this.mapaPesquisas = new HashMap<>();
		this.objetivoController = new ObjetivoController();
		this.problemaController = new ProblemaController();
	}

	/**
	 * Adiciona uma nova pesquisa no mapa de pesquisas
	 * 
	 * @param descricao, a descricao da pesquisa
	 * @param interesse, o campo de interesse da pesquisa
	 * @return retornar o codigo de cadastro da pesquisa
	 */
	public String cadastraPesquisa(String descricao, String interesse) {
		verificaInteresseValido(interesse);
		Verificador.verificaEntrada(descricao, "Descricao nao pode ser nula ou vazia.");
		String codigo = "";
		for (int i = 0; i < 3; i++) {
			codigo += interesse.charAt(i);
		}
		codigo = codigo + (idPesquisa + 1);
		codigo = geraId(codigo.toUpperCase());
		this.mapaPesquisas.put(codigo, new Pesquisa(descricao, interesse, codigo));
		return codigo;
	}

	/**
	 * Altera algum dado especifico de uma pesquisa ja cadastrada
	 * 
	 * @param codigo,              o codigo da pesquisa que sera alterada
	 * @param conteudoASerAlterado , o parametro que sera alterado
	 * @param novoConteudo,        o novo valor que o parametro alterado recebera
	 */
	public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {
		if (!this.mapaPesquisas.containsKey(codigo))
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		if (!pesquisaEhAtiva(codigo))
			throw new IllegalArgumentException("Pesquisa desativada.");
		if (conteudoASerAlterado.equals("descricao") || conteudoASerAlterado.equals("DESCRICAO")) {
			Verificador.verificaEntrada(novoConteudo, "Descricao nao pode ser nula ou vazia.");
			this.mapaPesquisas.get(codigo).setDescricao(novoConteudo);
		} else if (conteudoASerAlterado.equals("campo") || conteudoASerAlterado.equals("CAMPO")) {
			Verificador.verificaEntrada(novoConteudo, "Formato do campo de interesse invalido.");
			this.mapaPesquisas.get(codigo).setCampo(novoConteudo);
		} else {
			throw new IllegalArgumentException("Nao e possivel alterar esse valor de pesquisa.");
		}
	}

	/**
	 * Exibe como string uma pesquisa solicitada
	 * 
	 * @param codigo , o codigo da pesquisa que sera exibido
	 * @return a representacao em string da pesquisa
	 */
	public String exibePesquisa(String codigo) {
		if (!this.mapaPesquisas.containsKey(codigo))
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		if (!pesquisaEhAtiva(codigo))
			throw new IllegalArgumentException("Pesquisa inativa.");
		return this.mapaPesquisas.get(codigo).toString();
	}

	/**
	 * Altera o status de uma pesquisa cadastrada para ativado
	 * 
	 * @param codigo, o codigo da pesquisa que sera ativada
	 */
	public void ativaPesquisa(String codigo) {
		if (!this.mapaPesquisas.containsKey(codigo))
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		if (pesquisaEhAtiva(codigo))
			throw new IllegalArgumentException("Pesquisa ja ativada.");
		this.mapaPesquisas.get(codigo).setStatus("Ativa");
	}

	/**
	 * Altera o status de uma pesquisa para encerrado
	 * 
	 * @param codigo, o codigo da pesquisa que sera encerrada
	 * @param motivo, o motivo do encerramento da pesquisa
	 */
	public void encerraPesquisa(String codigo, String motivo) {
		Verificador.verificaEntrada(motivo, "Motivo nao pode ser nulo ou vazio.");
		if (!this.mapaPesquisas.containsKey(codigo))
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		if (!pesquisaEhAtiva(codigo))
			throw new IllegalArgumentException("Pesquisa desativada.");
		this.mapaPesquisas.get(codigo).setStatus("Inativa");
	}

	/**
	 * Verifica se uma pesquisa esta ativa
	 * 
	 * @param codigo, o codigo da pesquisa que sera verificado
	 * @return retorna true ou false de acordo com o status da pesquisa
	 */
	public boolean pesquisaEhAtiva(String codigo) {
		Verificador.verificaEntrada(codigo, "Codigo nao pode ser nulo ou vazio.");
		if (!this.mapaPesquisas.containsKey(codigo)) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		} else {
			if (this.mapaPesquisas.get(codigo).getStatus().equals("Ativa")) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * Verifica se o campo interessa da pesquisa segue os requisitos de criacao
	 * 
	 * @param interesse , o campo de interesse da pesquisa
	 */
	private void verificaInteresseValido(String interesse) {
		Verificador.verificaEntrada(interesse, "Formato do campo de interesse invalido.");
		if (interesse.length() > 255 || interesse.length() < 3)
			throw new IllegalArgumentException("Formato do campo de interesse invalido.");
		String[] interesses = interesse.split(",");
		if (interesses.length > 4)
			throw new IllegalArgumentException("Formato do campo de interesse invalido.");
		for (String i : interesses) {
			if (i.trim().equals("")) {
				throw new IllegalArgumentException("Formato do campo de interesse invalido.");
			}
		}
	}

	/**
	 * Gera um id unico para a pesquisa
	 * 
	 * @param id, o id da pesquisa
	 * @return retorna o id da pesquisa
	 */
	private String geraId(String id) {
		if (mapaPesquisas.containsKey(id)) {
			int indice = (int) id.charAt(id.length() - 1);
			indice++;
			return id.replace(id.charAt(id.length() - 1), (char) indice);
		}
		return id;
	}

	/**
	 * Procura, nos dados da entidade, por um termo informado pelo usuario
	 * 
	 * @param palavra, o termo que o usuario deseja pesquisar
	 */
	public void ProcurarPalavraPesquisa(String palavra) {
		Verificador.verificaEntrada(palavra, "Campo termo nao pode ser nulo ou vazio.");
		List<Pesquisa> listaPesquisas = new ArrayList<>(this.mapaPesquisas.values());
		Collections.sort(listaPesquisas, new ComparadorPesquisa());
		for (Pesquisa pesquisa : listaPesquisas) {
			BuscadorPalavra.adicionaEncontrado(
					BuscadorPalavra.procuraPalavra(palavra, pesquisa.getCodigo() + ": " + pesquisa.getDescricao()));
			BuscadorPalavra.adicionaEncontrado(BuscadorPalavra.procuraPalavraEmPesquisa(palavra,
					pesquisa.getCodigo() + ": " + pesquisa.getDescricao() + pesquisa.getCampo(),
					pesquisa.getCampo().length()));

		}
	}

	// Metodos referentes as operacoes com OBJETIVOS!

	public String cadastraObjetivo(String tipo, String descricao, String aderencia, String viabilidade) {
		return this.objetivoController.cadastraObjetivo(tipo, descricao, aderencia, viabilidade);
	}

	public void apagarObjetivo(String codigo) {
		this.objetivoController.apagarObjetivo(codigo);
	}

	public String exibeObjetivo(String codigo) {
		return this.objetivoController.exibeObjetivo(codigo);
	}

	/**
	 * Associa um Objetivo a uma Pesquisa. Uma pesquisa pode estar associada a
	 * vários objetivos, entretanto, cada objetivo só pode estar associado a uma
	 * única pesquisa.
	 * 
	 * @param idPesquisa o identificador unico da Pesquisa
	 * @param idObjetivo o identificador unico do Objetivo
	 * @return return a String correspondente ao sucesso ou nao da operacao
	 */
	@SuppressWarnings("static-access")
	public String associaObjetivo(String idPesquisa, String idObjetivo) {

		verificador.verificaEntrada(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		verificador.verificaEntrada(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
		verificador.existeChave(mapaPesquisas, idPesquisa, "Pesquisa nao encontrada.");
		verificador.verificaEhAtiva(this.mapaPesquisas, idPesquisa, "Pesquisa desativada.");
		
		if (this.mapaPesquisas.get(idPesquisa).contemObjetivo(idObjetivo)) {
			return "false";
		}

		for (Pesquisa pesquisa : this.mapaPesquisas.values()) {

			if (pesquisa.contemObjetivo(idObjetivo)) {
				throw new IllegalArgumentException("Objetivo ja associado a uma pesquisa.");
			}

		}

		this.mapaPesquisas.get(idPesquisa).adicionaObjetivo(idObjetivo,
				this.objetivoController.getObjetivo(idObjetivo));
		return "sucesso";
	}

	/**
	 * Retira a associacao entre um Objetivo e uma Pesquisa.
	 * 
	 * @param idPesquisa o identificador unico da Pesquisa
	 * @param idObjetivo o identificador unico do Objetivo
	 * @return return a String correspondente ao sucesso ou nao da operacao
	 */
	@SuppressWarnings("static-access")
	public String desassociaObjetivo(String idPesquisa, String idObjetivo) {

		verificador.verificaEntrada(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		verificador.verificaEntrada(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
		verificador.existeChave(mapaPesquisas, idPesquisa, "Pesquisa nao encontrada.");
		verificador.verificaEhAtiva(this.mapaPesquisas, idPesquisa, "Pesquisa desativada.");
		
		if (!this.mapaPesquisas.get(idPesquisa).contemObjetivo(idObjetivo)) {
			return "false";
		}

		this.mapaPesquisas.get(idPesquisa).removeObjetivo(idObjetivo);
		return "sucesso";
	}

	public void procurarPalavraObjetivo(String palavra) {
		this.objetivoController.ProcurarPalavra(palavra);
	}

	// Metodos referentes as operacoes com PROBLEMAS!

	public String cadastraProblema(String descricao, String viabilidade) {
		return this.problemaController.cadastraProblema(descricao, viabilidade);
	}

	public void apagarProblema(String codigo) {
		this.problemaController.apagarProblema(codigo);
	}

	public String exibeProblema(String codigo) {
		return this.problemaController.exibeProblema(codigo);
	}

	public void procurarPalavraProblema(String palavra) {
		this.problemaController.procurarPalavra(palavra);
	}

	/**
	 * Associa um Problema a uma Pesquisa e retorna a String correspondente ao
	 * sucesso ou nao da operacao. Uma pesquisa pode estar associada a um único
	 * problema. Mas o mesmo problema pode estar associado a várias pesquisas.
	 * 
	 * @param idPesquisa o identificador unico da pesquisa
	 * @param idProblema o identificador unico do problema
	 * @return return a String correspondente ao sucesso ou nao da operacao
	 */
	@SuppressWarnings("static-access")
	public String associaProblema(String idPesquisa, String idProblema) {

		verificador.verificaEntrada(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		verificador.verificaEntrada(idProblema, "Campo idProblema nao pode ser nulo ou vazio.");
		verificador.existeChave(this.mapaPesquisas, idPesquisa, "Pesquisa nao encontrada.");
		verificador.verificaEhAtiva(this.mapaPesquisas, idPesquisa, "Pesquisa desativada.");
		
		if (this.problemaController.getProblema(idProblema).equals(this.mapaPesquisas.get(idPesquisa).getProblema())) {
			return "false";
		}

		if (this.mapaPesquisas.get(idPesquisa).contemProblema()) {
			throw new IllegalArgumentException("Pesquisa ja associada a um problema.");
		}

		if (!this.mapaPesquisas.get(idPesquisa).isAtivada()) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}

		this.mapaPesquisas.get(idPesquisa).setProblema(this.problemaController.getProblema(idProblema));
		return "sucesso";

	}

	/**
	 * Retira a associação entre um Problema e uma Pesquisa
	 * 
	 * @param idPesquisa o identificador unico da Pesquisa
	 * @param idProblema o identificador unico do Problema
	 * @return return a String correspondente ao sucesso ou nao da operacao
	 */
	@SuppressWarnings("static-access")
	public String desassociaProblema(String idPesquisa, String idProblema) {

		verificador.verificaEntrada(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		verificador.verificaEntrada(idProblema, "Campo idProblema nao pode ser nulo ou vazio.");
		verificador.existeChave(this.mapaPesquisas, idPesquisa, "Pesquisa nao encontrada.");
		verificador.verificaEhAtiva(this.mapaPesquisas, idPesquisa, "Pesquisa desativada.");
		
		if (!this.mapaPesquisas.get(idPesquisa).contemProblema()) {
			return "false";
		}

		this.mapaPesquisas.get(idPesquisa).setProblema(null);
		return "sucesso";

	}

	public String listaPesquisar(String ordem) {
		
		return "a";
		
	}
	
	public Pesquisa getPesquisa(String idPesquisa) {
		return this.mapaPesquisas.get(idPesquisa);
	}

	
	
}