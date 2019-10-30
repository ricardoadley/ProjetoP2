package pesquisa;

import java.util.HashMap;
import java.util.Map;
import sistema.Verificador;

public class PesquisaController {
	
	private Map<String, Pesquisa> mapaPesquisas;
	private int idPesquisa = 0;
	private Verificador verificador;
	
	public PesquisaController() {
		this.mapaPesquisas = new HashMap<>();
	}
	
	public String cadastraPesquisa(String descricao, String interesse) {
		verificaInteresseValido(interesse);
		if (descricao == null || descricao.trim().equals("")) {
			throw new IllegalArgumentException("Descricao nao pode ser nula ou vazia.");
		}
		
		String codigo = "";
		for (int i=0; i<3; i++) {
			codigo += interesse.charAt(i);
		}
		codigo = codigo + (idPesquisa+1);
		codigo = geraId(codigo.toUpperCase());
		this.mapaPesquisas.put(codigo, new Pesquisa(descricao, interesse));
		return codigo;
	}
	
/*expectError "Pesquisa nao encontrada." alteraPesquisa codigo="ENE1" conteudoASerAlterado="DESCRICAO" novoConteudo="Modelagem do motor de inducao em estudos de estabilidade de tensao"
expectError "Nao e possivel alterar esse valor de pesquisa." alteraPesquisa codigo="COM1" conteudoASerAlterado="CaMpO" novoConteudo="age of aquarius"
expectError "Pesquisa desativada." alteraPesquisa codigo="PSI1" conteudoASerAlterado="CAMPO" novoConteudo="age of aquarius"
expectError "Descricao nao pode ser nula ou vazia." alteraPesquisa codigo="COM1" conteudoASerAlterado="DESCRICAO" novoConteudo=""
expectError "Formato do campo de interesse invalido." alteraPesquisa codigo="COM1" conteudoASerAlterado="CAMPO" novoConteudo=""
alteraPesquisa codigo="ELE1" conteudoASerAlterado="DESCRICAO" novoConteudo="Aumento da evasao no numero de eleitores paraibanos."
alteraPesquisa codigo="ELE1" conteudoASerAlterado="CAMPO" novoConteudo="eleicao, paraiba"
expect "ELE1 - Aumento da evasao no numero de eleitores paraibanos. - eleicao, paraiba" exibePesquisa codigo="ELE1" */
	
	public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {
		if (!this.mapaPesquisas.containsKey(codigo)) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
			}
		if (!pesquisaEhAtiva(codigo)) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}		
		if (conteudoASerAlterado.equals("descricao") || conteudoASerAlterado.equals("DESCRICAO")) {
			if (novoConteudo.trim().equals("")) {
				throw new IllegalArgumentException("Descricao nao pode ser nula ou vazia.");
			}else {
				this.mapaPesquisas.get(codigo).setDescricao(novoConteudo);
			}
		}else if (conteudoASerAlterado.equals("campo") || conteudoASerAlterado.equals("CAMPO")) {
			if (novoConteudo.trim().equals("")) {
				throw new IllegalArgumentException("Formato do campo de interesse invalido.");
			}else {
				this.mapaPesquisas.get(codigo).setCampo(novoConteudo);
			}
		}else {
			throw new IllegalArgumentException("Nao e possivel alterar esse valor de pesquisa.");
		}
	}
	
	public String exibePesquisa(String codigo) {
		if (!this.mapaPesquisas.containsKey(codigo)) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
			}
		if (!pesquisaEhAtiva(codigo)) {
			throw new IllegalArgumentException("Pesquisa inativa.");
		}
		return this.mapaPesquisas.get(codigo).toString(codigo);
	}
	
	public void ativaPesquisa(String codigo) {
		if (!this.mapaPesquisas.containsKey(codigo)) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
			}
		if (pesquisaEhAtiva(codigo)) {
			throw new IllegalArgumentException("Pesquisa ja ativada.");
		}
		this.mapaPesquisas.get(codigo).setStatus("Ativa");
	}
	
	public void encerraPesquisa(String codigo, String motivo) {
		if (!this.mapaPesquisas.containsKey(codigo)) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
			}	
		if (!pesquisaEhAtiva(codigo)) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
		this.mapaPesquisas.get(codigo).setStatus("Inativa");
	}
	
	
	public boolean pesquisaEhAtiva(String codigo) {
		if (codigo == null || codigo.trim().equals("")) {
			throw new IllegalArgumentException("Codigo nao pode ser nulo ou vazio.");
		}
		if (!this.mapaPesquisas.containsKey(codigo)) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}else {
			if (this.mapaPesquisas.get(codigo).getStatus().equals("Ativa")) {
				return true;
			}else {
				return false;
			}
		}
	}
	
	private void verificaInteresseValido(String interesse) {
		if (interesse == null || interesse.trim().equals("")) {
			throw new IllegalArgumentException("Formato do campo de interesse invalido.");
		}
		if (interesse.length()>255 || interesse.length()<3) {
			throw new IllegalArgumentException("Formato do campo de interesse invalido.");
		}
		String[] interesses = interesse.split(",");
		if (interesses.length>4) {
			throw new IllegalArgumentException("Formato do campo de interesse invalido.");
		}
		for (String i:interesses) {
			if (i.trim().equals("")) {
				throw new IllegalArgumentException("Formato do campo de interesse invalido.");
			}
		}
	}
	
	private String geraId(String id) {
		if (mapaPesquisas.containsKey(id)) {
			int indice = (int) id.charAt(id.length()-1);
			indice++;
			return id.replace(id.charAt(id.length()-1), (char) indice);
		}
		return id;
	}
}