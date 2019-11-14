package pesquisador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

	/**
	 * Constroi o controlador
	 */
	public PesquisadorController(PesquisaController pesquisaController) {
		this.mapaEmailPesquisador = new HashMap<>();
		this.pesquisaController = pesquisaController;
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
		Verificador.existeChaveString(this.mapaEmailPesquisador, email, "Pesquisador nao encontrado");
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
			((Aluno) this.mapaEmailPesquisador.get(email).getEspecialidade()).setSemestre(Integer.parseInt(novoValor));
		} else if (atributo.equals("IEA")){
			Verificador.verificaEntrada(novoValor, "Campo iea nao pode ser nulo ou vazio.");
			((Aluno) this.mapaEmailPesquisador.get(email).getEspecialidade()).setIea(Double.parseDouble(novoValor));
		} else if (atributo.equals("FORMACAO")){
			Verificador.verificaEntrada(novoValor, "Campo formacao nao pode ser nulo ou vazio.");
			((Professor) this.mapaEmailPesquisador.get(email).getEspecialidade()).setFormacao(novoValor);
		} else if (atributo.equals("UNIDADE")){
			Verificador.verificaEntrada(novoValor, "Campo unidade nao pode ser nulo ou vazio.");
			((Professor) this.mapaEmailPesquisador.get(email).getEspecialidade()).setUnidade(novoValor);
		} else if (atributo.equals("DATA")){
			Verificador.verificaEntrada(novoValor, "Campo data nao pode ser nulo ou vazio.");
			Verificador.verificaData(novoValor, "Atributo data com formato invalido.");
			((Professor) this.mapaEmailPesquisador.get(email).getEspecialidade()).setData(novoValor);
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
		Verificador.verificaEntrada(email, "Campo email nao pode ser nulo ou vazio.");
		Verificador.verificaEmail(email, "Formato de email invalido.");
		Verificador.existeChaveString(this.mapaEmailPesquisador, email, "Pesquisador nao encontrado");
		if (!pesquisadorEhAtivo(email)) {
			throw new IllegalArgumentException("Pesquisador inativo.");
		} 
		if (mapaEmailPesquisador.get(email).getEspecialidade() == null) {
			return this.mapaEmailPesquisador.get(email).toString();
		}
		return this.mapaEmailPesquisador.get(email).toStringEspecialidade();
	}

	/**
	 * Ativa um pesquisador que esta atualmente inativo
	 * 
	 * @param email
	 *            email identificador do pesquisador a ser ativo
	 */
	public void ativaPesquisador(String email) {
		Verificador.verificaEmail(email, "Formato de email invalido.");
		Verificador.existeChaveString(this.mapaEmailPesquisador, email, "Pesquisador nao encontrado");
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
		Verificador.existeChaveString(this.mapaEmailPesquisador, email, "Pesquisador nao encontrado");
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
		Verificador.existeChaveString(this.mapaEmailPesquisador, email, "Pesquisador nao encontrado");
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
	 *            o termo que o usuario deseja pesquisar
	 * @return retorna uma string com todos os resultados encontrados
	 */
	public String procuraPalavra(String palavra) {
		String retorno = "";
		Verificador.verificaEntrada(palavra, "Campo termo nao pode ser nulo ou vazio.");
		List<Pesquisador> listaPesquisadores = new ArrayList<>(this.mapaEmailPesquisador.values());
		for (Pesquisador pesquisador : listaPesquisadores) {
			if (pesquisador.getBiografia().toLowerCase().contains(palavra)) {
				retorno = retorno + pesquisador.getEmail() + ": " + pesquisador.getBiografia() + " | ";
			}
		}
		return retorno;
	}
	private Pesquisador capturaPesquisaNoMapa(String email) {
		return this.mapaEmailPesquisador.get(email);
	}

	public void cadastraEspecialidadeProfessor(String email, String formacao, String unidade, String data) {
		Verificador.verificaEntrada(email, "Campo email nao pode ser nulo ou vazio.");
		Verificador.verificaEntrada(formacao, "Campo formacao nao pode ser nulo ou vazio.");
		Verificador.verificaEntrada(unidade, "Campo unidade nao pode ser nulo ou vazio.");
		Verificador.verificaEntrada(data, "Campo data nao pode ser nulo ou vazio.");
		Verificador.verificaEmail(email, "Atributo email com o formato invalido.");
		Verificador.verificaData(data, "Atributo data com formato invalido.");
		Verificador.existeChave(this.mapaEmailPesquisador, email, "Pesquisadora nao encontrada.");
		if (this.mapaEmailPesquisador.get(email).getFuncao().equals("professor")) {	
			Professor especialidade = new Professor(formacao, unidade, data);
			this.mapaEmailPesquisador.get(email).setEspecialidade(especialidade);
		} else {
			throw new IllegalArgumentException("Pesquisador nao compativel com a especialidade.");
		}
	}

	public void cadastraEspecialidadeAluno(String email, int semestre, double IEA) {
		Verificador.verificaEntrada(email, "Campo email nao pode ser nulo ou vazio.");
		Verificador.verificaEntrada(String.valueOf(semestre), "Semestre nao pode ser nulo ou vazio.");
		Verificador.verificaEntrada(String.valueOf(IEA), "IEA nao pode ser nulo ou vazio.");
		Verificador.verificaIEA(IEA, "Atributo IEA com formato invalido.");
		Verificador.verificaInteiroPositivo(semestre, "Atributo semestre com formato invalido.");
		Verificador.verificaEmail(email, "Atributo email com formato invalido.");
		Verificador.existeChave(this.mapaEmailPesquisador, email, "Pesquisadora nao encontrada.");
		if (this.mapaEmailPesquisador.get(email).getFuncao().equals("estudante")) {		
			this.mapaEmailPesquisador.get(email).setEspecialidade(new Aluno(semestre, IEA));
		}else {
			throw new IllegalArgumentException("Pesquisador nao compativel com a especialidade.");
		}
	}

	public String listaPesquisadores(String tipo) {
		String exibe = "";
		Verificador.verificaEntrada(tipo, "Campo tipo nao pode ser nulo ou vazio.");
		Verificador.verificaTipo(tipo, "Tipo " + tipo + " inexistente.");
		for(Pesquisador p : mapaEmailPesquisador.values()) {
			if (p.getFuncao().toUpperCase().equals(tipo)) {
				exibe += p.toString()+" | ";
			}
		}
		return exibe.substring(0, exibe.length()-3);
	}

	public boolean associaPesquisador(String idPesquisa, String emailPesquisador) {
		Verificador.verificaEntrada(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		Verificador.verificaEntrada(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");
		Verificador.verificaEmail(emailPesquisador, "Atributo email com formato invalido.");
		Verificador.existeChave(pesquisaController.getMapaPesquisas(), idPesquisa, "Pesquisa nao encontrada.");
		Verificador.verificaEhAtiva(pesquisaController.getMapaPesquisas(), idPesquisa, "Pesquisa desativada.");
		
		return this.capturaPesquisaNoMapa(emailPesquisador).associaPesquisador(idPesquisa);
	}

	public boolean desassociaPesquisador(String idPesquisa, String emailPesquisador) {
		Verificador.verificaEntrada(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		Verificador.verificaEntrada(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");
		Verificador.verificaEmail(emailPesquisador, "Atributo email com formato invalido.");
		Verificador.existeChave(pesquisaController.getMapaPesquisas(), idPesquisa, "Pesquisa nao encontrada.");
		Verificador.verificaEhAtiva(pesquisaController.getMapaPesquisas(), idPesquisa, "Pesquisa desativada.");
		
		return this.capturaPesquisaNoMapa(emailPesquisador).desassociaPesquisador(idPesquisa);
	}
		
} 
