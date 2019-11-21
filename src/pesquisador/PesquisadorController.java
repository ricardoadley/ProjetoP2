package pesquisador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import sistema.SalvaSistema;
import sistema.Verificador;


/**
 * Controlador de objetos do tipo "Pesquisador", usado para armazena-los e
 * manipula-los
 * 
 * @author Vinicius M. V. Varjao, Ana Beatriz da S. Truta.
 * 
 */
public class PesquisadorController {

	/**
	 * Mapa para armazenar os pesquisadores, com chaves do tipo String e valores do
	 * tipo Pesquisador
	 */
	private Map<String, Pesquisador> mapaPesquisadores;

	/**
	 * Ordem na qual o Pesquisador foi cadastrado.
	 */
	public int ordemCadastro;

	/**
	 * Constroi o controlador
	 */
	public PesquisadorController() {
		this.mapaPesquisadores = new HashMap<>();
		this.ordemCadastro = 1;
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
		this.mapaPesquisadores.put(email, new Pesquisador(nome, funcao, biografia, email, fotoURL));
		this.ordemCadastro++;
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
		Verificador.existeChaveString(this.mapaPesquisadores, email, "Pesquisador nao encontrado");
		if (!pesquisadorEhAtivo(email)) {
			throw new IllegalArgumentException("Pesquisador inativo.");
		}
		if (atributo.equals("NOME")) {
			Verificador.verificaEntrada(novoValor, "Campo nome nao pode ser nulo ou vazio.");
			this.mapaPesquisadores.get(email).setNome(novoValor);
		} else if (atributo.equals("FUNCAO")) {
			Verificador.verificaEntrada(novoValor, "Campo funcao nao pode ser nulo ou vazio.");
			this.mapaPesquisadores.get(email).setFuncao(novoValor);
		} else if (atributo.equals("BIOGRAFIA")) {
			Verificador.verificaEntrada(novoValor, "Campo biografia nao pode ser nulo ou vazio.");
			this.mapaPesquisadores.get(email).setBiografia(novoValor);
		} else if (atributo.equals("EMAIL")) {
			Verificador.verificaEntrada(novoValor, "Campo email nao pode ser nulo ou vazio.");
			Verificador.verificaEmail(novoValor, "Formato de email invalido.");
			this.mapaPesquisadores.put(novoValor, this.mapaPesquisadores.get(email));
			this.mapaPesquisadores.get(novoValor).setEmail(novoValor);
			this.mapaPesquisadores.remove(email);
		} else if (atributo.equals("FOTO")) {
			Verificador.verificaEntrada(novoValor, "Campo fotoURL nao pode ser nulo ou vazio.");
			Verificador.verificaFotoURL(novoValor, "Formato de foto invalido.");
			this.mapaPesquisadores.get(email).setFotoURL(novoValor);
		} else if (atributo.equals("SEMESTRE")) {
			Verificador.verificaEntrada(novoValor, "Campo semestre nao pode ser nulo ou vazio.");
			Verificador.verificaSemestre(novoValor, "Atributo semestre com formato invalido.");
			((Aluno) this.mapaPesquisadores.get(email).getEspecialidade()).setSemestre(Integer.parseInt(novoValor));
		} else if (atributo.equals("IEA")) {
			Verificador.verificaEntrada(novoValor, "Campo iea nao pode ser nulo ou vazio.");
			((Aluno) this.mapaPesquisadores.get(email).getEspecialidade()).setIea(Double.parseDouble(novoValor));
		} else if (atributo.equals("FORMACAO")) {
			Verificador.verificaEntrada(novoValor, "Campo formacao nao pode ser nulo ou vazio.");
			((Professor) this.mapaPesquisadores.get(email).getEspecialidade()).setFormacao(novoValor);
		} else if (atributo.equals("UNIDADE")) {
			Verificador.verificaEntrada(novoValor, "Campo unidade nao pode ser nulo ou vazio.");
			((Professor) this.mapaPesquisadores.get(email).getEspecialidade()).setUnidade(novoValor);
		} else if (atributo.equals("DATA")) {
			Verificador.verificaEntrada(novoValor, "Campo data nao pode ser nulo ou vazio.");
			Verificador.verificaData(novoValor, "Atributo data com formato invalido.");
			((Professor) this.mapaPesquisadores.get(email).getEspecialidade()).setData(novoValor);
		} else {
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
		Verificador.existeChaveString(this.mapaPesquisadores, email, "Pesquisador nao encontrado");
		if (!pesquisadorEhAtivo(email)) {
			throw new IllegalArgumentException("Pesquisador inativo.");
		}
		if (mapaPesquisadores.get(email).getEspecialidade() == null) {
			return this.mapaPesquisadores.get(email).toString();
		}
		return this.mapaPesquisadores.get(email).toString();
	}

	/**
	 * Ativa um pesquisador que esta atualmente inativo
	 * 
	 * @param email
	 *            email identificador do pesquisador a ser ativo
	 */
	public void ativaPesquisador(String email) {
		Verificador.verificaEmail(email, "Formato de email invalido.");
		Verificador.existeChaveString(this.mapaPesquisadores, email, "Pesquisador nao encontrado");
		if (pesquisadorEhAtivo(email)) {
			throw new IllegalArgumentException("Pesquisador ja ativado.");
		}
		this.mapaPesquisadores.get(email).setAtividade("Ativo");
	}

	/**
	 * Desativa um pesquisador atualmente ativo
	 * 
	 * @param email
	 *            email identificador do pesquisador a ser desativado
	 */
	public void desativaPesquisador(String email) {
		Verificador.verificaEmail(email, "Formato de email invalido.");
		Verificador.existeChaveString(this.mapaPesquisadores, email, "Pesquisador nao encontrado");
		if (!pesquisadorEhAtivo(email)) {
			throw new IllegalArgumentException("Pesquisador inativo.");
		}
		this.mapaPesquisadores.get(email).setAtividade("Inativo");
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
		Verificador.existeChaveString(this.mapaPesquisadores, email, "Pesquisador nao encontrado");
		if (this.mapaPesquisadores.get(email).getAtividade().equals("Ativo")) {
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
	public List<String> procuraPalavra(String palavra) {

		List<String> resultados = new ArrayList<String>();
		Verificador.verificaEntrada(palavra, "Campo termo nao pode ser nulo ou vazio.");
		List<Pesquisador> listaPesquisadores = new ArrayList<>(this.mapaPesquisadores.values());
		for (Pesquisador pesquisador : listaPesquisadores) {
			if (pesquisador.getBiografia().toLowerCase().contains(palavra)) {
				resultados.add(pesquisador.getEmail() + ": " + pesquisador.getBiografia());
			}
		}
		return resultados;
	}

	/**
	 * Cadastra as informacoes adicionais referentes a professor.
	 * 
	 * @param email email do pesquisador que se deseja alterar.
	 * @param formacao formacao do professor.
	 * @param unidade unidade na qual o prpofessor esta alocado.
	 * @param data data de contratacao do professor.
	 */
	public void cadastraEspecialidadeProfessor(String email, String formacao, String unidade, String data) {
		Verificador.verificaEntrada(email, "Campo email nao pode ser nulo ou vazio.");
		Verificador.verificaEntrada(formacao, "Campo formacao nao pode ser nulo ou vazio.");
		Verificador.verificaEntrada(unidade, "Campo unidade nao pode ser nulo ou vazio.");
		Verificador.verificaEntrada(data, "Campo data nao pode ser nulo ou vazio.");
		Verificador.verificaEmail(email, "Atributo email com o formato invalido.");
		Verificador.verificaData(data, "Atributo data com formato invalido.");
		Verificador.existeChave(this.mapaPesquisadores, email, "Pesquisadora nao encontrada.");
		if (this.mapaPesquisadores.get(email).getFuncao().equals("professor")) {
			Professor especialidade = new Professor(formacao, unidade, data);
			this.mapaPesquisadores.get(email).setEspecialidade(especialidade);
		} else {
			throw new IllegalArgumentException("Pesquisador nao compativel com a especialidade.");
		}
	}

	/**
	 * Cadastra as informacoes adicionais referentes a aluno.
	 * 
	 * @param email email do pesquisador que se deseja alterar.
	 * @param semestre semestre de ingresso do aluno.
	 * @param IEA iea do aluno.
	 */
	public void cadastraEspecialidadeAluno(String email, int semestre, double IEA) {
		Verificador.verificaEntrada(email, "Campo email nao pode ser nulo ou vazio.");
		Verificador.verificaEntrada(String.valueOf(semestre), "Semestre nao pode ser nulo ou vazio.");
		Verificador.verificaEntrada(String.valueOf(IEA), "IEA nao pode ser nulo ou vazio.");
		Verificador.verificaIEA(IEA, "Atributo IEA com formato invalido.");
		Verificador.verificaInteiroPositivo(semestre, "Atributo semestre com formato invalido.");
		Verificador.verificaEmail(email, "Atributo email com formato invalido.");
		Verificador.existeChave(this.mapaPesquisadores, email, "Pesquisadora nao encontrada.");
		if (this.mapaPesquisadores.get(email).getFuncao().equals("estudante")) {
			this.mapaPesquisadores.get(email).setEspecialidade(new Aluno(semestre, IEA));
		} else {
			throw new IllegalArgumentException("Pesquisador nao compativel com a especialidade.");
		}
	}

	/**
	 * Lista os Pesquisadores de determinado tipo
	 * @param tipo, o tipo do pesquisador que sera listado
	 * @return a representa��o em string dos pesquisadores encontrados
	 */
	public String listaPesquisadores(String tipo) {
		String exibicao = "";
		Verificador.verificaEntrada(tipo, "Campo tipo nao pode ser nulo ou vazio.");
		Verificador.verificaTipo(tipo, "Tipo " + tipo + " inexistente.");
		for (Pesquisador p : mapaPesquisadores.values()) {
			if (p.getFuncao().toUpperCase().equals(tipo)) {
				exibicao += p.toString() + " | ";
			}
		}
		return exibicao.substring(0, exibicao.length() - 3);
	}

	/**
	 * Retorna um Objeto do tipo Pesquisador que esta guardado no mapa de
	 * pesquisadores
	 * 
	 * @param emailPesquisador
	 *            o identificador unico do Pesquisador
	 * @return retorna um objeto do tipo Pesquisador
	 */
	public Pesquisador getPesquisador(String emailPesquisador) {
		return this.mapaPesquisadores.get(emailPesquisador);

	}

	public void salvar() {
		SalvaSistema.gravarDados(this.mapaPesquisadores,"dadosPesquisadores.dat");
	}
	public void retorna() {
		this.mapaPesquisadores = SalvaSistema.retornaDadoPesquisador();
	}
}
