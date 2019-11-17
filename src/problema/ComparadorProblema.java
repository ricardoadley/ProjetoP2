package problema;

import java.util.Comparator;

/**
 * Compara os problemas de acordo com os seus identificadores unicos
 * 
 * @author Ricardo A. S. Sena
 *
 */
public class ComparadorProblema implements Comparator<Problema> {

	@Override
	public int compare(Problema p1, Problema p2) {
		if (Integer.parseInt(p1.getCodigo().substring(1, 2)) < Integer.parseInt(p2.getCodigo().substring(1, 2))) {
			return 1;
		}
		if (Integer.parseInt(p1.getCodigo().substring(1, 2)) > Integer.parseInt(p2.getCodigo().substring(1, 2))) {
			return -1;
		}
		return 0;
	}

}