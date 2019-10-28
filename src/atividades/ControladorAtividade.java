package atividades;

import java.time.Period;
import java.util.HashMap;

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
	/**
	 * Instancia a classe de lancamento de excessoes
	 */
	Verificador verifica = new Verificador();
	/**
	 * Mapa de atividades do tipo <String, Atividade>
	 */
	HashMap<String, Atividade> atividades;
	/**
	 * Codigo unico de cada atividade, o codigo ja é iniciado com o valor 1
	 */
	int codigo = 1;

	/**
	 * Construtor do objeto ControladorAtividade
	 */
	public ControladorAtividade() {
		atividades = new HashMap<String, Atividade>();
	}

	/**
	 * Cadastra um objeto do tipo Atividade no mapa de atividades
	 * 
	 * @param descricao,
	 *            a descricao da atividade que sera cadastrada
	 * @param nivelRisco,
	 *            o nivel de risco da atividade que sera cadastrada
	 * @param descricaoRisco,
	 *            a descricao do risco da atividade
	 * @return retorna o codigo da atividade cadastrada
	 */
	public String cadastraAtividade(String descricao, String nivelRisco, String descricaoRisco) {
		verifica.verificaEntrada(descricao, "Campo Descricao nao pode ser nulo ou vazio.");
		verifica.verificaEntrada(nivelRisco, "Campo nivelRisco nao pode ser nulo ou vazio.");
		if (!nivelRisco.equalsIgnoreCase("BAIXO") && !nivelRisco.equalsIgnoreCase("MEDIO")
				&& !nivelRisco.equalsIgnoreCase("ALTO")) {
			throw new IllegalArgumentException("Valor invalido do nivel do risco.");
		}
		verifica.verificaEntrada(descricaoRisco, "Campo descricaoRisco nao pode ser nulo ou vazio.");
		Atividade atividade = new Atividade(descricao, nivelRisco, descricaoRisco, Period.ofDays(8));
		atividades.put("A" + codigo, atividade);
		String retorno = "A" + codigo;
		codigo++;
		return retorno;
	}

	/**
	 * Remove um objeto do tipo atividade do mapa de atividades
	 * 
	 * @param codigo
	 *            , o codigo da atividade que sera removida
	 */
	public void apagaAtividade(String codigo) {
		verifica.verificaEntrada(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		if (!existeAtividade(codigo)) {
			throw new IllegalArgumentException("Atividade nao encontrada");
		} else {
			atividades.remove(codigo);
		}
	}

	/**
	 * Cadastra um novo item a uma atividade presente no mapa de atividades
	 * 
	 * @param codigo,
	 *            o codigo da atividade que recebera o item
	 * @param item,
	 *            o item que sera cadastrado na atividade
	 */
	public void cadastraItem(String codigo, String item) {
		verifica.verificaEntrada(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		verifica.verificaEntrada(item, "Item nao pode ser nulo ou vazio.");
		if (!existeAtividade(codigo)) {
			throw new IllegalArgumentException("Atividade nao encontrada");
		} else {
			capturaAtividadeNoMapa(codigo).adicionaItem(item);
		}
	}

	/**
	 * Exibe uma atividade presente no mapa
	 * 
	 * @param codigo,
	 *            o codigo da atividade que sera exibida
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
	 * @param codigo,
	 *            o codigo da atividade a qual os itens pertencem
	 * @return retorna um inteiro representando a soma de itens com status pendente
	 */
	public int contaItensPendentes(String codigo) {
		verifica.verificaEntrada(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		if (!existeAtividade(codigo)) {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}
		return capturaAtividadeNoMapa(codigo).contaItensPendentes();
	}

	/**
	 * Conta o total de itens com status de realizado em uma atividade
	 * 
	 * @param codigo,
	 *            o codigo da atividade ao qual os itens pertencem
	 * @return retorna um inteiro representando a soma dos itens com status
	 *         realizado
	 */
	public int contaItensRealizados(String codigo) {
		verifica.verificaEntrada(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		if (!existeAtividade(codigo)) {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}
		return capturaAtividadeNoMapa(codigo).contaItensRealizados();
	}

	// todo o codigo abaixo se refere a acoes basicas no mapa de atividades
	public boolean existeAtividade(String codigo) {
		// retorna se ja existe ou nao a atividade no mapa
		return this.atividades.containsKey(codigo);
	}

	public Atividade capturaAtividadeNoMapa(String codigo) {
		return this.atividades.get(codigo);
	}
}
