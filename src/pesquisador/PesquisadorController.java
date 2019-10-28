package pesquisador;

import java.util.HashMap;
import java.util.Map;

public class PesquisadorController {

	private Map<String, Pesquisador> mapaEmailPesquisador;

	public PesquisadorController() {
		this.mapaEmailPesquisador = new HashMap<>();
	}

	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoLink) {
		this.mapaEmailPesquisador.put(email,  new Pesquisador(nome, funcao, biografia, email, fotoLink));
		
	}
	public void alteraPesquisador(String email, String atributo, String novoValor) {
		if (atributo == "nome") {
			this.mapaEmailPesquisador.get(email).setNome(novoValor);
		}
		if (atributo == "funcao") {
			this.mapaEmailPesquisador.get(email).setFuncao(novoValor);
		}
		if (atributo == "biografia") {
			this.mapaEmailPesquisador.get(email).setBiografia(novoValor);
		}
		if (atributo == "email") {
			this.mapaEmailPesquisador.put(novoValor, this.mapaEmailPesquisador.get(email));
			this.mapaEmailPesquisador.get(novoValor).setEmail(novoValor);
			this.mapaEmailPesquisador.remove(email);
		}
		if (atributo == "foto") {
			this.mapaEmailPesquisador.get(email).setFotoURL(novoValor);
		}
		
	}
}
