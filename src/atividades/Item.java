package atividades;

import sistema.Verificador;

/**
 * 
 * @author Ricardo A. S. Sena
 *
 */
public class Item implements Comparable<Item> {
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
		Verificador.verificaEntrada(descricao, "O campo descricao nao pode ser vazio ou nulo");
		realizado = false;
		this.ordemCadastro = ordemCadastro;
		this.descricao = descricao;
	}

	public int getOrdemCadastro() {
		return this.ordemCadastro;
	}

	public String getDescricao() {
		return descricao;
	}

	public String toString() {

		return "ITEM" + this.ordemCadastro;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ordemCadastro;
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
		Item other = (Item) obj;
		if (ordemCadastro != other.ordemCadastro)
			return false;
		return true;
	}

	public boolean isRealizado() {
		return realizado;
	}

	public void setRealizado(boolean realizado) {
		this.realizado = realizado;
	}

	public boolean getRealizado() {
		return this.realizado;
	}

	@Override
	public int compareTo(Item item) {

		return this.toString().compareTo(item.toString());

	}
}
