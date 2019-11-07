package pesquisa;

import java.util.Comparator;

public class PesquisaIDComparator implements Comparator<Pesquisa> {

	public int compare(Pesquisa pesquisa1, Pesquisa pesquisa2) {

		return pesquisa1.getCodigo().compareTo(pesquisa2.getCodigo());
	}

}
