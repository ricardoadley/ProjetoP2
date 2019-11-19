package atividades;

public class OrdenaAtividade<ordem> {
	
	private ordem valor;

	private OrdenaAtividade<ordem> proximo;

	public OrdenaAtividade(ordem valor) {
		this.valor = valor;
	}

	public void adicionaProximo(ordem valor) {
		if (this.proximo == null) {
			this.proximo = new OrdenaAtividade<ordem>(valor);
		} else {
			this.proximo.adicionaProximo(valor);
		}
	}
}
