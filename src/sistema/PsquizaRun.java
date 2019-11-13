package sistema;


import easyaccept.EasyAccept;

public class PsquizaRun {  

	public static void main(String[] args) {
			args = new String[] {"sistema.Facade","testes_de_aceitacao/use_case_1.txt", "testes_de_aceitacao/use_case_2.txt", 
			"testes_de_aceitacao/use_case_3.txt", "testes_de_aceitacao/use_case_4.txt", "testes_de_aceitacao/use_case_5.txt",
			"testes_de_aceitacao/use_case_7.txt", "testes_de_aceitacao/use_case_8.txt"}; 
			EasyAccept.main(args);
	}
} 



