package pesquisa;

import java.util.Comparator;

/**
 * Classe que compara as Pesquisas de acordo com a quantidade de Objetivos
 * associados a elas. Caso contenham a mesma quantidade de Objetivos cada uma,
 * as Pesquisas sao comparadas pelo proprio identificador unico.
 * 
 * @author José Matheus do N. Gama
 *
 */
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

			return (pesquisa1.getCodigo().compareTo(pesquisa2.getCodigo()) * -1);

		}

	}

}
