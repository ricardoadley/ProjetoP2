package pesquisa;

import java.util.Comparator;

/**
 * Classe que compara as Pesquisas de acordo com o ID do Problema a qual elas
 * estao associadas. Ordena do maior ID para o menor ID.
 * 
 * @author Jose Matheus do N. Gama
 *
 */
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
