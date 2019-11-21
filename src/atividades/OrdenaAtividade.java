package atividades;

/**
 * Classe responsável por ordenar a atividade.
 * 
 * @author josemng
 *
 * @param <ordem>
 */
public class OrdenaAtividade<ordem> {

	/**
	 * O valor da atividade
	 */
	private ordem valor;

	/**
	 * O proximo valor da atividade
	 */
	private OrdenaAtividade<ordem> proximo;

	/**
	 * Construtor de OrdenaAtividade
	 * 
	 * @param valor o valor da atividade
	 */
	public OrdenaAtividade(ordem valor) {
		this.valor = valor;
	}

	/**
	 * Adiciona um proximo valor na atividade
	 * 
	 * @param valor o valor da atividade
	 */
	public void adicionaProximo(ordem valor) {
		if (this.proximo == null) {
			this.proximo = new OrdenaAtividade<ordem>(valor);
		} else {
			this.proximo.adicionaProximo(valor);
		}
	}
}