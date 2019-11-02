package sistema;

import java.util.ArrayList;
import java.util.List;

public class BuscadorPalavra{
	private static List<String> encontradas;

	public BuscadorPalavra() {
		encontradas = new ArrayList<>();
	}
	public static String procuraPalavra(String palavra, String frase) {
		if(frase.toLowerCase().contains(palavra.toLowerCase())) {
			return frase;
		}else {
			return "";
		}
	}
	public static void adicionaEncontrado(String frase) {
		if(!frase.equals("")) {
			encontradas.add(frase);
		}
	}
	
	public String retornaEncontradas(int indice) {
		String retorno = "";
		for(int i=0;i<encontradas.size();i++) {
//			//if(encontradas.get(i).contains(Integer.toString(indice))){
				retorno = retorno + encontradas.get(i)+System.lineSeparator();
			}
//			
		//}
		return retorno;
		// return Collections.sort(encontradas);
		//return encontradas.get(indice-1);
	}

}
