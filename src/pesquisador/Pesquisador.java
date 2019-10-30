package pesquisador;

import sistema.Verificador;

/**
 * Representacao de um pesquisador
 * @author Vinícius M. V. Varjão
 *
 */
public class Pesquisador {

	/**
	 * O email do pesquisador, o qual vai ser usado para identifica-lo
	 */
	private String email;
	/**
	 * O nome do pesquisador
	 */
	private String nome;
	/**
	 * A funcao do pesquisador
	 */
	private String funcao;
	/**
	 * A biografia do pesquisador
	 */
	private String biografia;
	/**
	 * A URL da foto a ser usada pelo pesquisador
	 */
	private String fotoURL;	
	/**
	 * O status de atividade do pesquisador, que pode ser "Ativo" ou "Inativo e define a possibilidade de manipulacai
	 * do mesmo"
	 */
	private String atividade;
	/**
	 * Instancia da classe lancadora de excecoes
	 */
	private Verificador verificador;
	
	/**
	 * Construtor do objeto Pesquisador, que recebe seus atributos e define a atividade como "Ativo" por padrao
	 * @param nome o nome do pesquisador
	 * @param funcao a funcao do pesquisador
	 * @param biografia a biografia do pesquisador
	 * @param email o email do pesquisador
	 * @param fotoURL a URL da foto do pesquisador
	 */
	public Pesquisador(String nome, String funcao, String biografia, String email, String fotoURL) {
		verificador = new Verificador();
		verificador.verificaEntrada(nome, "Campo nome nao pode ser nulo ou vazio.");
		verificador.verificaEntrada(funcao, "Campo funcao nao pode ser nulo ou vazio.");
		verificador.verificaEntrada(biografia, "Campo biografia nao pode ser nulo ou vazio.");
		verificador.verificaEntrada(email, "Campo email nao pode ser nulo ou vazio.");
		verificador.verificaEntrada(fotoURL, "Campo fotoURL nao pode ser nulo ou vazio.");
		verificador.verificaEmail(email, "Formato de email invalido.");
		verificador.verificaFotoURL(fotoURL, "Formato de foto invalido.");
		this.nome = nome;
		this.funcao = funcao;
		this.biografia = biografia;
		this.email = email;
		this.fotoURL = fotoURL;
		this.atividade = "Ativo";
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	public void setFotoURL(String fotoURL) {
		this.fotoURL = fotoURL;
	}

	public String getAtividade() {
		return atividade;
	}

	public void setAtividade(String atividade) {
		this.atividade = atividade;
	}
	
	/**
	 * Retorna a representacao em texto do pesquisador no formato "NOME (FUNÇÃO) - BIOGRAFIA - EMAIL - FOTO"
	 */
	@Override
	public String toString() {
		return this.nome + " (" + this.funcao + ") - " + this.biografia + " - " + this.email + " - " + this.fotoURL;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pesquisador other = (Pesquisador) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
}
