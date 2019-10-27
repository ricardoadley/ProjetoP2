package atividades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Atividade {
	private int ordemCadastroItem =1;
	private String descricao;
	private String nivelRisco;
	private String descricaoRisco;
	private Map <Integer,Item> itens = new HashMap<Integer,Item>();
	//private ArrayList<String> itens = new ArrayList<String>();
	public Atividade(String descricao, String nivelRisco, String descricaoRisco) {
		//super();
		this.descricao = descricao;
		this.nivelRisco = nivelRisco;
		this.descricaoRisco = descricaoRisco;
	}
	public void adicionaItem(String item) {
		Item it = new Item(item,ordemCadastroItem);
		itens.put(ordemCadastroItem, it);
		ordemCadastroItem ++;
	}
	public int contaItensPendentes() {
		int contador =0;
		List<Item> itens = new ArrayList<>(this.itens.values());
		for(Item item : itens) {
			if(item.isRealizado() == false) {
				contador++;
			}
		}
		return contador;
		
	}
	public int contaItensRealizados() {
		int contador =0;
		List<Item> itens = new ArrayList<>(this.itens.values());
		for(Item item : itens) {
			if(item.isRealizado() == true) {
				contador++;
			}
		}
		return contador;
	}
	public void removeItem(String item) {
		
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
	@Override
	public String toString() {
		return descricao +" ("+nivelRisco +" - "+ descricaoRisco +")";
	}
	
}
