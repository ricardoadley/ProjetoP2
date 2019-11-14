package pesquisador;

public class Aluno implements Especialidade{

	private int semestre;
	private double iea;

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
		// TODO Auto-generated method stub
		return null;
	}	

}