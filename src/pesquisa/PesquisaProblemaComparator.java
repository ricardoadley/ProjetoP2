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

		if (pesquisa.contemProblema() && !outraPesquisa.contemProblema()) {

			return -1;

		}

		if (!pesquisa.contemProblema() && outraPesquisa.contemProblema()) {

			return 1;

		}

		else {

			return pesquisa.getCodigo().compareTo(outraPesquisa.getCodigo()) * -1;

		}

	}

}
