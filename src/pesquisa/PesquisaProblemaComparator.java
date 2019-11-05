package pesquisa;

import java.util.Comparator;

public class PesquisaProblemaComparator implements Comparator<Pesquisa>{

	@Override
	public int compare(Pesquisa pesquisa, Pesquisa outraPesquisa) {
		
		return pesquisa.getProblema().getCodigo().compareTo(outraPesquisa.getProblema().getCodigo());
		
	}

}
