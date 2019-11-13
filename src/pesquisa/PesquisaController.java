package pesquisa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import atividades.Atividade;
import atividades.ControladorAtividade;
import objetivo.ObjetivoController;
import problema.ProblemaController;
import sistema.Verificador;

/**
 * Classe controladora de Pesquisas.
 * 
 * @author Beatriz Truta, José Matheus do N. Gama
 *
 */
public class PesquisaController {
	/**
	 * mapa de pesquisas cadastradas no sistema
	 */
	private Map<String, Pesquisa> mapaPesquisas;
	/**
	 * identificador unico da pesquisa
	 */
	private int idPesquisa = 0;

	/**
	 * Instancia da classe controladora de Objetivos.
	 */
	private ObjetivoController objetivoController;

	/**
	 * Instancia da classe controladora de Objetivos.
	 */
	private ProblemaController problemaController;
	/**
	 * Instancia da classe controladora de Atividades.
	 */
	private ControladorAtividade atividadeController;
	/**
	 * Instancia da classe verificadora de entradas, a qual lanca as excessoes
	 * necessarias.
	 */
	Verificador verificador = new Verificador();

	/**
	 * Construtor de Pesquisa
	 */
	public PesquisaController(ControladorAtividade controlaAtividade) {
		this.mapaPesquisas = new HashMap<>();
		this.atividadeController = controlaAtividade;
		this.objetivoController = new ObjetivoController();
		this.problemaController = new ProblemaController();
	}

	/**
	 * Adiciona uma nova pesquisa no mapa de pesquisas
	 * 
	 * @param descricao,
	 *            a descricao da pesquisa
	 * @param interesse,
	 *            o campo de interesse da pesquisa
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
	 * @param codigo,
	 *            o codigo da pesquisa que sera alterada
	 * @param conteudoASerAlterado
	 *            , o parametro que sera alterado
	 * @param novoConteudo,
	 *            o novo valor que o parametro alterado recebera
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
	 * @param codigo
	 *            , o codigo da pesquisa que sera exibido
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
	 * @param codigo,
	 *            o codigo da pesquisa que sera ativada
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
	 * @param codigo,
	 *            o codigo da pesquisa que sera encerrada
	 * @param motivo,
	 *            o motivo do encerramento da pesquisa
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
	 * @param codigo,
	 *            o codigo da pesquisa que sera verificado
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
	 * Verifica se o campo interesse da pesquisa segue os requisitos de criacao
	 * 
	 * @param interesse
	 *            , o campo de interesse da pesquisa
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
	 * @param id,
	 *            o id da pesquisa
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
	 * Procura, na entidade Pesquisa, por um termo informado pelo usuario
	 * 
	 * @param palavra,
	 *            o termo que o usuario deseja que seja procurado
	 * @return uma string contendo todos os resultados da procura
	 */
	public String procuraPalavra(String palavra) {
		// encontradas = null;
		String retorno = "";
		String fraseDescricao = "";
		String fraseCampo = "";
		Verificador.verificaEntrada(palavra, "Campo termo nao pode ser nulo ou vazio.");
		List<Pesquisa> listaPesquisas = new ArrayList<>(this.mapaPesquisas.values());
		Collections.sort(listaPesquisas, new ComparadorPesquisa());
		for (Pesquisa pesquisa : listaPesquisas) {
			fraseDescricao = pesquisa.getCodigo() + ": " + pesquisa.getDescricao();
			fraseCampo = pesquisa.getCodigo() + ": " + pesquisa.getCampo();
			if (fraseDescricao.toLowerCase().contains(palavra)) {
				retorno = retorno + fraseDescricao + " | ";
			}
			if (fraseCampo.toLowerCase().contains(palavra)) {
				retorno = retorno + fraseCampo + " | ";
			}
		}
		return retorno;
	}

	// Metodos referentes as operacoes com OBJETIVOS!

	/**
	 * Cadastra um objeto do tipo Objetivo no mapa de objetivos em
	 * objetivoController.
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
		return this.objetivoController.cadastraObjetivo(tipo, descricao, aderencia, viabilidade);
	}

	/**
	 * Remove um Objetivo do mapa de objetivos em objetivoController
	 * 
	 * @param codigo
	 *            o codigo pelo qual o objetivo e identificado unicamente
	 */
	public void apagarObjetivo(String codigo) {
		this.objetivoController.apagarObjetivo(codigo);
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
		return this.objetivoController.exibeObjetivo(codigo);
	}

