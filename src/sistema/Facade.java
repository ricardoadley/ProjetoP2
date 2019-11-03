package sistema;

import atividades.ControladorAtividade;
//import objetivo.ObjetivoController;
import pesquisador.PesquisadorController;
//import problema.ProblemaController;
import pesquisa.PesquisaController;

/**
 * Facade do sistema.
 * 
 * @author josemng , Ricardo A. S. Sena, Vinicius M. V. Varjao, Beatriz Truta
 *
 */
public class Facade {

	private ControladorAtividade controlaAtividade;
	private PesquisadorController pesquisadorController;
	private PesquisaController pesquisaController;
	private BuscadorPalavra buscador;

	public Facade() {
		this.controlaAtividade = new ControladorAtividade();
		this.pesquisadorController = new PesquisadorController();
		this.pesquisaController = new PesquisaController();
		this.buscador = new BuscadorPalavra();
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

	public String busca(String termo) {
		pesquisaController.ProcurarPalavraPesquisa(termo); 
		pesquisadorController.ProcurarPalavra(termo);
		pesquisaController.procurarPalavraProblema(termo); 
		pesquisaController.procurarPalavraObjetivo(termo);
		controlaAtividade.ProcurarPalavra(termo);
		return buscador.retornaEncontradas();
	}
	public String busca(String termo, int numeroResultado) {
		pesquisaController.ProcurarPalavraPesquisa(termo); 
		pesquisadorController.ProcurarPalavra(termo);
		pesquisaController.procurarPalavraProblema(termo); 
		pesquisaController.procurarPalavraObjetivo(termo);
		controlaAtividade.ProcurarPalavra(termo);
		return buscador.retornaEncontradasNumeroResultado(numeroResultado);
	}
	public int contaResultadosBusca(String termo) {
		pesquisaController.ProcurarPalavraPesquisa(termo); 
		pesquisadorController.ProcurarPalavra(termo);
		pesquisaController.procurarPalavraProblema(termo); 
		pesquisaController.procurarPalavraObjetivo(termo);
		controlaAtividade.ProcurarPalavra(termo);
		return buscador.retornaQuantidadeDeResultados();
				
	}

	//Jose Matheus (US5)
	
	public String associaProblema(String idPesquisa, String idProblema) {
		return this.pesquisaController.associaProblema(idPesquisa, idProblema);
	}
	
	public String desassociaProblema(String idPesquisa, String idProblema) {
		return this.pesquisaController.desassociaProblema(idPesquisa, idProblema);
	}
	
	public String associaObjetivo(String idPesquisa, String idObjetivo) {
		return this.pesquisaController.associaObjetivo(idPesquisa, idObjetivo);
	}
	
	public String desassociaObjetivo(String idPesquisa, String idObjetivo) {
		return this.pesquisaController.desassociaObjetivo(idPesquisa, idObjetivo);
	}
	
}
