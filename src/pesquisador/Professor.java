package pesquisador;

import java.io.Serializable;

/**
 * Classe que define as caracteristicas de um pesquisador que possui "PROFESSOR"
 * como funcao.
 * 
 * @author Ana Beatriz da S. Truta
 *
 */
public class Professor implements Especialidade, Serializable {

	/**
	 * versao de serializacao da classe
	 */
	private static final long serialVersionUID = -5228101515900261669L;
	/*
	 * A formacao do professor
	 */
	private String formacao;
	/*
	 * A unidade a qual o professor pertence
	 */
	private String unidade;
	/**
	 * a data de ingresso do professor
	 */
	private String data;

	/**
	 * construtor da entidade professor
	 * 
	 * @param formacao, a formacao do professor
	 * @param unidade,  a unidade a qual o professor pertence
	 * @param data,     a data de ingresso do professor
	 */
	public Professor(String formacao, String unidade, String data) {
		super();
		this.formacao = formacao;
		this.unidade = unidade;
		this.data = data;
	}

	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public void setAtributo(String atributo, String novo) {

	}

	@Override
	/**
	 * retorna a representacao em string do professor no formato FORMACAO - UNIDADE
	 * - DATA
	 */
	public String toString() {
		return formacao + " - " + unidade + " - " + data;
	}

	@Override
	public String getEspecialidade() {
		return "Professor";
	}

}