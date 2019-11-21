package pesquisador;

/**
 * Interface da Especialidade do aluno.
 * 
 * @author Ana Beatriz da S. Truta
 *
 */
public interface Especialidade {

	@Override
	public String toString();

	public String getEspecialidade();

	public void setAtributo(String atributo, String novo);
}
