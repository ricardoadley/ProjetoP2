package pesquisa;

import sistema.Verificador;

public class Pesquisa {
	
	private String descricao;
	private String campo;
	private String status;
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private Verificador verificador;
	
	public Pesquisa(String descricao, String campo) {
		verificador = new Verificador();
		verificador.verificaEntrada(descricao, "Descricao nao pode ser nula ou vazia.");
		verificador.verificaEntrada(campo, "Formato do campo de interesse invalido.");
		this.descricao = descricao;
		this.campo = campo;	
		this.status = "Ativa";
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public String toString(String codigo) {
		return codigo + " - " + descricao + " - " + campo;
	}
	
}