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
		if (!this.mapaEmailPesquisador.containsKey(email)) {
			throw new IllegalArgumentException("Pesquisador nao encontrado.");
			}
		if (!pesquisadorEhAtivo(email)) {
			throw new IllegalArgumentException("Pesquisador inativo.");
		}
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
	
	public String exibePesquisador(String email) {
		if (!this.mapaEmailPesquisador.containsKey(email)) {
			throw new IllegalArgumentException("Pesquisador nao encontrado.");
			}
		if (!pesquisadorEhAtivo(email)) {
			throw new IllegalArgumentException("Pesquisador inativo.");
		}
		return this.mapaEmailPesquisador.get(email).toString();
	}
	
	public void ativaPesquisador(String email) {
		if (!this.mapaEmailPesquisador.containsKey(email)) {
			throw new IllegalArgumentException("Pesquisador nao encontrado.");
			}
		if (pesquisadorEhAtivo(email)) {
			throw new IllegalArgumentException("Pesquisador ja ativado.");
		}
		this.mapaEmailPesquisador.get(email).setAtividade("Ativo");
	}
	
	public void desativaPesquisador(String email) {
		if (!this.mapaEmailPesquisador.containsKey(email)) {
			throw new IllegalArgumentException("Pesquisador nao encontrado.");
			}	
		if (!pesquisadorEhAtivo(email)) {
			throw new IllegalArgumentException("Pesquisador inativo.");
		}
		this.mapaEmailPesquisador.get(email).setAtividade("Inativo");
	}
	
	public boolean pesquisadorEhAtivo(String email) {
		if (this.mapaEmailPesquisador.get(email).getAtividade() == "Ativo") {
			return true;
		}
		return false;
	}
}
