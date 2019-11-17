package sistema;

import atividades.ControladorAtividade;
import pesquisador.PesquisadorController;
import pesquisa.PesquisaController;

/**
 * Facade do sistema.
 * 
 * @author Jose Matheus do N.Gama , Ricardo A. S. Sena, Vinicius M. V. Varjao,
 *         Ana Beatriz da S. Truta
 *
 */
public class Facade {

	private ControladorAtividade controlaAtividade;
	private PesquisadorController pesquisadorController;
	private PesquisaController pesquisaController;
	private BuscadorPalavra buscador;

	public Facade() {
		this.controlaAtividade = new ControladorAtividade();
		this.pesquisaController = new PesquisaController(controlaAtividade);
		this.pesquisadorController = new PesquisadorController(pesquisaController);
		this.buscador = new BuscadorPalavra(controlaAtividade,pesquisaController,pesquisadorController);
	}

	// Jose Matheus (US3)
	public String cadastraProblema(String descricao, String viabilidade) {
		return this.pesquisaController.cadastraProblema(descricao, viabilidade);
	}

	public void apagarProblema(String codigo) {
		this.pesquisaController.apagarProblema(codigo);
	}

	public String exibeProblema(String codigo) {
		return this.pesquisaController.exibeProblema(codigo);
	}

	public void cadastraObjetivo(String tipo, String descricao, String aderencia, String viabilidade) {
		this.pesquisaController.cadastraObjetivo(tipo, descricao, aderencia, viabilidade);
	}

	public void apagarObjetivo(String codigo) {
		this.pesquisaController.apagarObjetivo(codigo);
	}

	public String exibeObjetivo(String codigo) {
		return this.pesquisaController.exibeObjetivo(codigo);
	}

	// Ricardo (US4)
	public String cadastraAtividade(String descricao, String nivelRisco, String descricaoRisco) {
		return controlaAtividade.cadastraAtividade(descricao, nivelRisco, descricaoRisco);
	}

	public void apagaAtividade(String codigo) {
		controlaAtividade.apagaAtividade(codigo);
	}

	public void cadastraItem(String codigo, String item) {
		controlaAtividade.cadastraItem(codigo, item);
	}

	public String exibeAtividade(String codigo) {
		return controlaAtividade.exibeAtividade(codigo);

	}

	public int contaItensPendentes(String codigo) {
		return controlaAtividade.contaItensPendentes(codigo);
	}

	public int contaItensRealizados(String codigo) {
		return controlaAtividade.contaItensRealizados(codigo);
	}

	// Vinicius (US2)
	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoLink) {
		pesquisadorController.cadastraPesquisador(nome, funcao, biografia, email, fotoLink);
	}

	public void alteraPesquisador(String email, String atributo, String novoValor) {
		pesquisadorController.alteraPesquisador(email, atributo, novoValor);
	}

	public String exibePesquisador(String email) {
		return pesquisadorController.exibePesquisador(email);
	}

	public void ativaPesquisador(String email) {
		pesquisadorController.ativaPesquisador(email);
	}

	public void desativaPesquisador(String email) {
		pesquisadorController.desativaPesquisador(email);
	}

	public boolean pesquisadorEhAtivo(String email) {
		return pesquisadorController.pesquisadorEhAtivo(email);
	}

	// Ana Beatriz Truta (US1)
	public String cadastraPesquisa(String descricao, String interesse) {
		return pesquisaController.cadastraPesquisa(descricao, interesse);
	}

	public void alteraPesquisa(String codigo, String alterar, String mudanca) {
		pesquisaController.alteraPesquisa(codigo, alterar, mudanca);
	}

	public String exibePesquisa(String codigo) {
		return pesquisaController.exibePesquisa(codigo);
	}

	public void ativaPesquisa(String codigo) {
		pesquisaController.ativaPesquisa(codigo);
	}

	public void encerraPesquisa(String codigo, String motivo) {
		pesquisaController.encerraPesquisa(codigo, motivo);
	}

	public boolean pesquisaEhAtiva(String codigo) {
		return pesquisaController.pesquisaEhAtiva(codigo);
	}

	public String listaPesquisas(String ordem) {
		return pesquisaController.listaPesquisas(ordem);
	}
	public String busca(String termo) {
		return buscador.retornaEncontradas(termo);
	}

	public String busca(String termo, int numeroResultado) {
		return buscador.retornaEncontradasNumeroResultado(termo,numeroResultado);
	}
	public int contaResultadosBusca(String termo) {
		return buscador.retornaQuantidadeDeResultados(termo);

	}

	// Jose Matheus (US5)

	public boolean associaProblema(String idPesquisa, String idProblema) {
		return this.pesquisaController.associaProblema(idPesquisa, idProblema);
	}

	public boolean desassociaProblema(String idPesquisa) {
		return this.pesquisaController.desassociaProblema(idPesquisa);
	}

	public boolean associaObjetivo(String idPesquisa, String idObjetivo) {
		return this.pesquisaController.associaObjetivo(idPesquisa, idObjetivo);
	}

	public boolean desassociaObjetivo(String idPesquisa, String idObjetivo) {
		return this.pesquisaController.desassociaObjetivo(idPesquisa, idObjetivo);
	}
	
	//Ana Beatriz Truta (US6)

	public void cadastraEspecialidadeProfessor(String email, String formacao, String unidade, String data) {
		pesquisadorController.cadastraEspecialidadeProfessor(email, formacao, unidade, data);
		}

	public void cadastraEspecialidadeAluno(String email, int semestre, double IEA) {
		pesquisadorController.cadastraEspecialidadeAluno(email, semestre, IEA);
	}

	public String listaPesquisadores(String tipo) {
		return pesquisadorController.listaPesquisadores(tipo);
	}

	public boolean associaPesquisador(String idPesquisa, String emailPesquisador) {
		return pesquisadorController.associaPesquisador(idPesquisa, emailPesquisador);
	}

	public boolean desassociaPesquisador(String idPesquisa, String emailPesquisador) {
		return pesquisadorController.desassociaPesquisador(idPesquisa, emailPesquisador);
	}	
	
	//Vinicius (US7)
	public boolean associaAtividade(String codigoPesquisa, String codigoAtividade) {
		return this.pesquisaController.associaAtividade(codigoPesquisa, codigoAtividade);
	}

	public boolean desassociaAtividade(String codigoPesquisa, String codigoAtividade) {
		return this.pesquisaController.desassociaAtividade(codigoPesquisa, codigoAtividade);
	}

	public void executaAtividade(String codigoAtividade, int item, int duracao) {	
		this.controlaAtividade.executaAtividade(codigoAtividade, item, duracao);
	}

	public int cadastraResultado(String codigoAtividade, String resultado) {
		return this.controlaAtividade.cadastraResultado(codigoAtividade, resultado);
	}

	public boolean removeResultado(String codigoAtividade, int numeroResultado) {
		return this.controlaAtividade.removeResultado(codigoAtividade, numeroResultado);
	}

	public String listaResultados(String codigoAtividade) {
		return this.controlaAtividade.listaResultados(codigoAtividade);
	}

	public int getDuracao(String codigoAtividade) {
		return this.controlaAtividade.getDuracao(codigoAtividade);
	}
}
