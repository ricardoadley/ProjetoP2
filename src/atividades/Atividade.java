package atividades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import sistema.Verificador;

/**
 * Representacao de uma atividade, especificamente de uma pesquisa cadastrada
 * por um usuario no sistema
 * 
 * @author Ricardo A. S. Sena, Ana Beatriz da S. Truta.
 *
 */
public class Atividade implements Comparable<Atividade>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5554254115400970053L;
	/**
	 * A ordem de cadastro dos itens da atividade
	 */
	private int ordemCadastroItem;
	/**
	 * A descricao da atividade criada
	 */
	private String descricao;
	/**
	 * O nivel de risco da atividade criada
	 */
	private String nivelRisco;
	/**
	 * A descricao do risco da atividade
	 */
	private String descricaoRisco;
	/**
	 * O periodo de duracao da atividade em dias
	 */
	private int duracao;
	/**
	 * Resultados da atividade
	 */
	private Map<Integer, String> resultados;
	/**
	 * O ultimo resultado da Atividade
	 */
	private int ultimoResultado;

	/**
	 * Codigo que identifica unicamente a Atividade
	 */
	private String codigo;

	/**
	 * Mapa dos itens pertencentes a atividade
	 */
	private Map<Integer, Item> itens = new HashMap<Integer, Item>();
	/**
	 * Lista com o nome das pesquisas associadas a atividade
	 */
	private List<String> pesquisasAssociadas;
	private Atividade prox;

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
		this.ordemCadastroItem = 1;
		this.descricao = descricao;
		this.nivelRisco = nivelRisco;
		this.descricaoRisco = descricaoRisco;
		this.duracao = duracao;
		this.codigo = codigo;
		this.resultados = new HashMap<>();
		this.ultimoResultado = 0;
		this.pesquisasAssociadas = new ArrayList<String>();
		this.prox = null;
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

	public String getDescricaoRisco() {
		return descricaoRisco;
	}

	public int getduracao() {
		return duracao;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public String getNivelRisco() {
		return nivelRisco;
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
		if (itens.get(item).isRealizado()) {
			throw new IllegalArgumentException("Item ja executado.");
		}
		this.itens.get(item).setDuracao(duracao);
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

	/**
	 * Verifica se contem alguma pesquisa associada a Atividade.
	 * 
	 * @return true se conter pesquisa associada, false caso contrario
	 */
	public boolean contemPesquisasAssociadas() {
		if (this.pesquisasAssociadas.size() > 0) {
			return true;
		}

		return false;
	}

	@Override
	public int compareTo(Atividade atividade) {
		return this.getCodigo().compareTo(atividade.getCodigo());
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
		return this.descricao + " (" + this.nivelRisco + " - " + this.descricaoRisco + ")" + lista;
	}

	/**
	 * Gera um resumo de Atividade. Retorna descricao, nivel do risco, descricao do
	 * risco e detalhes dos itens associados
	 * 
	 * @return retorna os detalhes de Atividade
	 */
	public String geraResumo() {
		String resumo = "";
		List<Item> itens = new ArrayList<>(this.itens.values());
		Collections.sort(itens);
		for (int i = 0; i < itens.size(); i++) {
			if (itens.get(i).isRealizado() == false) {
				resumo += "            - PENDENTE - " + itens.get(i).toString() + System.lineSeparator();
			} else {
				resumo += "            - REALIZADO - " + itens.get(i).toString() + System.lineSeparator();
			}

		}
		return this.descricao + " (" + this.nivelRisco + " - " + this.descricaoRisco + ")" + System.lineSeparator()
				+ resumo;

	}

	/**
	 * Retorna os resultados da Atividade: itens e suas durações, além das
	 * descrições de Atividade.
	 * 
	 * @return os resultados da Atividade
	 */
	public String geraResultados() {

		String resultados = "        - " + this.descricao + System.lineSeparator();
		List<Item> itens = new ArrayList<>(this.itens.values());
		Collections.sort(itens);

		for (int i = 0; i < itens.size(); i++) {

			if (itens.get(i).isRealizado()) {

				resultados += "            - " + itens.get(i).toString() + " - " + itens.get(i).getDuracao()
						+ System.lineSeparator();
			}
		}

		for (Integer intResultado : this.resultados.keySet()) {

			resultados += "            - " + this.resultados.get(intResultado) + System.lineSeparator();

		}

		return resultados;

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
	 * Define qual a proxima atividade que a atividade atual ira apontar, nao
	 * podendo apontar para mais de uma atividade.
	 * 
	 * @param atividade
	 *            atividade que sera colocada como proxima.
	 */
	public void defineProximaAtividade(Atividade atividade) {
		if (this.prox != null) {
			throw new IllegalArgumentException("Atividade ja possui uma subsequente.");
		}
		if (ehLoop(atividade, this.codigo)) {
			throw new IllegalArgumentException("Criacao de loops negada.");
		}
		this.prox = atividade;
	}

	/**
	 * Conta a quantidade de proximos que a atividade possui.
	 * 
	 * @return a quantidade de proximas atividades.
	 */
	public int contaProximo() {
		if (this.prox == null) {
			return 0;
		}
		return 1 + this.prox.contaProximo();
	}

	/**
	 * Retira a atividade a qual a atividade atual aponta. Deixando assim, a
	 * atividade atual sem proximo.
	 */
	public void tiraProximaAtividade() {
		this.prox = null;
	}

	/**
	 * Pega uma determinda atividade a uma n distancia da atual.
	 * 
	 * @param enesimaAtividade
	 *            distancia entre as atividades.
	 * 
	 * @return retorna o codigo da atividade encontrada e caso isso nao ocorra lanca
	 *         um erro.
	 */
	public String pegaProximo(int enesimaAtividade) {
		if (enesimaAtividade == 0) {
			return codigo;
		}
		if (this.prox == null) {
			throw new IllegalArgumentException("Atividade inexistente.");
		}
		return this.prox.pegaProximo(enesimaAtividade - 1);
	}

	private Atividade pegaAtividadeMaiorRisco() {
		if (this.prox == null) {
			return this;
		}
		Atividade atv = this.prox.pegaAtividadeMaiorRisco();
		String risco = atv.getNivelRisco();
		if (risco.equals("ALTO")) {
			return atv;
		} else {
			if (this.nivelRisco.equals("ALTO"))
				return this;
			else if (this.nivelRisco.equals(risco))
				return atv;
			else if (this.nivelRisco.equals("MEDIO"))
				return this;
			else
				return atv;
		}
	}

	/**
	 * Pega a atividade com maior risco.
	 * 
	 * @return codigo da atividade com maior risco.
	 */
	public String pegaMaiorRiscoAtividades() {
		if (this.prox == null) {
			throw new IllegalArgumentException("Nao existe proxima atividade.");
		} else {
			return this.prox.pegaAtividadeMaiorRisco().getCodigo();
		}
	}

	/**
	 * Verifica se ao colocar a atividade como proximo nao ira formar um loop.
	 * 
	 * @param atividade
	 *            atividade que sera colocada como proximo.
	 * @param id
	 *            codigo da atividade atual
	 * 
	 * @return retorna true caso haja loop e false caso contrario.
	 */
	public boolean ehLoop(Atividade atividade, String id) {
		if (atividade.prox == null) {
			return false;
		}
		if (atividade.prox.getCodigo().equals(id)) {
			return true;
		}
		return ehLoop(atividade.prox, id);
	}

}