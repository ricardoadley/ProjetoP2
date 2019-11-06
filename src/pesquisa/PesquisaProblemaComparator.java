package pesquisa;

import java.util.Comparator;

public class PesquisaProblemaComparator implements Comparator<Pesquisa> {

	@Override
	public int compare(Pesquisa pesquisa, Pesquisa outraPesquisa) {

		if (pesquisa.contemProblema() && pesquisa.contemProblema()) {

			return ((pesquisa.verificaProblema() + pesquisa.getCodigo() + pesquisa.getProblema().getCodigo())
					.compareTo(outraPesquisa.verificaProblema() + outraPesquisa.getCodigo()
							+ outraPesquisa.getProblema().getCodigo()));
		}

		if (pesquisa.contemProblema() && !pesquisa.contemProblema()) {
			return ((pesquisa.verificaProblema() + pesquisa.getCodigo() + pesquisa.getProblema().getCodigo())
					.compareTo(outraPesquisa.verificaProblema() + outraPesquisa.getCodigo()));
		}

		if (!pesquisa.contemProblema() && pesquisa.contemProblema()) {

			return (pesquisa.verificaProblema() + pesquisa.getCodigo().compareTo(outraPesquisa.verificaProblema()
					+ outraPesquisa.getCodigo() + outraPesquisa.getProblema().getCodigo()));

		}

		else {
			return (pesquisa.verificaProblema() + pesquisa.getCodigo())
					.compareTo(outraPesquisa.verificaProblema() + outraPesquisa.getCodigo());
		}

	}

}
