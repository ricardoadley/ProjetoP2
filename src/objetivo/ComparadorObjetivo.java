package objetivo;

import java.util.Comparator;

/**
 * Compara os objetivos de acordo com seu identificador unico
 * 
 * @author Ricardo A. S. Sena
 *
 */
public class ComparadorObjetivo implements Comparator<Objetivo> {

	@Override
	public int compare(Objetivo o1, Objetivo o2) {
		if (Integer.parseInt(o1.getCodigo().substring(1, 2)) < Integer.parseInt(o2.getCodigo().substring(1, 2))) {
			return 1;
		}
		if (Integer.parseInt(o1.getCodigo().substring(1, 2)) > Integer.parseInt(o2.getCodigo().substring(1, 2))) {
			return -1;
		}
		return 0;
	}
}
