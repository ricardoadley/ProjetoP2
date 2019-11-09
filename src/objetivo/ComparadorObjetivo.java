package objetivo;

import java.util.Comparator;

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
