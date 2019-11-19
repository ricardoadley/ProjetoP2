package pesquisador;

import java.io.Serializable;

public class Aluno implements Especialidade, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 211413174667739514L;
	private int semestre;
	private double iea;

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

	@Override
	public String toString() {
		return semestre + "o SEMESTRE - " + iea;
	}

	@Override
	public String getEspecialidade() {
		return "Aluno";
	}	

}