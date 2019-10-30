package pesquisador;

import java.util.HashMap;
import java.util.Map;

import sistema.Verificador;

/**
 * Controlador de objetos do tipo "Pesquisador", usado para armazena-los e manipula-los
 * @author Vinicius M. V. Varjao
 *
 */
public class PesquisadorController {

	/**
	 * Mapa para armazenar os pesquisadores, com chaves do tipo String e valores do tipo Pesquisador
	 */
	private Map<String, Pesquisador> mapaEmailPesquisador;
	/**
	 * Instancia da classe lancadora de exceçoes
	 */
	private Verificador verificador;
	
	/**
	 * Constroi o controlador
	 */
	public PesquisadorController() {
		this.mapaEmailPesquisador = new HashMap<>();
		this.verificador = new Verificador();
	}

	/**
	 * Cadastra um pesquisador no controlador
	 * @param nome o nome do pesquisador que sera cadastrado
	 * @param funcao a funcao do pesquisador que sera cadastrado
	 * @param biografia a biografia do pesquisador que sera cadastrado
	 * @param email o email do pesquisador que sera cadastrado
	 * @param fotoURL a URL da foto do pesquisador que sera cadastrado
	 */
	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoURL) {
		this.mapaEmailPesquisador.put(email,  new Pesquisador(nome, funcao, biografia, email, fotoURL));
		
	}
	
	/**
	 * Altera um determinado atributo de um Pesquisador já salvo no sistema
	 * @param email email identificador do pesquisador a ser alterado
	 * @param atributo atributo a ser alterado
	 * @param novoValor novo valo a ser atribuido ao atribudo a ser alterado
	 */
	public void alteraPesquisador(String email, String atributo, String novoValor) {
		verificador.verificaEmail(email, "Formato de email invalido.");
		verificador.existeChave(this.mapaEmailPesquisador, email, "Pesquisador nao encontrado");
		if (!pesquisadorEhAtivo(email)) {
			throw new IllegalArgumentException("Pesquisador inativo.");
		}
		if (atributo.equals("nome")) {
			verificador.verificaEntrada(novoValor, "Campo nome nao pode ser nulo ou vazio.");
			this.mapaEmailPesquisador.get(email).setNome(novoValor);
			return;
		}
		if (atributo.equals("funcao")) {
			verificador.verificaEntrada(novoValor, "Campo funcao nao pode ser nulo ou vazio.");
			this.mapaEmailPesquisador.get(email).setFuncao(novoValor);
		}
		if (atributo.equals("biografia")) {
			verificador.verificaEntrada(novoValor, "Campo biografia nao pode ser nulo ou vazio.");
			this.mapaEmailPesquisador.get(email).setBiografia(novoValor);
		}
		if (atributo.equals("email")) {
			verificador.verificaEntrada(novoValor, "Campo email nao pode ser nulo ou vazio.");
			verificador.verificaEmail(novoValor, "Formato de email invalido.");
			this.mapaEmailPesquisador.put(novoValor, this.mapaEmailPesquisador.get(email));
			this.mapaEmailPesquisador.get(novoValor).setEmail(novoValor);
			this.mapaEmailPesquisador.remove(email);
		}
		if (atributo.equals("fotoURL")) {
			verificador.verificaEntrada(novoValor, "Campo fotoURL nao pode ser nulo ou vazio.");
			verificador.verificaFotoURL(novoValor, "Formato de foto invalido.");
			this.mapaEmailPesquisador.get(email).setFotoURL(novoValor);
		}
	}
	
	/**
	 * Retorna a representacao em texto de um Pesquisador ja cadastrado no controlador
	 * @param email email identificador do pesquisador a ser exibido
	 * @return representacao em texto de um Pesquisador ja cadastrado 
	 */
	public String exibePesquisador(String email) {
		verificador.verificaEmail(email, "Formato de email invalido.");
		verificador.existeChave(this.mapaEmailPesquisador, email, "Pesquisador nao encontrado");
		if (!pesquisadorEhAtivo(email)) {
			throw new IllegalArgumentException("Pesquisador inativo.");
		}
		return this.mapaEmailPesquisador.get(email).toString();
	}
	
	/**
	 * Ativa um pesquisador que está atualmente inativo
	 * @param email email identificador do pesquisador a ser ativo
	 */
	public void ativaPesquisador(String email) {
		verificador.verificaEmail(email, "Formato de email invalido.");
		verificador.existeChave(this.mapaEmailPesquisador, email, "Pesquisador nao encontrado");
		if (pesquisadorEhAtivo(email)) {
			throw new IllegalArgumentException("Pesquisador ja ativado.");
		}
		this.mapaEmailPesquisador.get(email).setAtividade("Ativo");
	}
	
	/**
	 * Desativa um pesquisador atualmente ativo
	 * @param email email identificador do pesquisador a ser desativado
	 */
	public void desativaPesquisador(String email) {
		verificador.verificaEmail(email, "Formato de email invalido.");
		verificador.existeChave(this.mapaEmailPesquisador, email, "Pesquisador nao encontrado");
		if (!pesquisadorEhAtivo(email)) {
			throw new IllegalArgumentException("Pesquisador inativo.");
		}
		this.mapaEmailPesquisador.get(email).setAtividade("Inativo");
	}
	
	/**
	 * Verifica se um pesquisador especifico esta ativo e retorna o valor booleano referente a verificacao
	 * @param email email do pesquisador a ser verificado
	 * @return valor booleano referente a atividade do pesquisador
	 */
	public boolean pesquisadorEhAtivo(String email) {
		verificador.verificaEntrada(email, "Email nao pode ser vazio ou nulo.");
		verificador.verificaEmail(email, "Formato de email invalido.");
		verificador.existeChave(this.mapaEmailPesquisador, email, "Pesquisador nao encontrado");
		if (this.mapaEmailPesquisador.get(email).getAtividade().equals("Ativo")) {
			return true;
		}
		return false;
	}
}
