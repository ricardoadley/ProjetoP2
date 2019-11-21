package sistema;

import java.io.IOException;

import atividades.ControladorAtividade;
import objetivo.ObjetivoController;
import pesquisador.PesquisadorController;
import problema.ProblemaController;
import pesquisa.PesquisaController;

/**
 * Facade do sistema.
 * 
 * @author Jose Matheus do N.Gama , Ricardo A. S. Sena, Vinicius M. V. Varjao,
 *         Beatriz Truta
 *
 */
public class Facade {

	private ControladorAtividade atividadeController;
	private ObjetivoController objetivoController;
	private ProblemaController problemaController;
	private PesquisadorController pesquisadorController;
	private PesquisaController pesquisaController;
	private BuscadorPalavra buscador;

	public Facade() throws IOException {
		this.atividadeController = new ControladorAtividade();
		this.pesquisadorController = new PesquisadorController();
		this.objetivoController = new ObjetivoController();
		this.problemaController = new ProblemaController();
		this.pesquisaController = new PesquisaController(objetivoController, problemaController, pesquisadorController,
				atividadeController);
		this.buscador = new BuscadorPalavra(atividadeController, pesquisaController, pesquisadorController);
	}

	// Jose Matheus (US3)
	public String cadastraProblema(String descricao, String viabilidade) {
		return this.problemaController.cadastraProblema(descricao, viabilidade);
	}

	public void apagarProblema(String codigo) {
		this.problemaController.apagarProblema(codigo);
	}

	public String exibeProblema(String codigo) {
		return this.problemaController.exibeProblema(codigo);
	}

	public void cadastraObjetivo(String tipo, String descricao, String aderencia, String viabilidade) {
		this.objetivoController.cadastraObjetivo(tipo, descricao, aderencia, viabilidade);
	}

	public void apagarObjetivo(String codigo) {
		this.objetivoController.apagarObjetivo(codigo);
	}

	public String exibeObjetivo(String codigo) {
		return this.objetivoController.exibeObjetivo(codigo);
	}

	// Ricardo (US4)
	public String cadastraAtividade(String descricao, String nivelRisco, String descricaoRisco) {
		return atividadeController.cadastraAtividade(descricao, nivelRisco, descricaoRisco);
	}

	public void apagaAtividade(String codigo) {
		atividadeController.apagaAtividade(codigo);
	}

	public void cadastraItem(String codigo, String item) {
		atividadeController.cadastraItem(codigo, item);
	}

	public String exibeAtividade(String codigo) {
		return atividadeController.exibeAtividade(codigo);

	}

	public int contaItensPendentes(String codigo) {
		return atividadeController.contaItensPendentes(codigo);
	}

	public int contaItensRealizados(String codigo) {
		return atividadeController.contaItensRealizados(codigo);
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

	// Beatriz (US1)
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
		return buscador.retornaEncontradasNumeroResultado(termo, numeroResultado);
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

	// Ana Beatriz Truta (US6)

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
		return pesquisaController.associaPesquisador(idPesquisa, emailPesquisador);
	}

	public boolean desassociaPesquisador(String idPesquisa, String emailPesquisador) {
		return pesquisaController.desassociaPesquisador(idPesquisa, emailPesquisador);
	}

	// Vinicius (US7)
	public boolean associaAtividade(String codigoPesquisa, String codigoAtividade) {
		return this.pesquisaController.associaAtividade(codigoPesquisa, codigoAtividade);
	}

	public boolean desassociaAtividade(String codigoPesquisa, String codigoAtividade) {
		return this.pesquisaController.desassociaAtividade(codigoPesquisa, codigoAtividade);
	}

	public void executaAtividade(String codigoAtividade, int item, int duracao) {
		this.atividadeController.executaAtividade(codigoAtividade, item, duracao);
	}

	public int cadastraResultado(String codigoAtividade, String resultado) {
		return this.atividadeController.cadastraResultado(codigoAtividade, resultado);
	}

	public boolean removeResultado(String codigoAtividade, int numeroResultado) {
		return this.atividadeController.removeResultado(codigoAtividade, numeroResultado);
	}

	public String listaResultados(String codigoAtividade) {
		return this.atividadeController.listaResultados(codigoAtividade);
	}

	public int getDuracao(String codigoAtividade) {
		return this.atividadeController.getDuracao(codigoAtividade);
	}

	// Vinicius (US10)

	public void configuraEstrategia(String estrategia) {
		this.pesquisaController.configuraEstrategia(estrategia);
	}

	public String proximaAtividade(String codigoPesquisa) {
		return this.pesquisaController.proximaAtividade(codigoPesquisa);
	}

	// Matheus (US11)

	public void gravarResumo(String codigoPesquisa) {
		this.pesquisaController.gravarResumo(codigoPesquisa);
	}

	public void gravarResultados(String codigoPesquisa) {
		this.pesquisaController.gravarResultados(codigoPesquisa);
	}

	public void salvar() {
		pesquisaController.salvar();
		pesquisadorController.salvar();
		problemaController.salvar();
		atividadeController.salvar();
		objetivoController.salvar();
	}

	public void carregar() {
		pesquisaController.retorna();
		pesquisadorController.retorna();
		problemaController.retorna();
		atividadeController.retorna();
		objetivoController.retorna();
	}

	public void defineProximaAtividade(String idPrecedente, String idSubsquente) {
		this.atividadeController.defineProximaAtividade(idPrecedente, idSubsquente);
	}

	public void tiraProximaAtividade(String idPrecedente) {
		this.atividadeController.tiraProximaAtividade(idPrecedente);
	}

	public int contaProximos(String idPrecedente) {
		return this.atividadeController.contaProximos(idPrecedente);
	}

	public String pegaProximo(String idAtividade, int enesimaAtividade) {
		return this.atividadeController.pegaProximo(idAtividade, enesimaAtividade);
	}

	public String pegaMaiorRiscoAtividades(String idAtividade) {
		return this.atividadeController.pegaMaiorRiscoAtividades(idAtividade);
	}
}
