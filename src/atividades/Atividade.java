package atividades;

import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private Period days;
	/**
	 * mapa dos itens pertencentes a atividade
	 */
	private Map<Integer, Item> itens = new HashMap<Integer, Item>();

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
	 *            a duracao em dias da aatividade
	 */
	public Atividade(String descricao, String nivelRisco, String descricaoRisco, Period days) {
		// super();
		this.descricao = descricao;
		this.nivelRisco = nivelRisco;
		this.descricaoRisco = descricaoRisco;
		this.days = days;
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

	public Period getDays() {
		return days;
	}

	public void setDays(Period days) {
		this.days = days;
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
				lista = lista + " | PENDENTE - " + item.getDescricao();
			} else {
				lista = lista + " |REALIZADO - " + item.getDescricao();
			}
		}
		return descricao + " (" + nivelRisco + " - " + descricaoRisco + ")" + lista;
	}

}
