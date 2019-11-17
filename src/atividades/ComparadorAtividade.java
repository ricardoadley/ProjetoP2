package atividades;

import java.util.Comparator;

/**
 * Compara as atividades de acordo com os seus identificadores unicos
 * 
 * @author Ricardo A. S. Sena
 *
 */
public class ComparadorAtividade implements Comparator<Atividade> {

	@Override
	public int compare(Atividade a1, Atividade a2) {

		return a1.getCodigo().compareTo(a2.getCodigo()) * -1;

	}

}
