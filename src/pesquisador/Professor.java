package pesquisador;

/**
 * Representacao da especialidade de um professor.
 * 
 * @author Ana Beatriz da S. Truta
 *
 */
public class Professor implements Especialidade{

	/**
	 * Formacao do professor.
	 */
	private String formacao;
	/**
	 * Unidade na qual o professor esta alocado
	 */
	private String unidade;
	/**
	 * Data de contratacao do professor.
	 */
	private String data;

	/**
	 * Construtor do objeto Professor, que recebe seus atributos.
	 * 
	 * @param formacao, a formacao do professor.
	 * @param unidade, a unidade ao qual o professor esta alocado.
	 * @param data, a data de contratacao do professor.
	 */
	public Professor(String formacao, String unidade, String data) {
		super();
		this.formacao = formacao;
		this.unidade = unidade;
		this.data = data;
	}

	public String getFormacao() {
		return formacao;
	}

	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public void setAtributo(String atributo, String novo) {
		// TODO Auto-generated method stub

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