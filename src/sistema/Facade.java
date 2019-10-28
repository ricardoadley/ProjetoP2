package sistema;

import atividades.ControladorAtividade;
import objetivo.ObjetivoController;
import problema.ProblemaController;

/**
 * Facade do sistema.
 * @author josemng , Ricardo A. S. Sena
 *
 */
public class Facade {
	
	ControladorAtividade controlaAtividade;
	ProblemaController problemaController;
	ObjetivoController objetivoController;

	public Facade() {
		this.controlaAtividade  = new ControladorAtividade();
		this.problemaController = new ProblemaController();
		this.objetivoController = new ObjetivoController();
	}

	public void cadastraProblema(String descricao, String viabilidade) {
		this.problemaController.cadastraProblema(descricao, viabilidade);
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
	
}
