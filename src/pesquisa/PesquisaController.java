package pesquisa;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import atividades.ControladorAtividade;
import objetivo.ObjetivoController;
import pesquisador.PesquisadorController;
import problema.ProblemaController;
import sistema.Verificador;

/**
 * Classe controladora de Pesquisas.
 * 
 * @author Beatriz Truta, Jose Matheus do N. Gama
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
	private int idPesquisa;
	/**
	 * Atual estrategia de sugestao de proxima atividade
	 */
	private String estrategia;

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
	 * Instancia da classe controladora de Pesquisadores.
	 */
	private PesquisadorController pesquisadorController;

	/**
	 * Construtor de PesquisaController.
	 * 
	 * @param objetivoController
	 * @param problemaController
	 * @param pesquisadorController
	 * @param atividadeController
	 */
	public PesquisaController(ObjetivoController objetivoController, ProblemaController problemaController,
			PesquisadorController pesquisadorController, ControladorAtividade atividadeController) {
		this.mapaPesquisas = new HashMap<>();
		this.idPesquisa = 1;
		this.estrategia = "MAIS_ANTIGA";
		this.atividadeController = atividadeController;
		this.objetivoController = objetivoController;
		this.problemaController = problemaController;
		this.pesquisadorController = pesquisadorController;
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
		codigo += (idPesquisa);
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
	 * Verifica se o campo interesse da pesquisa segue os requisitos de criacao
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

		int aux = 1;

		for (String pesquisa : this.mapaPesquisas.keySet()) {

			String codigo = mapaPesquisas.get(pesquisa).getCodigo();

			if (codigo.substring(0, codigo.length() - 1).equals(id.substring(0, id.length() - 1))) {
				aux++;
			}

		}

		return id.substring(0, id.length() - 1) + aux;

	}

	/**
	 * Procura, na entidade Pesquisa, por um termo informado pelo usuario
	 * 
	 * @param palavra, o termo que o usuario deseja que seja procurado
	 * @return uma string contendo todos os resultados da procura
	 */
	public List<String> procuraPalavra(String palavra) {
		// encontradas = null;
		List<String> resultados = new ArrayList<String>();
		// String retorno = "";
		String fraseDescricao = "";
		String fraseCampo = "";
		Verificador.verificaEntrada(palavra, "Campo termo nao pode ser nulo ou vazio.");
		List<Pesquisa> listaPesquisas = new ArrayList<>(this.mapaPesquisas.values());
		Collections.sort(listaPesquisas, new PesquisaIDComparator());
		for (Pesquisa pesquisa : listaPesquisas) {
			fraseDescricao = pesquisa.getCodigo() + ": " + pesquisa.getDescricao();
			fraseCampo = pesquisa.getCodigo() + ": " + pesquisa.getCampo();
			if (fraseDescricao.toLowerCase().contains(palavra)) {
				// retorno = retorno + fraseDescricao + " | ";
				resultados.add(fraseDescricao);
			}
			if (fraseCampo.toLowerCase().contains(palavra)) {
				// retorno = retorno + fraseCampo + " | ";
				resultados.add(fraseCampo);
			}
		}
		return resultados;
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
	public boolean associaObjetivo(String idPesquisa, String idObjetivo) {

		Verificador.verificaEntrada(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		Verificador.verificaEntrada(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
		Verificador.existeChave(mapaPesquisas, idPesquisa, "Pesquisa nao encontrada.");
		Verificador.verificaEhAtiva(this.mapaPesquisas, idPesquisa, "Pesquisa desativada.");

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
	 * @param idPesquisa o identificador unico da Pesquisa
	 * @param idObjetivo o identificador unico do Objetivo
	 * @return return a String correspondente ao sucesso ou nao da operacao
	 */
	public boolean desassociaObjetivo(String idPesquisa, String idObjetivo) {

		Verificador.verificaEntrada(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		Verificador.verificaEntrada(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
		Verificador.existeChave(mapaPesquisas, idPesquisa, "Pesquisa nao encontrada.");
		Verificador.verificaEhAtiva(this.mapaPesquisas, idPesquisa, "Pesquisa desativada.");

		if (!this.mapaPesquisas.get(idPesquisa).contemObjetivo(idObjetivo)) {
			return false;
		}

		this.mapaPesquisas.get(idPesquisa).removeObjetivo(idObjetivo);
		return true;
	}

	/**
	 * Busca um objetivo que contenha tal palavra passada como parametro.
	 * 
	 * @param palavra a palavra a qual se deseja procurar um Objetivo que contenha a
	 *                mesma
	 */
	public List<String> procuraPalavraObjetivo(String palavra) {
		return this.objetivoController.procuraPalavra(palavra);
	}

	/**
	 * Procura um Problema que possua a palavra passada como parametro
	 * 
	 * @param palavra palavra a palavra a qual se deseja procurar um Problema que
	 *                contenha a mesma
	 */
	public List<String> procuraPalavraProblema(String palavra) {
		return this.problemaController.procuraPalavra(palavra);
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
	 * @param idPesquisa o identificador unico da Pesquisa
	 * @param idProblema o identificador unico do Problema
	 * @return return a String correspondente ao sucesso ou nao da operacao
	 */
	public boolean desassociaProblema(String idPesquisa) {

		Verificador.verificaEntrada(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		Verificador.existeChave(this.mapaPesquisas, idPesquisa, "Pesquisa nao encontrada.");
		Verificador.verificaEhAtiva(this.mapaPesquisas, idPesquisa, "Pesquisa desativada.");

		if (!this.mapaPesquisas.get(idPesquisa).contemProblema()) {
			return false;
		}

		this.mapaPesquisas.get(idPesquisa).setProblema(null);
		return true;

	}

	/**
	 * Lista pesquisas de acordo com o tipo de ordenacao desejada.
	 * 
	 * @param ordem a ordem com que se deseja listar as pesquisas
	 * @return retorna a String correspondente as informacoes de todas as pesquisas
	 */
	public String listaPesquisas(String ordem) {

		if (!(ordem.equalsIgnoreCase("PROBLEMA") || ordem.equalsIgnoreCase("OBJETIVOS")
				|| ordem.equalsIgnoreCase("PESQUISA")) || ordem == null) {
			throw new IllegalArgumentException("Valor invalido da ordem");
		}

		ArrayList<Pesquisa> pesquisas = new ArrayList<Pesquisa>(this.mapaPesquisas.values());
		String pesquisasOrdenadas = "";

		if (ordem.equalsIgnoreCase("PESQUISA")) {

			Collections.sort(pesquisas, new PesquisaIDComparator());

			for (int i = 0; i < pesquisas.size(); i++) {

				pesquisasOrdenadas += pesquisas.get(i).toString() + " | ";

			}

			return pesquisasOrdenadas.substring(0, pesquisasOrdenadas.length() - 3);

		}

		if (ordem.equalsIgnoreCase("PROBLEMA")) {

			Collections.sort(pesquisas, new PesquisaProblemaComparator());

			for (int i = 0; i < pesquisas.size(); i++) {

				pesquisasOrdenadas += pesquisas.get(i).toString() + " | ";

			}

			return pesquisasOrdenadas.substring(0, pesquisasOrdenadas.length() - 3);

		}

		else {

			Collections.sort(pesquisas, new PesquisaObjetivosComparator());

			for (int i = 0; i < pesquisas.size(); i++) {

				pesquisasOrdenadas += pesquisas.get(i).toString() + " | ";

			}

			return pesquisasOrdenadas.substring(0, pesquisasOrdenadas.length() - 3);

		}

	}

	/**
	 * Armazena o codigo de uma atividade associada a uma Pesquisa na mesma.
	 * 
	 * @param codigoPesquisa  codigo da pesquisa que vai receber a associacao
	 * @param codigoAtividade codigo da atividade a ser associada
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
		return this.mapaPesquisas.get(codigoPesquisa).associaAtividade(codigoAtividade,
				this.atividadeController.capturaAtividadeNoMapa(codigoAtividade));
	}

	/**
	 * Remove o codigo de uma ativadade associada da lista de atividades
	 * 
	 * @param codigoPesquisa  codigo da pesquisa a ter a atividade desassociada
	 * @param codigoAtividade codigo da atividade a ser removido
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

	/**
	 * Associa Pesquisadores a Pesquisas
	 * 
	 * @param idPesquisa       o identificador unico da Pesquisa
	 * @param emailPesquisador o identificador unico do Pesquisador
	 * @return true se associado corretamente, false se nao
	 */
	public boolean associaPesquisador(String idPesquisa, String emailPesquisador) {
		Verificador.verificaEntrada(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		Verificador.verificaEntrada(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");
		Verificador.verificaEmail(emailPesquisador, "Atributo email com formato invalido.");
		Verificador.existeChave(this.mapaPesquisas, idPesquisa, "Pesquisa nao encontrada.");
		Verificador.verificaEhAtiva(this.mapaPesquisas, idPesquisa, "Pesquisa desativada.");

		return this.mapaPesquisas.get(idPesquisa).associaPesquisador(emailPesquisador,
				this.pesquisadorController.getPesquisador(emailPesquisador));
	}

	/**
	 * Desassocia um Pesquisador de uma Pesquisa
	 * 
	 * @param idPesquisa       o identificador unico da Pesquisa
	 * @param emailPesquisador o identificador unico do Pesquisador
	 * @return true se foi desassociado corretamente, false se nao
	 */
	public boolean desassociaPesquisador(String idPesquisa, String emailPesquisador) {
		Verificador.verificaEntrada(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		Verificador.verificaEntrada(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");
		Verificador.verificaEmail(emailPesquisador, "Atributo email com formato invalido.");
		Verificador.existeChave(this.mapaPesquisas, idPesquisa, "Pesquisa nao encontrada.");
		Verificador.verificaEhAtiva(this.mapaPesquisas, idPesquisa, "Pesquisa desativada.");

		return this.mapaPesquisas.get(idPesquisa).desassociaPesquisador(emailPesquisador);
	}
	public boolean existePesquisa(String codigo) {
		if(!this.mapaPesquisas.containsKey(codigo)) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
		return this.mapaPesquisas.containsKey(codigo);
	}

	/**
	 * Grava resumo de uma Pesquisa em um arquivo de texto.
	 * 
	 * @param codigoPesquisa o identificador unico da Pesquisa
	 */
	public void gravarResumo(String codigoPesquisa) {
		Verificador.verificaEntrada(codigoPesquisa, "Pesquisa nao pode ser nula ou vazia.");
		Verificador.existeChave(this.mapaPesquisas, codigoPesquisa, "Pesquisa nao encontrada.");

		try {

			File file = new File("./_" + codigoPesquisa + ".txt");

			FileWriter escritorDeArquivo = new FileWriter(file.getAbsoluteFile());
			BufferedWriter buffWrite = new BufferedWriter(escritorDeArquivo);

			buffWrite.write(this.mapaPesquisas.get(codigoPesquisa).getResumo().trim());
			buffWrite.close();
			

		} catch (IOException e) {
			e.printStackTrace();
		}
		}


	/**
	 * Grava resultados de uma Pesquisa em um arquivo de texto
	 * 
	 * @param codigoPesquisa o identificador unico da Pesquisa
	 */
	public void gravarResultados(String codigoPesquisa) {
		Verificador.verificaEntrada(codigoPesquisa, "Pesquisa nao pode ser nula ou vazia.");
		Verificador.existeChave(this.mapaPesquisas, codigoPesquisa, "Pesquisa nao encontrada.");

		try {

			File file = new File("./" + codigoPesquisa + "-Resultados.txt");

			FileWriter escritorDeArquivo = new FileWriter(file.getAbsoluteFile());
			BufferedWriter buffWrite = new BufferedWriter(escritorDeArquivo);

			buffWrite.write("\"" + this.mapaPesquisas.get(codigoPesquisa).getResultados().trim() + "\"");
			buffWrite.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Altera a estrategia de sugestao de proxima atividade
	 * @param estrategia nova estrategia a ser adotada
	 */
	public void configuraEstrategia(String estrategia) {
		Verificador.verificaEntrada(estrategia, "Estrategia nao pode ser nula ou vazia.");
		if (!estrategia.equals("MAIS_ANTIGA") && !estrategia.equals("MAIOR_RISCO") && !estrategia.equals("MAIOR_DURACAO") && !estrategia.equals("MENOS_PENDENCIAS")) {
			throw new IllegalArgumentException("Valor invalido da estrategia");
		} 
		this.estrategia = estrategia;
	}

	/**
	 * Sugere uma atividade de uma pesquisa para ser realizada baseado na estrategia de sugestao atual
	 * @param codigoPesquisa identificador da pesquisa
	 * @return codigo da atividade sugerida
	 */
	public String proximaAtividade(String codigoPesquisa) {
		Verificador.verificaEntrada(codigoPesquisa, "Pesquisa nao pode ser nula ou vazia.");
		Verificador.existeChave(this.mapaPesquisas, codigoPesquisa, "Pesquisa nao encontrada.");
		Verificador.verificaEhAtiva(this.mapaPesquisas, codigoPesquisa, "Pesquisa desativada.");
		if (this.mapaPesquisas.get(codigoPesquisa).contaPendencias() == 0) {
			throw new IllegalArgumentException("Pesquisa sem atividades com pendencias.");
		}
		return this.mapaPesquisas.get(codigoPesquisa).proximaAtividade(estrategia);
	}
}