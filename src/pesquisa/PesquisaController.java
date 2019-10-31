package pesquisa;

import java.util.HashMap;
import java.util.Map;
import sistema.Verificador;
/**
 * 
 * @author Beatriz Truta
 *
 */ 
public class PesquisaController {
	/**
	 * mapa de pesquisas cadastradas no sistema
	 */
	private Map<String, Pesquisa> mapaPesquisas; 
	/**
	 * identificador unico da pesquisa
	 */
	private int idPesquisa = 0;
	/**
	 * construtor da classe
	 */
	public PesquisaController() {
		this.mapaPesquisas = new HashMap<>();
	}
	/**
	 * Adiciona uma nova pesquisa no mapa de pesquisas
	 * @param descricao, a descricao da pesquisa
	 * @param interesse, o campo de interesse da pesquisa
	 * @return retornar o codigo de cadastro da pesquisa
	 */
	public String cadastraPesquisa(String descricao, String interesse) {
		verificaInteresseValido(interesse);
		Verificador.verificaEntrada(descricao, "Descricao nao pode ser nula ou vazia.");		
		String codigo = "";
		for (int i=0; i<3; i++) {
			codigo += interesse.charAt(i);
		}
		codigo = codigo + (idPesquisa+1);
		codigo = geraId(codigo.toUpperCase());
		this.mapaPesquisas.put(codigo, new Pesquisa(descricao, interesse, codigo));
		return codigo;
	}
	/**
	 * Altera algum dado especifico de uma pesquisa ja cadastrada
	 * @param codigo, o codigo da pesquisa que sera alterada
	 * @param conteudoASerAlterado , o parametro que sera alterado
	 * @param novoConteudo, o novo valor que o parametro alterado recebera
	 */
	public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {
		if (!this.mapaPesquisas.containsKey(codigo)) 
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		if (!pesquisaEhAtiva(codigo))
			throw new IllegalArgumentException("Pesquisa desativada.");
		if (conteudoASerAlterado.equals("descricao") || conteudoASerAlterado.equals("DESCRICAO")) {
			Verificador.verificaEntrada(novoConteudo, "Descricao nao pode ser nula ou vazia.");
			this.mapaPesquisas.get(codigo).setDescricao(novoConteudo);
		} else if (conteudoASerAlterado.equals("campo") || conteudoASerAlterado.equals("CAMPO")) {
			Verificador.verificaEntrada(novoConteudo, "Formato do campo de interesse invalido.");
			this.mapaPesquisas.get(codigo).setCampo(novoConteudo);
		} else {
			throw new IllegalArgumentException("Nao e possivel alterar esse valor de pesquisa.");
		}
	}
	/**
	 * Exibe como string uma pesquisa solicitada
	 * @param codigo , o codigo da pesquisa que sera exibido
	 * @return a representacao em string da pesquisa
	 */
	public String exibePesquisa(String codigo) {
		if (!this.mapaPesquisas.containsKey(codigo))
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		if (!pesquisaEhAtiva(codigo))
			throw new IllegalArgumentException("Pesquisa inativa.");
		return this.mapaPesquisas.get(codigo).toString();
	}
	/**
	 * Altera o status de uma pesquisa cadastrada para ativado
	 * @param codigo, o codigo da pesquisa que sera ativada
	 */
	public void ativaPesquisa(String codigo) {
		if (!this.mapaPesquisas.containsKey(codigo))
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		if (pesquisaEhAtiva(codigo))
			throw new IllegalArgumentException("Pesquisa ja ativada.");
		this.mapaPesquisas.get(codigo).setStatus("Ativa");
	}
	/**
	 * Altera o status de uma pesquisa para encerrado
	 * @param codigo, o codigo da pesquisa que sera encerrada
	 * @param motivo, o motivo do encerramento da pesquisa
	 */
	public void encerraPesquisa(String codigo, String motivo) {
		Verificador.verificaEntrada(motivo, "Motivo nao pode ser nulo ou vazio.");
		if (!this.mapaPesquisas.containsKey(codigo))
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		if (!pesquisaEhAtiva(codigo))
			throw new IllegalArgumentException("Pesquisa desativada.");
		this.mapaPesquisas.get(codigo).setStatus("Inativa");
	}
	
	/**
	 * Verifica se uma pesquisa esta ativa
	 * @param codigo, o codigo da pesquisa que sera verificado
	 * @return retorna true ou false de acordo com o status da pesquisa
	 */
	public boolean pesquisaEhAtiva(String codigo) {
		Verificador.verificaEntrada(codigo, "Codigo nao pode ser nulo ou vazio.");
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
	/**
	 *Verifica se o campo interessa da pesquisa segue os requisitos de criacao
	 * @param interesse , o campo de interesse da pesquisa
	 */
	private void verificaInteresseValido(String interesse) {
		Verificador.verificaEntrada(interesse, "Formato do campo de interesse invalido.");
		if (interesse.length()>255 || interesse.length()<3)
			throw new IllegalArgumentException("Formato do campo de interesse invalido.");
		String[] interesses = interesse.split(",");
		if (interesses.length>4)
			throw new IllegalArgumentException("Formato do campo de interesse invalido.");
		for (String i:interesses) {
			if (i.trim().equals("")) {
				throw new IllegalArgumentException("Formato do campo de interesse invalido.");
			}
		}
	}
	/**
	 * Gera um id unico para a pesquisa
	 * @param id, o id da pesquisa
	 * @return retorna o id da pesquisa
	 */
	private String geraId(String id) {
		if (mapaPesquisas.containsKey(id)) {
			int indice = (int) id.charAt(id.length()-1);
			indice++;
			return id.replace(id.charAt(id.length()-1), (char) indice);
		}
		return id;
	}
}