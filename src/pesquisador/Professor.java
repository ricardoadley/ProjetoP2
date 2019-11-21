package pesquisador;

import java.io.Serializable;

public class Professor implements Especialidade, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5228101515900261669L;
	private String formacao;
	/**
	 * Unidade na qual o professor esta alocado.
	 */
	private String unidade;
	/**
	 * Data de contratacao do professor.
	 */
	private String data;

	/**
	 * Contrutor das caracteristicas do professor.
	 * 
	 * @param formacao formacao do professor.
	 * @param unidade unidadade na qual o professor esta alocado.
	 * @param data data de contratacao do professor.
	 */
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