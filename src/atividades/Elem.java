package atividades;

public class Elem<T> {

	private T v;

	private Elem<T> prox;

	public Elem(T v) {
		this.v = v;
	}

	public void defineProximaAtividade(T v) {
		if (this.prox == null) {
			this.prox = new Elem<T>(v);
		} else {
			this.prox.defineProximaAtividade(v);
		}
	}
}