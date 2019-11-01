package pesquisa;

import sistema.Verificador;
/**
 * 
 * @author Beatriz Truta
 *
 */
public class Pesquisa {
	/**
	 * a descricao da pesquisa
	 */
	private String descricao;
	/**
	 * O campo de interesse da pesquisa
	 */
	private String campo;
	/**
	 * o status atual da pesquisa
	 */
	private String status;
	/**
	 * o codigo da pesquisa, e tambem seu identificador
	 */
	private String codigo;
	/**
	 * Constroi uma nova pesquisa de acordo com os parametros informados pelo usuario
	 * @param descricao, a descricao da pesquisa
	 * @param campo, o campo da pesquisa
	 * @param codigo, o codigo unico da pesquisa e tambem seu identificador
	 */
	public Pesquisa(String descricao, String campo, String codigo) {
		Verificador.verificaEntrada(descricao, "Descricao nao pode ser nula ou vazia.");
		Verificador.verificaEntrada(campo, "Formato do campo de interesse invalido.");
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
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
	public String getCodigo() {
		return codigo;
	}
	public void setCampo(String campo) {
		this.campo = campo;
	}
	
	@Override
	/**
	 * Retorna uma representacao em string da pesquisa no formato
	 * CODIGO - DESCRICAO - CAMPO DA PESQUISA
	 * @return a representacao em string da pesquisa.
	 */
	public String toString() {
		return codigo + " - " + descricao + " - " + campo;
	}
	
}