	/**
	 * Associa um Objetivo a uma Pesquisa. Uma pesquisa pode estar associada a
	 * vários objetivos, entretanto, cada objetivo só pode estar associado a uma
	 * única pesquisa.
	 * 
	 * @param idPesquisa
	 *            o identificador unico da Pesquisa
	 * @param idObjetivo
	 *            o identificador unico do Objetivo
	 * @return return a String correspondente ao sucesso ou nao da operacao
	 */
	@SuppressWarnings("static-access")
	public boolean associaObjetivo(String idPesquisa, String idObjetivo) {

		verificador.verificaEntrada(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		verificador.verificaEntrada(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
		verificador.existeChave(mapaPesquisas, idPesquisa, "Pesquisa nao encontrada.");
		verificador.verificaEhAtiva(this.mapaPesquisas, idPesquisa, "Pesquisa desativada.");

		if (this.mapaPesquisas.get(idPesquisa).contemObjetivo(idObjetivo)) {
			return false;
		}

		for (Pesquisa pesquisa : this.mapaPesquisas.values()) {

			if (pesquisa.contemObjetivo(idObjetivo)) {
				throw new IllegalArgumentException("Objetivo ja associado a uma pesquisa.");
			}

		}

		this.mapaPesquisas.get(idPesquisa).adicionaObjetivo(idObjetivo,
				this.objetivoController.getObjetivo(idObjetivo));
		return true;
	}

	/**
	 * Retira a associacao entre um Objetivo e uma Pesquisa.
	 * 
	 * @param idPesquisa
	 *            o identificador unico da Pesquisa
	 * @param idObjetivo
	 *            o identificador unico do Objetivo
	 * @return return a String correspondente ao sucesso ou nao da operacao
	 */
	@SuppressWarnings("static-access")
	public boolean desassociaObjetivo(String idPesquisa, String idObjetivo) {

		verificador.verificaEntrada(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		verificador.verificaEntrada(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
		verificador.existeChave(mapaPesquisas, idPesquisa, "Pesquisa nao encontrada.");
		verificador.verificaEhAtiva(this.mapaPesquisas, idPesquisa, "Pesquisa desativada.");

		if (!this.mapaPesquisas.get(idPesquisa).contemObjetivo(idObjetivo)) {
			return false;
		}

		this.mapaPesquisas.get(idPesquisa).removeObjetivo(idObjetivo);
		return true;
	}

	/**
	 * Busca um objetivo que contenha tal palavra passada como parametro.
	 * 
	 * @param palavra
	 *            a palavra a qual se deseja procurar um Objetivo que contenha a
	 *            mesma
	 */
	public String procuraPalavraObjetivo(String palavra) {
		return this.objetivoController.procuraPalavra(palavra);
	}

	// Metodos referentes as operacoes com PROBLEMAS!

	/**
	 * Adiciona um objeto do tipo Problema no mapa de problemas em
	 * problemaController
	 * 
	 * @param descricao
	 *            descricao do problema
	 * @param viabilidade
	 *            representacao quantitativa do quanto o problema e viavel
	 */
	public String cadastraProblema(String descricao, String viabilidade) {
		return this.problemaController.cadastraProblema(descricao, viabilidade);
	}

	/**
	 * Remove um Problema do mapa de problemas em problemaController
	 * 
	 * @param codigo
	 *            o codigo pelo qual o Problema e identificado unicamente
	 */
	public void apagarProblema(String codigo) {
		this.problemaController.apagarProblema(codigo);
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
		return this.problemaController.exibeProblema(codigo);
	}

	/**
	 * Procura um Problema que possua a palavra passada como parametro
	 * 
	 * @param palavra
	 *            palavra a palavra a qual se deseja procurar um Problema que
	 *            contenha a mesma
	 */
	public String procuraPalavraProblema(String palavra) {
		return this.problemaController.procuraPalavra(palavra);
	}

	/**
	 * Associa um Problema a uma Pesquisa e retorna a String correspondente ao
	 * sucesso ou nao da operacao. Uma pesquisa pode estar associada a um único
	 * problema. Mas o mesmo problema pode estar associado a várias pesquisas.
	 * 
	 * @param idPesquisa
	 *            o identificador unico da pesquisa
	 * @param idProblema
	 *            o identificador unico do problema
	 * @return return a String correspondente ao sucesso ou nao da operacao
	 */
	public boolean associaProblema(String idPesquisa, String idProblema) {
		Verificador.verificaEntrada(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		Verificador.verificaEntrada(idProblema, "Campo idProblema nao pode ser nulo ou vazio.");
		Verificador.existeChave(this.mapaPesquisas, idPesquisa, "Pesquisa nao encontrada.");
		Verificador.verificaEhAtiva(this.mapaPesquisas, idPesquisa, "Pesquisa desativada.");

		if (this.problemaController.getProblema(idProblema).equals(this.mapaPesquisas.get(idPesquisa).getProblema())) {
			return false;
		}

		if (this.mapaPesquisas.get(idPesquisa).contemProblema()) {
			throw new IllegalArgumentException("Pesquisa ja associada a um problema.");
		}

		if (!this.mapaPesquisas.get(idPesquisa).isAtivada()) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}

		this.mapaPesquisas.get(idPesquisa).setProblema(this.problemaController.getProblema(idProblema));
		return true;

	}

	/**
	 * Retira a associação entre um Problema e uma Pesquisa
	 * 
	 * @param idPesquisa
	 *            o identificador unico da Pesquisa
	 * @param idProblema
	 *            o identificador unico do Problema
	 * @return return a String correspondente ao sucesso ou nao da operacao
	 */
	@SuppressWarnings("static-access")
	public boolean desassociaProblema(String idPesquisa) {

		verificador.verificaEntrada(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		verificador.existeChave(this.mapaPesquisas, idPesquisa, "Pesquisa nao encontrada.");
		verificador.verificaEhAtiva(this.mapaPesquisas, idPesquisa, "Pesquisa desativada.");

		if (!this.mapaPesquisas.get(idPesquisa).contemProblema()) {
			return false;
		}

		this.mapaPesquisas.get(idPesquisa).setProblema(null);
		return true;

	}

	/**
	 * Lista pesquisas de acordo com o tipo de ordenacao desejada.
	 * 
	 * @param ordem
	 *            a ordem com que se deseja listar as pesquisas
	 * @return retorna a String correspondente as informacoes de todas as pesquisas
	 */
	public String listaPesquisas(String ordem) {

		if (!(ordem.equalsIgnoreCase("PROBLEMA") || ordem.equalsIgnoreCase("OBJETIVOS")
				|| ordem.equalsIgnoreCase("PESQUISA")) || ordem == null) {
			throw new IllegalArgumentException("Valor invalido da ordem");
		}

		ArrayList<Pesquisa> valores = new ArrayList<Pesquisa>(this.mapaPesquisas.values());
		String pesquisasOrdenadas = "";

		if (ordem.equalsIgnoreCase("PESQUISA")) {

			Collections.sort(valores, new PesquisaIDComparator());

			for (int i = 0; i < valores.size(); i++) {

				pesquisasOrdenadas += valores.get(i).toString() + " | ";

			}

			return pesquisasOrdenadas.substring(0, pesquisasOrdenadas.length() - 3);

		}

		if (ordem.equalsIgnoreCase("PROBLEMA")) {

			Collections.sort(valores, new PesquisaProblemaComparator());

			for (int i = 0; i < valores.size(); i++) {

				pesquisasOrdenadas += valores.get(i).toString() + " | ";

			}

			return pesquisasOrdenadas.substring(0, pesquisasOrdenadas.length() - 3);

		}

		else {

			Collections.sort(valores, new PesquisaObjetivosComparator());

			for (int i = 0; i < valores.size(); i++) {

				pesquisasOrdenadas += valores.get(i).toString() + " | ";

			}

			return pesquisasOrdenadas.substring(0, pesquisasOrdenadas.length() - 3);

		}

	}

	/**
	 * Armazena o código de uma atividade associada à uma Pesquisa na mesma.
	 * 
	 * @param codigoPesquisa
	 *            codigo da pesquisa que vai receber a associacao
	 * @param codigoAtividade
	 *            codigo da atividade a ser associada
	 * @return false caso a associacao nao ocorra, true caso contrario
	 */
	public boolean associaAtividade(String codigoPesquisa, String codigoAtividade) {
		Verificador.verificaEntrada(codigoPesquisa, "Campo codigoPesquisa nao pode ser nulo ou vazio.");
		Verificador.verificaEntrada(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		Verificador.existeChave(mapaPesquisas, codigoPesquisa, "Pesquisa nao encontrada.");
		if (!this.atividadeController.existeAtividade(codigoAtividade)) {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}
		if (!this.pesquisaEhAtiva(codigoPesquisa)) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
		this.atividadeController.associaPesquisa(codigoPesquisa, codigoAtividade);
		return this.mapaPesquisas.get(codigoPesquisa).associaAtividade(codigoAtividade);
	}

	/**
	 * Remove o codigo de uma ativadade associada da lista de atividades
	 * 
	 * @param codigoPesquisa
	 *            codigo da pesquisa a ter a atividade desassociada
	 * @param codigoAtividade
	 *            codigo da atividade a ser removido
	 * @return false caso a desassociacao nao ocorra, true caso contrario
	 */
	public boolean desassociaAtividade(String codigoPesquisa, String codigoAtividade) {
		Verificador.verificaEntrada(codigoPesquisa, "Campo codigoPesquisa nao pode ser nulo ou vazio.");
		Verificador.verificaEntrada(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		Verificador.existeChave(mapaPesquisas, codigoPesquisa, "Pesquisa nao encontrada.");
		if (!this.atividadeController.existeAtividade(codigoAtividade)) {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}
		if (!this.pesquisaEhAtiva(codigoPesquisa)) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
		this.atividadeController.desassociaPesquisa(codigoPesquisa, codigoAtividade);
		return this.mapaPesquisas.get(codigoPesquisa).desassociaAtividade(codigoAtividade);
	}
	

	public Pesquisa getPesquisa(String idPesquisa) {
		return this.mapaPesquisas.get(idPesquisa);
	}

	public Map<String, Pesquisa> getMapaPesquisas() {
		return mapaPesquisas;
	}
	
	public boolean existePesquisa(String codigo) {
		// retorna se ja existe ou nao a atividade no mapa
		return this.mapaPesquisas.containsKey(codigo);
	}

//	private Pesquisa capturaPesquisaNoMapa(String codigo) {
//		return this.mapaPesquisas.get(codigo);
//	}
}