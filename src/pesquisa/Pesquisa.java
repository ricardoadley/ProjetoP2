package pesquisa;

import java.util.HashMap;
import java.util.Map;

import objetivo.Objetivo;
import problema.Problema;
import sistema.Verificador;

/**
 * 
 * @author Beatriz Truta
 *
 */
public class Pesquisa {
	/**
	 * a descricao da pesquisa
	 */
	private String descricao;
	/**
	 * O campo de interesse da pesquisa
	 */
	private String campo;
	/**
	 * o status atual da pesquisa
	 */
	private String status;
	/**
	 * o codigo da pesquisa, e tambem seu identificador
	 */
	private String codigo;

	private Problema problema;
	private Map<String, Objetivo> objetivos;

	/**
	 * Constroi uma nova pesquisa de acordo com os parametros informados pelo
	 * usuario
	 * 
	 * @param descricao, a descricao da pesquisa
	 * @param campo,     o campo da pesquisa
	 * @param codigo,    o codigo unico da pesquisa e tambem seu identificador
	 */
	public Pesquisa(String descricao, String campo, String codigo) {
		Verificador.verificaEntrada(descricao, "Descricao nao pode ser nula ou vazia.");
		Verificador.verificaEntrada(campo, "Formato do campo de interesse invalido.");
		this.descricao = descricao;
		this.campo = campo;
		this.codigo = codigo;
		this.status = "Ativa";
		this.problema = null;
		this.objetivos = new HashMap<>();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCampo() {
		return campo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public void setProblema(Problema problema) {
		this.problema = problema;
	}

	public void adicionaObjetivo(String idObjetivo, Objetivo objetivo) {
		this.objetivos.put(idObjetivo, objetivo);
	}

	public void removeObjetivo(String idObjetivo) {
		this.objetivos.remove(idObjetivo);
	}

	public boolean contemProblema() {

		if (this.problema == null) {
			return false;
		}

		return true;

	}

	public boolean contemObjetivo(String idObjetivo) {

		if (this.objetivos.containsKey(idObjetivo)) {
			return true;
		}

		else {
			return false;
		}

	}

	public Problema getProblema() {
		return this.problema;
	}

	public boolean isAtivada() {
		if (this.status.equalsIgnoreCase("Ativa")) {
			return true;
		}

		else {
			return false;
		}

	}

	@Override
	/**
	 * Retorna uma representacao em string da pesquisa no formato CODIGO - DESCRICAO
	 * - CAMPO DA PESQUISA
	 * 
	 * @return a representacao em string da pesquisa.
	 */
	public String toString() {
		return codigo + " - " + descricao + " - " + campo;
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
		Pesquisa other = (Pesquisa) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}