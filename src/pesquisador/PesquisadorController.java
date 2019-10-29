package pesquisador;

import java.util.HashMap;
import java.util.Map;

import sistema.Verificador;

public class PesquisadorController {

	private Map<String, Pesquisador> mapaEmailPesquisador;
	private Verificador verificador;
	
	public PesquisadorController() {
		this.mapaEmailPesquisador = new HashMap<>();
		this.verificador = new Verificador();
	}

	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoLink) {
		this.mapaEmailPesquisador.put(email,  new Pesquisador(nome, funcao, biografia, email, fotoLink));
		
	}
	public void alteraPesquisador(String email, String atributo, String novoValor) {
		verificador.verificaEmail(email, "Formato de email invalido.");
		verificador.existeChave(this.mapaEmailPesquisador, email, "Pesquisador nao encontrado");
		if (!pesquisadorEhAtivo(email)) {
			throw new IllegalArgumentException("Pesquisador inativo.");
		}
		if (atributo.equals("nome")) {
			verificador.verificaEntrada(novoValor, "Campo nome nao pode ser nulo ou vazio.");
			this.mapaEmailPesquisador.get(email).setNome(novoValor);
			return;
		}
		if (atributo.equals("funcao")) {
			verificador.verificaEntrada(novoValor, "Campo funcao nao pode ser nulo ou vazio.");
			this.mapaEmailPesquisador.get(email).setFuncao(novoValor);
		}
		if (atributo.equals("biografia")) {
			verificador.verificaEntrada(novoValor, "Campo biografia nao pode ser nulo ou vazio.");
			this.mapaEmailPesquisador.get(email).setBiografia(novoValor);
		}
		if (atributo.equals("email")) {
			verificador.verificaEntrada(novoValor, "Campo email nao pode ser nulo ou vazio.");
			verificador.verificaEmail(novoValor, "Formato de email invalido.");
			this.mapaEmailPesquisador.put(novoValor, this.mapaEmailPesquisador.get(email));
			this.mapaEmailPesquisador.get(novoValor).setEmail(novoValor);
			this.mapaEmailPesquisador.remove(email);
		}
		if (atributo.equals("fotoURL")) {
			verificador.verificaEntrada(novoValor, "Campo fotoURL nao pode ser nulo ou vazio.");
			verificador.verificaFotoURL(novoValor, "Formato de foto invalido.");
			this.mapaEmailPesquisador.get(email).setFotoURL(novoValor);
		}
	}
	
	public String exibePesquisador(String email) {
		verificador.verificaEmail(email, "Formato de email invalido.");
		verificador.existeChave(this.mapaEmailPesquisador, email, "Pesquisador nao encontrado");
		if (!pesquisadorEhAtivo(email)) {
			throw new IllegalArgumentException("Pesquisador inativo.");
		}
		return this.mapaEmailPesquisador.get(email).toString();
	}
	
	public void ativaPesquisador(String email) {
		verificador.verificaEmail(email, "Formato de email invalido.");
		verificador.existeChave(this.mapaEmailPesquisador, email, "Pesquisador nao encontrado");
		if (pesquisadorEhAtivo(email)) {
			throw new IllegalArgumentException("Pesquisador ja ativado.");
		}
		this.mapaEmailPesquisador.get(email).setAtividade("Ativo");
	}
	
	public void desativaPesquisador(String email) {
		verificador.verificaEmail(email, "Formato de email invalido.");
		verificador.existeChave(this.mapaEmailPesquisador, email, "Pesquisador nao encontrado");
		if (!pesquisadorEhAtivo(email)) {
			throw new IllegalArgumentException("Pesquisador inativo.");
		}
		this.mapaEmailPesquisador.get(email).setAtividade("Inativo");
	}
	
	public boolean pesquisadorEhAtivo(String email) {
		verificador.verificaEntrada(email, "Email nao pode ser vazio ou nulo.");
		verificador.verificaEmail(email, "Formato de email invalido.");
		verificador.existeChave(this.mapaEmailPesquisador, email, "Pesquisador nao encontrado");
		if (this.mapaEmailPesquisador.get(email).getAtividade().equals("Ativo")) {
			return true;
		}
		return false;
	}
}
