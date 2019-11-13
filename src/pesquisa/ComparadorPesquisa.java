package pesquisa;

import java.util.Comparator;

/**
 * Compara as pesquisas de acordo com o seu identificador unico
 * 
 * @author Ricardo A. S. Sena
 *
 */
public class ComparadorPesquisa implements Comparator<Pesquisa> {

	@Override
	public int compare(Pesquisa p1, Pesquisa p2) {
		if (Integer.parseInt(p1.getCodigo().substring(p1.getCodigo().length() - 1)) < Integer
				.parseInt(p2.getCodigo().substring(p2.getCodigo().length() - 1))) {
			return 1;
		}
		if (Integer.parseInt(p1.getCodigo().substring(p1.getCodigo().length() - 1)) > Integer
				.parseInt(p2.getCodigo().substring(p2.getCodigo().length() - 1))) {
			return -1;
		}
		return 0;
	}

}
