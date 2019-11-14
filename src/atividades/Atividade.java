package atividades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import sistema.Verificador;

/**
 * Representacao de uma atividade, especificamente de uma pesquisa cadastrada
 * por um usuario no sistema
 * 
 * @author Ricardo A. S. Sena
 *
 */

public class Atividade {
	/**
	 * A ordem de cadastro dos itens da atividade
	 */
	private int ordemCadastroItem = 1;
	/**
	 * a descricao da atividade criada
	 */
	private String descricao;
	/**
	 * o nivel de risco da atividade criada
	 */
	private String nivelRisco;
	/**
	 * a descricao do risco da atividade
	 */
	private String descricaoRisco;
	/**
	 * o periodo de duracao da atividade em dias
	 */
	private int duracao;
	/**
	 * Resultados da atividade
	 */
	private Map<Integer, String> resultados;
	/**
	 * 
	 */
	private int ultimoResultado;

	private String codigo;

	/**
	 * mapa dos itens pertencentes a atividade
	 */
	private Map<Integer, Item> itens = new HashMap<Integer, Item>();
	/**
	 * Lista com o nome das pesquisas associadas a atividade
	 */
	private List<String> pesquisasAssociadas;

	/**
	 * Constroi uma nova atividade a partir dos parametros informados pelo usuario
	 * 
	 * @param descricao,
	 *            a descricao da atividade
	 * @param nivelRisco,
	 *            o nivel de risco da atividade
	 * @param descricaoRisco,
	 *            a descricao do risco da atividade
	 * @param days,
	 *            a duracao em dias da atividade
	 */
	public Atividade(String descricao, String nivelRisco, String descricaoRisco, int duracao, String codigo) {
		// super();
		Verificador.verificaEntrada(descricao, "Campo Descricao nao pode ser nulo ou vazio.");
		Verificador.verificaEntrada(nivelRisco, "Campo nivelRisco nao pode ser nulo ou vazio.");
		Verificador.verificaEntrada(descricaoRisco, "Campo descricaoRisco nao pode ser nulo ou vazio.");
		this.descricao = descricao;
		this.nivelRisco = nivelRisco;
		this.descricaoRisco = descricaoRisco;
		this.duracao = duracao;
		this.codigo = codigo;
		this.resultados = new HashMap<>();
		this.ultimoResultado = 0;
		this.pesquisasAssociadas = new ArrayList<String>();
	}

	/**
	 * Adiciona um novo item ao mapa de itens
	 * 
	 * @param item,
	 *            o item que sera adicionado
	 */
	public void adicionaItem(String item) {
		Item it = new Item(item, ordemCadastroItem);
		itens.put(ordemCadastroItem, it);
		ordemCadastroItem++;
	}

	/**
	 * Conta o total de itens como pendentes da atividade
	 * 
	 * @return retorna o valor em inteiro dos intes com status pendente
	 */
	public int contaItensPendentes() {
		int contador = 0;
		List<Item> itens = new ArrayList<>(this.itens.values());
		for (Item item : itens) {
			if (item.isRealizado() == false) {
				contador++;
			}
		}
		return contador;

	}

	/**
	 * Conta o total de itens realizados na pesquisa
	 * 
	 * @return retorna o valor em inteiro dos itens com status realizado
	 */
	public int contaItensRealizados() {
		int contador = 0;
		List<Item> itens = new ArrayList<>(this.itens.values());
		for (Item item : itens) {
			if (item.isRealizado() == true) {
				contador++;
			}
		}
		return contador;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNivelRisco() {
		return nivelRisco;
	}

	public void setNivelRisco(String nivelRisco) {
		this.nivelRisco = nivelRisco;
	}

	public String getDescricaoRisco() {
		return descricaoRisco;
	}

	public void setDescricaoRisco(String descricaoRisco) {
		this.descricaoRisco = descricaoRisco;
	}

	public int getduracao() {
		return duracao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setduracao(int duracao) {
		this.duracao = duracao;
	}

	@Override
	/**
	 * Retorna a string que representa a pesquisa no formato "DESCRICAO (NIVEL RISCO
	 * - DESCRICAO RISCO)
	 */
	public String toString() {
		String lista = "";
		List<Item> itens = new ArrayList<>(this.itens.values());
		for (Item item : itens) {
			if (item.isRealizado() == false) {
				lista += " | PENDENTE - " + item.getDescricao();
			} else {
				lista += " |REALIZADO - " + item.getDescricao();
			}
		}
		return descricao + " (" + nivelRisco + " - " + descricaoRisco + ")" + lista;
	}

	/**
	 * Pesquisa se o termo informado pelo usuario esta presente nos itens da
	 * atividade
	 * 
	 * @param palavra
	 *            , a palavra, informada pelo usuario, que sera procurada
	 * @return retorna a string com os resultados encontrados
	 */
	public List<String> pesquisaItem(String palavra) {
		List<Item> listaItens = new ArrayList<>(this.itens.values());
		String frase = "";
		List<String> resultado = new ArrayList<String>();
		for (Item Item : listaItens) {
			frase = this.codigo + ": " + Item.getDescricao();
			if (frase.toLowerCase().contains(palavra)) {
				resultado.add(frase);
			}
		}
		return resultado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atividade other = (Atividade) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	/**
	 * Executa a atividade, realizando um dos itens e incremetando a duracao
	 * 
	 * @param item
	 *            item a ser realizado
	 * @param duracao
	 *            duracao a ser incrementada
	 */
	public void executaAtividade(int item, int duracao) {
		Verificador.existeChave(itens, item, "Item nao encontrado.");
		if (pesquisasAssociadas.isEmpty()) {
			throw new IllegalArgumentException("Atividade sem associacoes com pesquisas.");
		}
		if (itens.get(item).getRealizado()) {
			throw new IllegalArgumentException("Item ja executado.");
		}
		this.itens.get(item).setRealizado(true);
		this.duracao += duracao;
	}

	/**
	 * Cadastra um resultado na atividade.
	 * 
	 * @param resultado
	 *            resultado a ser cadastrado
	 * @return o ID do resultado cadastrado
	 */
	public int cadastraResultado(String resultado) {
		this.ultimoResultado++;
		this.resultados.put(ultimoResultado, resultado);
		return this.ultimoResultado;
	}

	/**
	 * Remove um resultado cadastrado anteriormente
	 * 
	 * @param numeroResultado
	 *            numero do resultado
	 * @return true
	 */
	public boolean removeResultado(int numeroResultado) {
		Verificador.existeChave(resultados, numeroResultado, "Resultado nao encontrado.");
		this.resultados.remove(numeroResultado);
		return true;
	}

	/**
	 * Retorna uma representacao em texto dos resultados cadastrados
	 * 
	 * @return representacao em texto dos resultados
	 */
	public String listaResultados() {
		String resultadosListados = "";
		for (String resultado : resultados.values()) {
			resultadosListados += resultado + " | ";
		}
		return resultadosListados.substring(0, resultadosListados.length() - 3);
	}

	/**
	 * Armazena o codigo de uma pesquisa em uma lista
	 * 
	 * @param codigoPesquisa
	 *            codigo da pesquisa a ser armazenado
	 */
	public void associaPesquisa(String codigoPesquisa) {
		this.pesquisasAssociadas.add(codigoPesquisa);
	}

	/**
	 * Remove o codigo de uma pesquisa em da lista de pesquisas
	 * 
	 * @param codigoPesquisa
	 *            codigo da pesquisa a ser removida
	 */
	public void desassociaPesquisa(String codigoPesquisa) {
		this.pesquisasAssociadas.remove(codigoPesquisa);
	}
}
