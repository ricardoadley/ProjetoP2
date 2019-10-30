package sistema;

import atividades.ControladorAtividade;
import objetivo.ObjetivoController;
import pesquisador.PesquisadorController;
import problema.ProblemaController;
import pesquisa.PesquisaController;

/**
 * Facade do sistema.
 * @author josemng , Ricardo A. S. Sena
 *
 */
public class Facade {
	
	ControladorAtividade controlaAtividade;
	ProblemaController problemaController;
	ObjetivoController objetivoController;
	PesquisadorController pesquisadorController;
	PesquisaController pesquisaController;
	
	public Facade() {
		this.controlaAtividade  = new ControladorAtividade();
		this.problemaController = new ProblemaController();
		this.objetivoController = new ObjetivoController();
		this.pesquisadorController = new PesquisadorController();
		this.pesquisaController = new PesquisaController();
	}

	public String cadastraProblema(String descricao, String viabilidade) {
		return this.problemaController.cadastraProblema(descricao, viabilidade);
	}

	public void apagarProblema(String codigo) {
		this.problemaController.apagarProblema(codigo);
	}
	
	public String exibeProblema(String codigo){
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
	
	// Vinícius (US2)
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
	
	//Beatriz (US1)
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
}
