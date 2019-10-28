package atividades;

/**
 * 
 * @author Ricardo A. S. Sena
 *
 */
public class Item {
	/**
	 * A ordem de cadastro do item na atividade
	 */
	private int ordemCadastro;
	/**
	 * a descricao do item cadastrado na atividade
	 */
	private String descricao;
	/**
	 * o status do item na atividade sendo false para pendente e true para
	 * realizado.
	 */
	private boolean realizado;

	/**
	 * Constroi um objeto item a partir dos parametros informados pelo usuario todo
	 * objeto item inicia com seu status como false.
	 * 
	 * @param descricao
	 * @param ordemCadastro
	 */
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
