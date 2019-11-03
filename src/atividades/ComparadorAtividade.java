package atividades;

import java.util.Comparator;

public class ComparadorAtividade implements Comparator <Atividade>{

	@Override
	public int compare(Atividade a1, Atividade a2) {
 		if(Integer.parseInt(a1.getCodigo().substring(1,2)) < Integer.parseInt(a2.getCodigo().substring(1,2))){
			return 1;
		}
		if(Integer.parseInt(a1.getCodigo().substring(1,2)) > Integer.parseInt(a2.getCodigo().substring(1,2))){
			return -1;
		}
		return 0;
	}

}
