package pesquisa;

import java.util.Comparator;

public class PesquisaProblemaComparator implements Comparator<Pesquisa> {

	@Override
	public int compare(Pesquisa pesquisa, Pesquisa outraPesquisa) {

		if (Integer.parseInt(
				pesquisa.getProblema().getCodigo().substring(pesquisa.getProblema().getCodigo().length() - 1)) < Integer
						.parseInt(outraPesquisa.getProblema().getCodigo()
								.substring(outraPesquisa.getProblema().getCodigo().length() - 1))) {
			return 1;
		}

		if (Integer.parseInt(
				pesquisa.getProblema().getCodigo().substring(pesquisa.getProblema().getCodigo().length() - 1)) > Integer
						.parseInt(outraPesquisa.getProblema().getCodigo()
								.substring(outraPesquisa.getProblema().getCodigo().length() - 1))) {
			return -1;
		}

		return 0;
	}

}
