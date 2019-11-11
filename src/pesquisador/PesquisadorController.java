package pesquisador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sistema.BuscadorPalavra;
import sistema.Verificador;
import pesquisa.PesquisaController;

/**
 * Controlador de objetos do tipo "Pesquisador", usado para armazena-los e
 * manipula-los
 * 
 * @author Vinicius M. V. Varjao
 * 
 */
public class PesquisadorController {

	/**
	 * Mapa para armazenar os pesquisadores, com chaves do tipo String e valores do
	 * tipo Pesquisador
	 */
	private Map<String, Pesquisador> mapaEmailPesquisador;
	private PesquisaController pesquisaController;
	private Funcao funcao;
	Verificador verificador = new Verificador();

	/**
	 * Constroi o controlador
	 */
	public PesquisadorController() {
		this.mapaEmailPesquisador = new HashMap<>();
		this.pesquisaController = new PesquisaController();
	}

	/**
	 * Cadastra um pesquisador no controlador
	 * 
	 * @param nome
	 *            o nome do pesquisador que sera cadastrado
	 * @param funcao
	 *            a funcao do pesquisador que sera cadastrado
	 * @param biografia
	 *            a biografia do pesquisador que sera cadastrado
	 * @param email
	 *            o email do pesquisador que sera cadastrado
	 * @param fotoURL
	 *            a URL da foto do pesquisador que sera cadastrado
	 */
	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoURL) {
		Verificador.verificaEntrada(nome, "Campo nome nao pode ser nulo ou vazio.");
		Verificador.verificaEntrada(funcao, "Campo funcao nao pode ser nulo ou vazio.");
		Verificador.verificaEntrada(biografia, "Campo biografia nao pode ser nulo ou vazio.");
		Verificador.verificaEntrada(email, "Campo email nao pode ser nulo ou vazio.");
		Verificador.verificaEntrada(fotoURL, "Campo fotoURL nao pode ser nulo ou vazio.");
		Verificador.verificaEmail(email, "Formato de email invalido.");
		Verificador.verificaFotoURL(fotoURL, "Formato de foto invalido.");
		this.mapaEmailPesquisador.put(email, new Pesquisador(nome, funcao, biografia, email, fotoURL));

	}

	/**
	 * Altera um determinado atributo de um Pesquisador ja salvo no sistema
	 * 
	 * @param email
	 *            email identificador do pesquisador a ser alterado
	 * @param atributo
	 *            atributo a ser alterado
	 * @param novoValor
	 *            novo valo a ser atribuido ao atribudo a ser alterado
	 */
	public void alteraPesquisador(String email, String atributo, String novoValor) {
		Verificador.verificaEmail(email, "Formato de email invalido.");
		Verificador.verificaEntrada(atributo, "Atributo nao pode ser vazio ou nulo.");
		Verificador.existeChave(this.mapaEmailPesquisador, email, "Pesquisador nao encontrado");
		if (!pesquisadorEhAtivo(email)) {
			throw new IllegalArgumentException("Pesquisador inativo.");
		}
		if (atributo.equals("NOME")) {
			Verificador.verificaEntrada(novoValor, "Campo nome nao pode ser nulo ou vazio.");
			this.mapaEmailPesquisador.get(email).setNome(novoValor);
		} else if (atributo.equals("FUNCAO")) {
			Verificador.verificaEntrada(novoValor, "Campo funcao nao pode ser nulo ou vazio.");
			this.mapaEmailPesquisador.get(email).setFuncao(novoValor);
		} else if (atributo.equals("BIOGRAFIA")) {
			Verificador.verificaEntrada(novoValor, "Campo biografia nao pode ser nulo ou vazio.");
			this.mapaEmailPesquisador.get(email).setBiografia(novoValor);
		} else if (atributo.equals("EMAIL")) {
			Verificador.verificaEntrada(novoValor, "Campo email nao pode ser nulo ou vazio.");
			Verificador.verificaEmail(novoValor, "Formato de email invalido.");
			this.mapaEmailPesquisador.put(novoValor, this.mapaEmailPesquisador.get(email));
			this.mapaEmailPesquisador.get(novoValor).setEmail(novoValor);
			this.mapaEmailPesquisador.remove(email);
		} else if (atributo.equals("FOTO")) {
			Verificador.verificaEntrada(novoValor, "Campo fotoURL nao pode ser nulo ou vazio.");
			Verificador.verificaFotoURL(novoValor, "Formato de foto invalido.");
			this.mapaEmailPesquisador.get(email).setFotoURL(novoValor);
		} else if (atributo.equals("SEMESTRE")){
			Verificador.verificaEntrada(novoValor, "Campo semestre nao pode ser nulo ou vazio.");
			Verificador.verificaSemestre(novoValor, "Atributo semestre com formato invalido.");
		} else if (atributo.equals("IEA")){
			Verificador.verificaEntrada(novoValor, "Campo iea nao pode ser nulo ou vazio.");
		} else if (atributo.equals("FORMACAO")){
			Verificador.verificaEntrada(novoValor, "Campo formacao nao pode ser nulo ou vazio.");
		} else if (atributo.equals("UNIDADE")){
			Verificador.verificaEntrada(novoValor, "Campo unidade nao pode ser nulo ou vazio.");
		} else if (atributo.equals("DATA")){
			Verificador.verificaEntrada(novoValor, "Campo data nao pode ser nulo ou vazio.");
			Verificador.verificaData(novoValor, "Atributo data ncom formato invalido.");
		}else {
			throw new IllegalArgumentException("Atributo invalido.");
		}
	}

	/**
	 * Retorna a representacao em texto de um Pesquisador ja cadastrado no
	 * controlador
	 * 
	 * @param email
	 *            email identificador do pesquisador a ser exibido
	 * @return representacao em texto de um Pesquisador ja cadastrado
	 */
	public String exibePesquisador(String email) {
		Verificador.verificaEmail(email, "Formato de email invalido.");
		Verificador.existeChave(this.mapaEmailPesquisador, email, "Pesquisador nao encontrado");
		if (!pesquisadorEhAtivo(email)) {
			throw new IllegalArgumentException("Pesquisador inativo.");
		}
		return this.mapaEmailPesquisador.get(email).toString();
		
	}

	/**
	 * Ativa um pesquisador que esta atualmente inativo
	 * 
	 * @param email
	 *            email identificador do pesquisador a ser ativo
	 */
	public void ativaPesquisador(String email) {
		Verificador.verificaEmail(email, "Formato de email invalido.");
		Verificador.existeChave(this.mapaEmailPesquisador, email, "Pesquisador nao encontrado");
		if (pesquisadorEhAtivo(email)) {
			throw new IllegalArgumentException("Pesquisador ja ativado.");
		}
		this.mapaEmailPesquisador.get(email).setAtividade("Ativo");
	}

	/**
	 * Desativa um pesquisador atualmente ativo
	 * 
	 * @param email
	 *            email identificador do pesquisador a ser desativado
	 */
	public void desativaPesquisador(String email) {
		Verificador.verificaEmail(email, "Formato de email invalido.");
		Verificador.existeChave(this.mapaEmailPesquisador, email, "Pesquisador nao encontrado");
		if (!pesquisadorEhAtivo(email)) {
			throw new IllegalArgumentException("Pesquisador inativo.");
		}
		this.mapaEmailPesquisador.get(email).setAtividade("Inativo");
	}

	/**
	 * Verifica se um pesquisador especifico esta ativo e retorna o valor booleano
	 * referente a verificacao
	 * 
	 * @param email
	 *            email do pesquisador a ser verificado
	 * @return valor booleano referente a atividade do pesquisador
	 */
	public boolean pesquisadorEhAtivo(String email) {
		Verificador.verificaEntrada(email, "Email nao pode ser vazio ou nulo.");
		Verificador.verificaEmail(email, "Formato de email invalido.");
		Verificador.existeChave(this.mapaEmailPesquisador, email, "Pesquisador nao encontrado");
		if (this.mapaEmailPesquisador.get(email).getAtividade().equals("Ativo")) {
			return true;
		}
		return false;
	}

	/**
	 * Procura, nos dados da entidade Pesquisador, por um termo informado pelo
	 * usuario
	 * 
	 * @param palavra,
	 *            o termo, informado pelo usuario, que sera pesquisado nos dados da
	 *            entidade
	 */
	public void ProcurarPalavra(String palavra) {
		Verificador.verificaEntrada(palavra, "Campo termo nao pode ser nulo ou vazio.");
		List<Pesquisador> listaPesquisadores = new ArrayList<>(this.mapaEmailPesquisador.values());
		for (Pesquisador pesquisador : listaPesquisadores) {
			BuscadorPalavra.adicionaEncontrado(BuscadorPalavra.procuraPalavraEmPesquisador(palavra,
					pesquisador.getBiografia(), pesquisador.getEmail()));
		}
	}
		
	public void cadastraEspecialidadeProfessor(String email, String formacao, String unidade, String data) {
		Verificador.verificaEntrada(email, "Email nao pode ser vazio ou nulo.");
		Verificador.verificaEntrada(formacao, "Formacao nao pode ser vazio ou nulo.");
		Verificador.verificaEntrada(unidade, "Unidade nao pode ser vazio ou nulo.");
		Verificador.verificaEntrada(data, "Data nao pode ser vazio ou nulo.");
		this.mapaEmailPesquisador.put(email, (Pesquisador) (this.funcao = new Professor(formacao, unidade, data)));
	}
	
	public void cadastraEspecialidadeAluno(String email, int semestre, double IEA) {
		Verificador.verificaEntrada(email, "Email nao pode ser vazio ou nulo.");
		Verificador.verificaEntrada(String.valueOf(semestre), "Semestre nao pode ser vazio ou nulo.");
		Verificador.verificaEntrada(String.valueOf(IEA), "IEA nao pode ser vazio ou nulo.");
		this.mapaEmailPesquisador.put(email, (Pesquisador) (this.funcao = new Aluno(semestre, IEA)));
	}
	 
	private String listaPesquisadores(String tipo) {
		return "";
	}
	
	@SuppressWarnings("static-access")
	public boolean associaProblema(String idPesquisa, String emailPesquisador) {

		verificador.verificaEntrada(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		verificador.verificaEntrada(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");
		verificador.existeChave(pesquisaController.mapaPesquisas, idPesquisa, "Pesquisa nao encontrada.");
		verificador.verificaEhAtiva(pesquisaController.mapaPesquisas, idPesquisa, "Pesquisa desativada.");

		if (this.pesquisaController.getPesquisa(idPesquisa).equals(this.mapaEmailPesquisador.get(emailPesquisador).contemPesquisa())) {
			return false;
		}

		if (this.mapaEmailPesquisador.get(emailPesquisador).contemPesquisa()) {
			throw new IllegalArgumentException("Pesquisador ja associado a uma pesquisa.");
		}

		if (!this.mapaEmailPesquisador.get(emailPesquisador).ehAtivo()) {
			throw new IllegalArgumentException("Pesquisador inativo.");
		}

		this.mapaEmailPesquisador.get(emailPesquisador).setPesquisa(this.pesquisaController.getPesquisa(idPesquisa));
		return true;

	}
	
	@SuppressWarnings("static-access")
	public boolean desassociaProblema(String idPesquisa, String emailPesquisador) {

		verificador.verificaEntrada(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");
		verificador.verificaEntrada(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		verificador.existeChave(pesquisaController.mapaPesquisas, idPesquisa, "Pesquisa nao encontrada.");
		verificador.verificaEhAtiva(pesquisaController.mapaPesquisas, idPesquisa, "Pesquisa desativada.");

		if (!this.mapaEmailPesquisador.get(emailPesquisador).contemPesquisa()) {
			return false;
		}

		this.mapaEmailPesquisador.get(emailPesquisador).setPesquisa(null);
		return true;

	}

}
