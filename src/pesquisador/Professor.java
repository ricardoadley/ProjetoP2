package pesquisador;

import java.io.Serializable;

public class Professor implements Especialidade, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5228101515900261669L;
	private String formacao;
	private String unidade;
	private String data;

	public Professor(String formacao, String unidade, String data) {
		super();
		this.formacao = formacao;
		this.unidade = unidade;
		this.data = data;
	}

	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public void setAtributo(String atributo, String novo) {

	}

	@Override
	public String toString() {
		return formacao + " - " + unidade + " - " + data;
	}

	@Override
	public String getEspecialidade() {
		return "Professor";
	}

}