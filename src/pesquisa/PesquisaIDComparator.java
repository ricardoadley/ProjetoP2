package pesquisa;

import java.util.Comparator;

/**
 * Classe que compara as Pesquisas de acordo com o identificador unico de cada.
 * 
 * @author José Matheus do N. Gama
 *
 */
public class PesquisaIDComparator implements Comparator<Pesquisa> {

	public int compare(Pesquisa pesquisa1, Pesquisa pesquisa2) {

		return pesquisa1.getCodigo().compareTo(pesquisa2.getCodigo());
	}

}
