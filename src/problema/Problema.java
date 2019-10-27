package problema;

/**
 * Classe que representa um problema. Todo objeto do tipo Problema possui uma
 * descricao, um inteiro (de 1 a 5) que representa sua viabilidade e um codigo pelo qual e
 * identificado unicamente.
 * 
 * @author josemng
 *
 */
public class Problema {

	/**
	 * Descricao do problema.
	 */
	private String descricao;
	
	/**
	 * Representacao quantitativa do quanto o problema e viavel.
	 */
	private int viabilidade;
	
	/**
	 * Codigo que identifica unicamente o Problema.
	 */
	private String codigo;

	/**
	 * Constroi um objeto do tipo Problema.
	 * 
	 * @param descricao a descricao do problema
	 * @param viabilidade a representacao quantitativa do quanto o problema e viavel
	 * @param codigo o codigo que identifica unicamente o problema
	 */
	public Problema(String descricao, int viabilidade, String codigo) {
		this.descricao = descricao;
		this.viabilidade = viabilidade;
		this.codigo = codigo;
	}

	/**
	 * Retorna a representacao em String do Problema, no formato "codigo - descricao - viabilidade".
	 * 
	 * @return a representacao em String do Problema
	 */
	public String toString() {

		return this.codigo + " - " + this.descricao + " - " + this.viabilidade;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Problema other = (Problema) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	
	
}
