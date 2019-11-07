package pesquisa;

import java.util.Comparator;

public class PesquisaIDComparator implements Comparator<Pesquisa> {

	@Override
	public int compare(Pesquisa p1, Pesquisa p2) {
		return p1.getCodigo().compareTo(p2.getCodigo());
	}

}
