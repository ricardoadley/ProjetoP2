package pesquisa;

import java.util.Comparator;

public class PesquisaObjetivosComparator implements Comparator<Pesquisa> {

	@Override
	public int compare(Pesquisa pesquisa1, Pesquisa pesquisa2) {

		if (pesquisa1.getQtdObjetivos() < pesquisa2.getQtdObjetivos()) {

			return 1;

		}

		if (pesquisa1.getQtdObjetivos() > pesquisa2.getQtdObjetivos()) {

			return -1;

		}

		else {

			if (pesquisa1.getCodigo().compareTo(pesquisa2.getCodigo()) == 1) {
				return -1;
			}

			if (pesquisa1.getCodigo().compareTo(pesquisa2.getCodigo()) == -1) {
				return 1;
			}

			return 0;

		}

	}

}
