package problema;

import java.util.Comparator;

public class ComparadorProblema implements Comparator <Problema>{

	@Override
	public int compare(Problema p1, Problema p2) {
		// TODO Auto-generated method stub
		if(Integer.parseInt(p1.getCodigo().substring(1,2)) < Integer.parseInt(p2.getCodigo().substring(1,2))){
			return 1;
		}
		if(Integer.parseInt(p1.getCodigo().substring(1,2)) > Integer.parseInt(p2.getCodigo().substring(1,2))){
			return -1;
		}
		return 0;
	}

	
}
