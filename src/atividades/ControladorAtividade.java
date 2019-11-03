package atividades;

import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import sistema.BuscadorPalavra;
import sistema.Verificador;

/**
 * Classe responsavel por controlar as informacoes referentes as atividades.
 * Todo ControladorAtividade possui um mapa de atividades e um codigo gerado
 * automaticamente que serve como identificador unico para a atividade.
 * 
 * @author Ricardo A. S. Sena
 *
 */ 
public class ControladorAtividade {
	
	/*
	 * Mapa de atividades do tipo <String, Atividade> 
	 */
	HashMap<String, Atividade> atividades;
	/**
	 * Codigo unico de cada atividade, o codigo ja eh iniciado com o valor 1
	 */
	int codigo;

	/**
	 * Construtor do objeto ControladorAtividade
	 */
	public ControladorAtividade() {
		atividades = new HashMap<String, Atividade>();
		this.codigo = 1;
	}

	/**
	 * Cadastra um objeto do tipo Atividade no mapa de atividades
	 * 
	 * @param descricao,      a descricao da atividade que sera cadastrada
	 * @param nivelRisco,     o nivel de risco da atividade que sera cadastrada
	 * @param descricaoRisco, a descricao do risco da atividade
	 * @return retorna o codigo da atividade cadastrada
	 */
	public String cadastraAtividade(String descricao, String nivelRisco, String descricaoRisco) {
		Verificador.verificaEntrada(descricao, "Campo Descricao nao pode ser nulo ou vazio.");
		Verificador.verificaEntrada(nivelRisco, "Campo nivelRisco nao pode ser nulo ou vazio.");
		if (!nivelRisco.equalsIgnoreCase("BAIXO") && !nivelRisco.equalsIgnoreCase("MEDIO")
				&& !nivelRisco.equalsIgnoreCase("ALTO")) {
			throw new IllegalArgumentException("Valor invalido do nivel do risco.");
		}
		Verificador.verificaEntrada(descricaoRisco, "Campo descricaoRisco nao pode ser nulo ou vazio.");
		Atividade atividade = new Atividade(descricao, nivelRisco, descricaoRisco, Period.ofDays(8), "A" + codigo);
		this.atividades.put("A" + codigo, atividade);
		String retorno = "A" + codigo;
		this.codigo++;
		return retorno;
	}

	/**
	 * Remove um objeto do tipo atividade do mapa de atividades
	 * 
	 * @param codigo , o codigo da atividade que sera removida
	 */
	public void apagaAtividade(String codigo) {
		Verificador.verificaEntrada(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		if (!existeAtividade(codigo)) {
			throw new IllegalArgumentException("Atividade nao encontrada");
		} else {
			atividades.remove(codigo);
		}
	}

	/**
	 * Cadastra um novo item a uma atividade presente no mapa de atividades
	 * 
	 * @param codigo, o codigo da atividade que recebera o item
	 * @param item,   o item que sera cadastrado na atividade
	 */
	public void cadastraItem(String codigo, String item) {
		Verificador.verificaEntrada(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		Verificador.verificaEntrada(item, "Item nao pode ser nulo ou vazio.");
		if (!existeAtividade(codigo)) {
			throw new IllegalArgumentException("Atividade nao encontrada");
		} else {
			capturaAtividadeNoMapa(codigo).adicionaItem(item);
		}
	}

	/**
	 * Exibe uma atividade presente no mapa
	 * 
	 * @param codigo, o codigo da atividade que sera exibida
	 * @return retorna a representacao em string da atividade
	 */
	public String exibeAtividade(String codigo) {
		if (!existeAtividade(codigo)) {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}
		return capturaAtividadeNoMapa(codigo).toString();
	}

	/**
	 * Conta o total de itens com status de pendente em uma atividade
	 * 
	 * @param codigo, o codigo da atividade a qual os itens pertencem
	 * @return retorna um inteiro representando a soma de itens com status pendente
	 */
	public int contaItensPendentes(String codigo) {
		Verificador.verificaEntrada(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		if (!existeAtividade(codigo)) {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}
		return capturaAtividadeNoMapa(codigo).contaItensPendentes();
	}

	/**
	 * Conta o total de itens com status de realizado em uma atividade
	 * 
	 * @param codigo, o codigo da atividade ao qual os itens pertencem
	 * @return retorna um inteiro representando a soma dos itens com status
	 *         realizado
	 */
	public int contaItensRealizados(String codigo) {
		Verificador.verificaEntrada(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		if (!existeAtividade(codigo)) {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}
		return capturaAtividadeNoMapa(codigo).contaItensRealizados();
	}

	// todo o codigo abaixo se refere a acoes basicas no mapa de atividades
	private boolean existeAtividade(String codigo) {
		// retorna se ja existe ou nao a atividade no mapa
		return this.atividades.containsKey(codigo);
	}

	private Atividade capturaAtividadeNoMapa(String codigo) {
		return this.atividades.get(codigo);
	}
	public void ProcurarPalavra(String palavra) {
		Verificador.verificaEntrada(palavra, "Campo termo nao pode ser nulo ou vazio.");
		List <Atividade> listaAtividades = new ArrayList<>(this.atividades.values());
		Collections.sort(listaAtividades, new ComparadorAtividade());
		for(Atividade atividade : listaAtividades) {
			BuscadorPalavra.adicionaEncontrado(BuscadorPalavra.procuraPalavra(palavra,atividade.getCodigo()+": "+atividade.getDescricao()));
			BuscadorPalavra.adicionaEncontrado(BuscadorPalavra.procuraPalavra(palavra,atividade.getCodigo()+": "+atividade.getDescricaoRisco()));
			atividade.pesquisaItem(palavra);
		}
	}
}
