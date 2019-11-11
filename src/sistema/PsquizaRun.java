package sistema;

import easyaccept.EasyAccept;

public class PsquizaRun {  

	public static void main(String[] args) {
			args = new String[] {"sistema.Facade","testes_de_aceitacao/use_case_1.txt", "testes_de_aceitacao/use_case_2.txt", 
			"testes_de_aceitacao/use_case_3.txt", "testes_de_aceitacao/use_case_4.txt", "testes_de_aceitacao/use_case_5.txt",
			"testes_de_aceitacao/use_case_7.txt", "testes_de_aceitacao/use_case_8.txt"}; 
			EasyAccept.main(args);
			
			//Verificando se estava pesquisando as coisas corretamente
			// falta colocar em ordem anti ortografica e entender pra que serve numeroDoResultado
//			Facade f = new Facade();
//			f.cadastraAtividade("atividade de ricardo","ALTO","descrevi");
//			f.cadastraAtividade("atividade de ","ALTO","descrevi");
//			f.cadastraAtividade("atividade de joao","ALTO","ricardo eh doido");
//			f.cadastraPesquisa("pesquisar a burrice de ricardo","cabeca, ombro");
//			f.cadastraItem("A1","pastel de ricardo");
//			f.cadastraItem("A1","forninho");
//			f.cadastraItem("A2","ricardo eh item");
//			f.cadastraProblema("problema de ar", "4");
//			f.cadastraObjetivo("GERAL", "diminuir o ar condicionado", "4", "3");
//			f.cadastraPesquisador("Ricardo","pesquisar","especialista em ligar ar","ricardo@email.com","https://fotominha.com");
//			System.out.println(f.busca("ar", 1));
			//System.out.println(f.busca("ricardo", 2));
	}
} 



