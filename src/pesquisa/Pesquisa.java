package pesquisa;

import sistema.Verificador;

public class Pesquisa {
	
	private String descricao;
	private String campo;
	private String status;
	private String codigo;
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private Verificador verificador;
	
	public Pesquisa(String descricao, String campo, String codigo) {
		verificador = new Verificador();
		verificador.verificaEntrada(descricao, "Descricao nao pode ser nula ou vazia.");
		verificador.verificaEntrada(campo, "Formato do campo de interesse invalido.");
		this.descricao = descricao;
		this.campo = campo;	
		this.codigo = codigo;
		this.status = "Ativa";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Pesquisa other = (Pesquisa) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
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