package pesquisador;

public class Professor implements Especialidade{

	private String formacao;
	private String unidade;
	private String data;

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
		// TODO Auto-generated method stub
		return null;
	}

}