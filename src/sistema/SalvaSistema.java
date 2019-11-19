package sistema;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;


public class SalvaSistema {

    public static void gravarDado(List lista, String arquivo) {
        File arq = new File(arquivo);
        try {
          //arq.delete();
          arq.createNewFile();
      
          ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(arq));
          objOutput.writeObject(lista);
          objOutput.close();
      
        } catch(IOException erro) {
            System.out.printf("Erro: %s", erro.getMessage());
        }
	
	
//	public void salvarTudo() {
//		this.atividadeController.salvar();
//		this.objetivoController.salvar();
//		this.problemaController.salvar();
//		this.pesquisadorController.salvar();
//		this.pesquisaController.salvar();
//	}
}
}
