package pesquisador;

public class Pesquisador {

	private String email;
	private String nome;
	private String funcao;
	private String biografia;
	private String fotoURL;	
	private sistema.Verificador verificador;
	
	public Pesquisador(String nome, String funcao, String biografia, String email, String fotoURL) {
		verificador.verificaEntrada(nome, "Campo nome nao pode ser nulo ou vazio.");
		verificador.verificaEntrada(funcao, "Campo funcao nao pode ser nulo ou vazio.");
		verificador.verificaEntrada(biografia, "Campo biografia nao pode ser nulo ou vazio.");
		verificador.verificaEntrada(email, "Campo email nao pode ser nulo ou vazio.");
		verificador.verificaEntrada(fotoURL, "Campo fotoURL nao pode ser nulo ou vazio.");
		this.nome = nome;
		this.funcao = funcao;
		this.biografia = biografia;
		this.email = email;
		this.fotoURL = fotoURL;
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

	@Override
	public String toString() {
		return this.nome + " (" + this.funcao + ") - " + this.biografia + " - " + this.email + " - " + this.fotoURL;
	}
}
