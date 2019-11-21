package pesquisador;

import java.io.Serializable;

/**
 * Classe que define as caracteristicas de um pesquisador que possui "ALUNO"
 * como funcao.
 * 
 * @author Ana Beatriz da S. Truta
 *
 */
public class Aluno implements Especialidade, Serializable {
	/**
	 * versao de serializacao da classe
	 */
	private static final long serialVersionUID = 211413174667739514L;
	/**
	 * Semestre de ingresso do aluno.
	 */
	private int semestre;
	/**
	 * Indice de Eficiencia do Aluno do aluno
	 */
	private double iea;

	/**
	 * Contrutor da classe Aluno.
	 * 
	 * @param semestre semestre de ingresso do aluno.
	 * @param iea      do aluno.
	 */
	public Aluno(int semestre, double iea) {
		super();
		this.semestre = semestre;
		this.iea = iea;
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public void setIea(double iea) {
		this.iea = iea;
	}

	@Override
	public void setAtributo(String atributo, String novo) {

	}

	/**
	 * Retorna a representação em String do Aluno no formato "semestre + o SEMESTRE
	 * + iea"
	 */
	@Override
	public String toString() {
		return semestre + "o SEMESTRE - " + iea;
	}

	@Override
	public String getEspecialidade() {
		return "Aluno";
	}

}