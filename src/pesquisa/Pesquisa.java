package pesquisa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import atividades.Atividade;
import objetivo.Objetivo;
import pesquisador.Pesquisador;
import problema.Problema;
import sistema.Verificador;

/**
 * Classe que representa uma Pesquisa. Toda Pesquisa possui uma descricao, um
 * campo, um status (ativada ou desativada) e um codigo pelo qual e identificada
 * unicamente. Alem disso, tambem pode estar ou nao associada a um Problema ou
 * Objetivos.
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
	 * Mapa de Atividades associadas a Pesquisa.
	 */
	private Map<String, Atividade> atividadesAssociadas;

	/**
	 * Problema ao qual a pesquisa esta associada.
	 */
	private Problema problemaAssociado;
	/**
	 * Mapa de Objetivos associados a Pesquisa.
	 */
	private Map<String, Objetivo> objetivosAssociados;

	/**
	 * Mapa de Pesquisadores associados a Pesquisa.
	 */
	private Map<String, Pesquisador> pesquisadoresAssociados;

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
		this.objetivosAssociados = new HashMap<>();
		this.atividadesAssociadas = new HashMap<>();
		this.pesquisadoresAssociados = new HashMap<>();
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
		this.problemaAssociado = problema;
	}

	/**
	 * Adiciona um Objetivo no mapa de objetivos
	 * 
	 * @param idObjetivo o ID unico do objetivo
	 * @param objetivo   um objeto do tipo Objetivo
	 */
	public void adicionaObjetivo(String idObjetivo, Objetivo objetivo) {
		this.objetivosAssociados.put(idObjetivo, objetivo);
	}

	/**
	 * Remove um Objetivo do mapa de objetivos
	 * 
	 * @param idObjetivo o ID unico do objetivo
	 */
	public void removeObjetivo(String idObjetivo) {
		this.objetivosAssociados.remove(idObjetivo);
	}

	/**
	 * Verifica se a pesquisa contem um Problema associado
	 * 
	 * @return retorna o boolean que representa a associacao ou nao do problema
	 */
	public boolean contemProblema() {

		if (this.problemaAssociado == null) {
			return false;
		}

		return true;

	}

	/**
	 * Verifica se existe tal Objetivo no mapa de objetivos
	 * 
	 * @param idObjetivo o ID unico do objetivo
	 * @return retorna o boolean que representa a associacao ou nao do objetivo
	 */
	public boolean contemObjetivo(String idObjetivo) {

		if (this.objetivosAssociados.containsKey(idObjetivo)) {
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
		if (this.objetivosAssociados.size() == 0) {
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

		return this.objetivosAssociados.size();

	}

	/**
	 * Retorna o objeto do tipo Problema que esta associado a Pesquisa
	 * 
	 * @return o objeto do tipo Problema que esta associado a Pesquisa
	 */
	public Problema getProblema() {
		return this.problemaAssociado;
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

	/**
	 * Armazena o codigo de uma atividade
	 * 
	 * @param atividade codigo da atividade a ser armazenado
	 * @return false caso a associacao nao ocorra, true caso contrario
	 */
	public boolean associaAtividade(String codigoAtividade, Atividade atividade) {
		if (this.atividadesAssociadas.containsKey(codigoAtividade)) {
			return false;
		}

		this.atividadesAssociadas.put(codigoAtividade, atividade);
		return true;
	}

	/**
	 * Remove o codigo de uma atividade associada da lista de atividades
	 * 
	 * @param codigoAtividade codigo da atividade a ser removido
	 * @return false caso a desassociacao nao ocorra, true caso contrario
	 */
	public boolean desassociaAtividade(String codigoAtividade) {
		if (!this.atividadesAssociadas.containsKey(codigoAtividade)) {
			return false;
		}

		this.atividadesAssociadas.remove(codigoAtividade);
		return true;
	}

	/**
	 * Associa um Pesquisador a Pesquisa
	 * 
	 * @param emailPesquisador o identificador unico do Pesquisador
	 * @param pesquisador      o objeto do tipo Pesquisador
	 * @return false se o Pesquisador ja esta associado, true se nao
	 */
	public boolean associaPesquisador(String emailPesquisador, Pesquisador pesquisador) {

		if (this.pesquisadoresAssociados.containsKey(emailPesquisador)) {
			return false;
		}

		this.pesquisadoresAssociados.put(emailPesquisador, pesquisador);
		return true;
	}

	/**
	 * Desassocia um Pesquisador da Pesquisa
	 * 
	 * @param emailPesquisador o identificador unico do Pesquisador
	 * @return false se o Pesquisador nao estiver associado, true se estiver e o
	 *         remove
	 */
	public boolean desassociaPesquisador(String emailPesquisador) {

		if (!this.pesquisadoresAssociados.containsKey(emailPesquisador)) {
			return false;
		}

		this.pesquisadoresAssociados.remove(emailPesquisador);
		return true;

	}

	/**
	 * Gera um resumo da Pesquisa. Retorna os detalhes de tudo o que esta associado
	 * a Pesquisa.
	 * 
	 * @return os detalhes de tudo o que esta associado a Pesquisa.
	 */
	public String getResumo() {

		String resumoGeral = "- Pesquisa: " + this.toString() + System.lineSeparator();

		if (this.pesquisadoresAssociados.size() > 0) {

			resumoGeral += "    - Pesquisadores:" + System.lineSeparator();

			ArrayList<Pesquisador> pesquisadoresOrdenados = new ArrayList<Pesquisador>(
					this.pesquisadoresAssociados.values());
			Collections.sort(pesquisadoresOrdenados);

			for (int i = 0; i < pesquisadoresOrdenados.size(); i++) {
				resumoGeral += "        - " + pesquisadoresOrdenados.get(i).toString() + System.lineSeparator();
			}
		}

		if (this.problemaAssociado != null) {

			resumoGeral += "    - Problema:" + System.lineSeparator() + "        - " + this.problemaAssociado.toString()
					+ System.lineSeparator();

		}

		if (this.objetivosAssociados.size() > 0) {

			resumoGeral += "    - Objetivo:" + System.lineSeparator();

			ArrayList<Objetivo> objetivosOrdenados = new ArrayList<Objetivo>(this.objetivosAssociados.values());
			Collections.sort(objetivosOrdenados);

			for (int i = 0; i < objetivosOrdenados.size(); i++) {
				resumoGeral += "        - " + objetivosOrdenados.get(i).toString() + System.lineSeparator();
			}

		}

		if (this.atividadesAssociadas.size() > 0) {

			resumoGeral += "    - Atividades:" + System.lineSeparator();

			ArrayList<Atividade> atividadesOrdenadas = new ArrayList<Atividade>(this.atividadesAssociadas.values());
			Collections.sort(atividadesOrdenadas);

			for (int i = 0; i < atividadesOrdenadas.size(); i++) {
				resumoGeral += "        - " + atividadesOrdenadas.get(i).geraResumo();
			}

		}

		return resumoGeral;

	}

	public String getResultados() {

		String resultados = "- Pesquisa: " + this.toString() + System.lineSeparator();

		if (this.atividadesAssociadas.size() > 0) {

			resultados += "    - Resultados:" + System.lineSeparator();

			ArrayList<Atividade> atividadesOrdenadas = new ArrayList<Atividade>(this.atividadesAssociadas.values());
			Collections.sort(atividadesOrdenadas);

			for (int i = 0; i < atividadesOrdenadas.size(); i++) {
				resultados += atividadesOrdenadas.get(i).geraResultados();
			}

		}

		return resultados;

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

}