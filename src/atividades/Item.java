package atividades;

public class Item {
	private int ordemCadastro;
	private String descricao;
	private boolean realizado;

	public Item(String descricao, int ordemCadastro) {
		// super();
		realizado = false;
		this.descricao = descricao;
	}

	public int getOrdemCadastro() {
		return ordemCadastro;
	}

	public void setOrdemCadastro(int ordemCadastro) {
		this.ordemCadastro = ordemCadastro;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isRealizado() {
		return realizado;
	}

	public void setRealizado(boolean realizado) {
		this.realizado = realizado;
	}

}
