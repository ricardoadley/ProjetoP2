package pesquisador;

/**
 * Representacao da especialidade de um aluno.
 * 
 * @author Ana Beatriz da S. Truta
 *
 */
public class Aluno implements Especialidade{

	/**
	 * Semestre de ingresso do aluno.
	 */
	private int semestre;
	/**
	 * Indice de eficiencia academica do aluno.
	 */
	private double iea;

	/**
	 * Construtor do objeto Aluno, que recebe seus atributos.
	 * 
	 * @param semestre o semestre de ingresso do aluno.
	 * @param iea o Indice de Eficiencia Academica do aluno.
	 */
	public Aluno(int semestre, double iea) {
		super();
		this.semestre = semestre;
		this.iea = iea;
	}

	public String getSemestre() {
		return semestre + "o SEMESTRE";
	}

	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	public double getIea() {
		return iea;
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