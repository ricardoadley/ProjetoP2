package atividades;

import java.time.Period;
import java.util.HashMap;

import sistema.Verificador;

/**
 * 
 * @author Ricardo A. S. Sena
 *
 */
public class ControladorAtividade {
	/**
	 * 
	 */
	Verificador verifica = new Verificador();
	/**
	 * 
	 */
	HashMap<String, Atividade> atividades;
	/**
	 * 
	 */
	int codigo = 1;

	/**
	 * 
	 */
	public ControladorAtividade() {
		atividades = new HashMap<String, Atividade>();
	}

	/**
	 * 
	 * @param descricao
	 * @param nivelRisco
	 * @param descricaoRisco
	 * @return
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

	public void apagaAtividade(String codigo) {
		verifica.verificaEntrada(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		if (!existeAtividade(codigo)) {
			throw new IllegalArgumentException("Atividade nao encontrada");
		} else {
			atividades.remove(codigo);
		}
	}

	public void cadastraItem(String codigo, String item) {
		verifica.verificaEntrada(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		verifica.verificaEntrada(item, "Item nao pode ser nulo ou vazio.");
		if (!existeAtividade(codigo)) {
			throw new IllegalArgumentException("Atividade nao encontrada");
		} else {
			capturaAtividadeNoMapa(codigo).adicionaItem(item);
		}
	}

	public String exibeAtividade(String codigo) {
		if (!existeAtividade(codigo)) {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}
		return capturaAtividadeNoMapa(codigo).toString();
	}

	public int contaItensPendentes(String codigo) {
		verifica.verificaEntrada(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		if (!existeAtividade(codigo)) {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}
		return capturaAtividadeNoMapa(codigo).contaItensPendentes();
	}

	public int contaItensRealizados(String codigo) {
		verifica.verificaEntrada(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		if (!existeAtividade(codigo)) {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}
		return capturaAtividadeNoMapa(codigo).contaItensRealizados();
	}

	// mapa
	public boolean existeAtividade(String codigo) {
		// retorna se ja existe ou nao a atividade no mapa
		return this.atividades.containsKey(codigo);
	}

	public Atividade capturaAtividadeNoMapa(String codigo) {
		return this.atividades.get(codigo);
	}
}
