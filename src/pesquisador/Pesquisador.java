package pesquisador;

import sistema.Verificador;
import java.util.ArrayList;
import pesquisa.Pesquisa;



/**
 * Representacao de um pesquisador
 * @author Vinicius M. V. Varjao
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
	private Pesquisa pesquisa;
	private Especialidade especialidade;
	private ArrayList<String> pesquisasAssociadas;
	
	/**
	 * Construtor do objeto Pesquisador, que recebe seus atributos e define a atividade como "Ativo" por padrao
	 * @param nome o nome do pesquisador
	 * @param funcao a funcao do pesquisador
	 * @param biografia a biografia do pesquisador
	 * @param email o email do pesquisador
	 * @param fotoURL a URL da foto do pesquisador
	 */
	public Pesquisador(String nome, String funcao, String biografia, String email, String fotoURL) {
		Verificador.verificaEntrada(nome, "Campo nome nao pode ser nulo ou vazio.");
		Verificador.verificaEntrada(funcao, "Campo funcao nao pode ser nulo ou vazio.");
		Verificador.verificaEntrada(biografia, "Campo biografia nao pode ser nulo ou vazio.");
		Verificador.verificaEntrada(email, "Campo email nao pode ser nulo ou vazio.");
		Verificador.verificaEntrada(fotoURL, "Campo fotoURL nao pode ser nulo ou vazio.");
		Verificador.verificaEmail(email, "Formato de email invalido.");
		Verificador.verificaFotoURL(fotoURL, "Formato de foto invalido."); 
		this.nome = nome;
		this.funcao = funcao; 
		this.biografia = biografia;
		this.email = email;
		this.fotoURL = fotoURL;
		this.atividade = "Ativo";
		this.pesquisasAssociadas = new ArrayList<String>();
		this.especialidade = null;
	}

	public String getFuncao() {
		return funcao;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBiografia() {
		return biografia;
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
	
	public boolean contemPesquisa() {
		if (this.pesquisa == null) {
			return false;
		}
		return true;
	}

	public boolean ehAtivo() {
		if (this.atividade.equalsIgnoreCase("Ativo")) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	/**
	 * Retorna a representacao em texto do pesquisador no formato "NOME (FUNCAO) - BIOGRAFIA - EMAIL - FOTO"
	 */
	@Override
	public String toString() {
		return this.nome + " (" + this.funcao + ") - " + this.biografia + " - " + this.email + " - " + this.fotoURL;
	}
	
	public String toStringEspecialidade() {
		return this.nome + " (" + this.funcao + ") - " + this.biografia + " - " + this.email + " - " + this.fotoURL + " - " + getEspecialidade().toString();
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
	
	public boolean associaPesquisador(String idPesquisa) {
		if (this.pesquisasAssociadas.contains(idPesquisa)) {
			return false;
		}
		this.pesquisasAssociadas.add(idPesquisa);
		return true;
	}

	public boolean desassociaPesquisador(String idPesquisa) {
		if (!this.pesquisasAssociadas.contains(idPesquisa)) {
			return false;
		}
		this.pesquisasAssociadas.remove(idPesquisa);
		return true;
	}
}
