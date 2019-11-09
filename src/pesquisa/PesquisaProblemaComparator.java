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

		return (pesquisa.getStringProblema() + pesquisa.getCodigo())
				.compareTo(outraPesquisa.getStringProblema() + outraPesquisa.getCodigo()) * -1;

	}

}
