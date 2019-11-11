package pesquisa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import objetivo.Objetivo;
import problema.Problema;
import sistema.Verificador;

/**
 * Classe que representa uma Pesquisa. Toda Pesquisa possui uma descricao, um
 * campo, um status (ativada ou desativada), um codigo pelo qual e identificada
 * unicamente. Alem disso, tambem pode estar ou nao associada a um Problema ou a
 * um ou mais Objetivos.
 * 
 * @author Beatriz Truta, José Matheus do N. Gama
 *
 */
public class Pesquisa {
	/**
	 * a descricao da pesquisa
	 */
	private String descricao;
	/**
	 * O campo de interesse da pesquisa
	 */
	private String campo;
	/**
	 * o status atual da pesquisa
	 */
	private String status;
	/**
	 * o codigo da pesquisa, e tambem seu identificador
	 */
	private String codigo;
	/**
	 * Lista da atividades associadas a pesquisa
	 */
	private List<String> atividadesAssociadas;
	/**
	 * Problema ao qual a pesquisa esta associada.
	 */
	private Problema problema;
	/**
	 * Mapa de Objetivos associados a Pesquisa.
	 */
	private Map<String, Objetivo> objetivos;

	/**
	 * Constroi uma nova pesquisa de acordo com os parametros informados pelo
	 * usuario
	 * 
	 * @param descricao, a descricao da pesquisa
	 * @param campo,     o campo da pesquisa
	 * @param codigo,    o codigo unico da pesquisa e tambem seu identificador
	 */
	public Pesquisa(String descricao, String campo, String codigo) {
		Verificador.verificaEntrada(descricao, "Descricao nao pode ser nula ou vazia.");
		Verificador.verificaEntrada(campo, "Formato do campo de interesse invalido.");
		this.descricao = descricao;
		this.campo = campo;
		this.codigo = codigo;
		this.status = "Ativa";
		this.objetivos = new HashMap<>();
		this.atividadesAssociadas = new ArrayList<>();
	}

	/**
	 * Retorna a String que representa o status (ativada ou desativada) da Pesquisa
	 * 
	 * @return a String que representa o status (ativada ou desativada) da Pesquisa
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Modifica o status da Pesquisa para ativada ou desativada
	 * 
	 * @param a String que representa o novo status da Pesquisa
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Retorna a String que representa a descricao da Pesquisa
	 * 
	 * @return a String que representa a descricao da Pesquisa
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Modifica a descricao da Pesquisa.
	 * 
	 * @param descricao a String que representa a nova descricao da Pesquisa.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Retorna a String que representa o campo de atuacao da Pesquisa
	 * 
	 * @return a String que representa o campo de atuacao da Pesquisa
	 */
	public String getCampo() {
		return campo;
	}

	/**
	 * Retorna a String que representa o identificador unico da Pesquisa
	 * 
	 * @returna String que representa o identificador unico da Pesquisa
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Modifica o campo de atuacao da Pesquisa
	 * 
	 * @param campo o novo campo de atuacao da Pesquisa
	 */
	public void setCampo(String campo) {
		this.campo = campo;
	}

	/**
	 * Adiciona um novo ou modifica o Problema atual associado a Pesquisa
	 * 
	 * @param problema o Problema o qual se deseja associar a Pesquisa
	 */
	public void setProblema(Problema problema) {
		this.problema = problema;
	}

	/**
	 * Adiciona um Objetivo no mapa de objetivos
	 * 
	 * @param idObjetivo o ID unico do objetivo
	 * @param objetivo   um objeto do tipo Objetivo
	 */
	public void adicionaObjetivo(String idObjetivo, Objetivo objetivo) {
		this.objetivos.put(idObjetivo, objetivo);
	}

	/**
	 * Remove um Objetivo do mapa de objetivos
	 * 
	 * @param idObjetivo o ID unico do objetivo
	 */
	public void removeObjetivo(String idObjetivo) {
		this.objetivos.remove(idObjetivo);
	}

	/**
	 * Verifica se a pesquisa contem um Problema associado
	 * 
	 * @return retorna o boolean que representa a associacao ou nao do problema
	 */
	public boolean contemProblema() {

		if (this.problema == null) {
			return false;
		}

		return true;

	}

	/**
	 * Retorna "99" se a Pesquisa nao tem Problema associado ou o codigo do Problema
	 * se tiver. Serve para fins de ordenacao.
	 * 
	 * @return retorna "99" se a Pesquisa nao tem Problema associado ou o codigo do
	 *         Problema se tiver
	 */
	public String getStringProblema() {

		if (this.problema == null) {
			return "99";
		}

		return this.problema.getCodigo();

	}

	/**
	 * Verifica se existe tal Objetivo no mapa de objetivos
	 * 
	 * @param idObjetivo o ID unico do objetivo
	 * @return retorna o boolean que representa a associacao ou nao do objetivo
	 */
	public boolean contemObjetivo(String idObjetivo) {

		if (this.objetivos.containsKey(idObjetivo)) {
			return true;
		}

		else {
			return false;
		}

	}

	/**
	 * Verifica se a Pesquisa contem quaisquer objetivos associados
	 * 
	 * @return retorna o boolean que representa a associacao ou nao de algum
	 *         objetivo
	 */
	public boolean contemObjetivos() {
		if (this.objetivos.size() == 0) {
			return false;
		}

		return true;

	}

	/**
	 * Retorna o inteiro que representa a quantidade de objetivos associados.
	 * 
	 * @return retorna o inteiro que representa a quantidade de objetivos
	 *         associados.
	 */
	public int getQtdObjetivos() {

		return this.objetivos.size();

	}

	/**
	 * Retorna o objeto do tipo Problema que esta associado a Pesquisa
	 * 
	 * @return o objeto do tipo Problema que esta associado a Pesquisa
	 */
	public Problema getProblema() {
		return this.problema;
	}

	/**
	 * Verifica se a pesquisa esta ativada (true) ou desativada (false)
	 * 
	 * @return retorna o boolean que representa o estado de ativacao da pesquisa
	 */
	public boolean isAtivada() {
		if (this.status.equalsIgnoreCase("Ativa")) {
			return true;
		}

		else {
			return false;
		}

	}

	@Override
	/**
	 * Retorna uma representacao em string da pesquisa no formato CODIGO - DESCRICAO
	 * - CAMPO DA PESQUISA
	 * 
	 * @return a representacao em string da pesquisa.
	 */
	public String toString() {
		return codigo + " - " + descricao + " - " + campo;
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
		Pesquisa other = (Pesquisa) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	/**
	 * Armazena o codigo de uma atividade
	 * @param codigoAtividade codigo da atividade a ser armazenado
	 * @return false caso a associacao nao ocorra, true caso contrario
	 */
	public boolean associaAtividade(String codigoAtividade) {
		if (this.atividadesAssociadas.contains(codigoAtividade)) {
			return false;
		}
		this.atividadesAssociadas.add(codigoAtividade);
		return true;
	}

	public boolean desassociaAtividade(String codigoAtividade) {
		if (!this.atividadesAssociadas.contains(codigoAtividade)) {
			return false;
		}
		this.atividadesAssociadas.remove(codigoAtividade);
		return true;
	}

}