package atividades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import sistema.SalvaSistema;
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
	 * Mapa de atividades do tipo <String, Atividade>
	 */
	private Map<String, Atividade> atividades;
	/**
	 * Codigo unico de cada atividade, o codigo ja eh iniciado com o valor 1
	 */
	private int codigo;
	// private List<String> resultados;
	LinkedList<Atividade> lista = new LinkedList<Atividade>();

	/**
	 * Construtor do objeto ControladorAtividade
	 */

	public ControladorAtividade() {
		this.atividades = new HashMap<>();
		this.codigo = 1;
	}

	/**
	 * Cadastra um objeto do tipo Atividade no mapa de atividades
	 * 
	 * @param descricao, a descricao da atividade que sera cadastrada
	 * @param nivelRisco, o nivel de risco da atividade que sera cadastrada
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
		Atividade atividade = new Atividade(descricao, nivelRisco, descricaoRisco, 0, "A" + codigo, null, null);
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
	 * @param item, o item que sera cadastrado na atividade
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
		return this.atividades.get(codigo).contaItensRealizados();
	}

	// todo o codigo abaixo se refere a acoes basicas no mapa de atividades
	public boolean existeAtividade(String codigo) {
		// retorna se ja existe ou nao a atividade no mapa
		return this.atividades.containsKey(codigo);
	}

	public Atividade capturaAtividadeNoMapa(String codigo) {
		return this.atividades.get(codigo);
	}

	/**
	 * Pesquisa,nos dados da entidade Atividade, por um termo informado pelo usuario
	 * 
	 * @param palavra, o termo informado pelo usuario
	 */
	public List<String> procuraPalavra(String palavra) {
		List<String> resultados = new ArrayList<String>();
		String fraseDescricao = "";
		String fraseDescricaoRisco = "";
		Verificador.verificaEntrada(palavra, "Campo termo nao pode ser nulo ou vazio.");
		List<Atividade> listaAtividades = new ArrayList<>(this.atividades.values());
		Collections.sort(listaAtividades, new ComparadorAtividade());
		for (Atividade atividade : listaAtividades) {
			fraseDescricao = atividade.getCodigo() + ": " + atividade.getDescricao();
			fraseDescricaoRisco = atividade.getCodigo() + ": " + atividade.getDescricaoRisco();
			if (fraseDescricao.toLowerCase().contains(palavra.toLowerCase())) {
				resultados.add(fraseDescricao);
			}
			if (fraseDescricaoRisco.toLowerCase().contains(palavra.toLowerCase())) {
				resultados.add(fraseDescricaoRisco);
			}
			resultados.addAll(atividade.pesquisaItem(palavra));
		}
		return resultados;
	}

	/**
	 * Executa um item de uma atividade
	 * 
	 * @param codigoAtividade codigo da atividade que possui o item
	 * @param item            item a ser realizado
	 * @param duracao         duracao a ser incrementada
	 */
	public void executaAtividade(String codigoAtividade, int item, int duracao) {
		Verificador.verificaEntrada(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		Verificador.verificaInteiroPositivo(duracao, "Duracao nao pode ser nula ou negativa.");
		Verificador.verificaInteiroPositivo(item, "Item nao pode ser nulo ou negativo.");
		this.capturaAtividadeNoMapa(codigoAtividade).executaAtividade(item, duracao);
	}

	/**
	 * Cadastra o resultado de uma atividade
	 * 
	 * @param codigoAtividade codigo da atividade a receber o resultado
	 * @param resultado       resultado a ser cadastrado
	 * @return o codigo do resultado que foi cadastrado
	 */
	public int cadastraResultado(String codigoAtividade, String resultado) {
		Verificador.verificaEntrada(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		Verificador.verificaEntrada(resultado, "Resultado nao pode ser nulo ou vazio.");
		Verificador.existeChave(atividades, codigoAtividade, "Atividade nao encontrada");
		return this.capturaAtividadeNoMapa(codigoAtividade).cadastraResultado(resultado);
	}

	/**
	 * Remove um resultado cadastrado em uma atividade
	 * 
	 * @param codigoAtividade codigo da atividade que possui o resultado
	 * @param numeroResultado codigo do resultado a ser removido
	 * @return true
	 */
	public boolean removeResultado(String codigoAtividade, int numeroResultado) {
		Verificador.verificaEntrada(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		Verificador.verificaInteiroPositivo(numeroResultado, "numeroResultado nao pode ser nulo ou negativo.");
		Verificador.existeChave(atividades, codigoAtividade, "Atividade nao encontrada");
		return this.capturaAtividadeNoMapa(codigoAtividade).removeResultado(numeroResultado);
	}

	/**
	 * Retorna a representacao em texto dos restulados cadastrados em uma atividade
	 * 
	 * @param codigoAtividade codigo da atividade que possui os resultados
	 * @return a representacao em texto dos restulados cadastrados da atividade
	 */
	public String listaResultados(String codigoAtividade) {
		Verificador.verificaEntrada(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		Verificador.existeChave(atividades, codigoAtividade, "Atividade nao encontrada");
		return this.capturaAtividadeNoMapa(codigoAtividade).listaResultados();
	}

	/**
	 * Retorna a duracao da atividade
	 * 
	 * @param codigoAtividade codigo da atividade que possui a duracao
	 * @return a duracao da atividade
	 */
	public int getDuracao(String codigoAtividade) {
		Verificador.verificaEntrada(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		Verificador.existeChave(atividades, codigoAtividade, "Atividade nao encontrada");
		return this.capturaAtividadeNoMapa(codigoAtividade).getduracao();
	}

	/**
	 * Armazena em uma pesquisa o codigo de uma atividade, representando uma
	 * associacao
	 * 
	 * @param codigoPesquisa  codigo da pesquisa a receber o codigo da atividade
	 * @param codigoAtividade codigo atividade a ser recebido pela pesquisa
	 */
	public void associaPesquisa(String codigoPesquisa, String codigoAtividade) {
		Verificador.verificaEntrada(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		Verificador.verificaEntrada(codigoPesquisa, "Campo codigoPesquisa nao pode ser nulo ou vazio.");
		Verificador.existeChave(atividades, codigoAtividade, "Atividade nao encontrada");
		this.capturaAtividadeNoMapa(codigoAtividade).associaPesquisa(codigoPesquisa);
	}

	/**
	 * Remove a associal de uma atividade com a pesquisa
	 * 
	 * @param codigoPesquisa  codigo da pesquisa que possui a atividade
	 * @param codigoAtividade codigo da atividade a ser removido
	 */
	public void desassociaPesquisa(String codigoPesquisa, String codigoAtividade) {
		Verificador.verificaEntrada(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		Verificador.verificaEntrada(codigoPesquisa, "Campo codigoPesquisa nao pode ser nulo ou vazio.");
		Verificador.existeChave(atividades, codigoAtividade, "Atividade nao encontrada");
		this.capturaAtividadeNoMapa(codigoAtividade).desassociaPesquisa(codigoPesquisa);
	}
	
	public void salvar() {
		SalvaSistema.gravarDados(this.atividades,"dadosAtividade.dat");
	}
	public void retorna() {
		this.atividades = SalvaSistema.retornaDadoAtividades();
	}
	
	/**
	 * Define uma atividade como proxima da outra.
	 * 
	 * @param idPrecedente id da atividade que ira ter proximo.
	 * @param idSubsquente id da atividade que sera colada como proximo.
	 */
	public void defineProximaAtividade(String idPrecedente, String idSubsquente) {
		Verificador.verificaEntrada(idPrecedente, "Atividade nao pode ser nulo ou vazio.");
		Verificador.verificaEntrada(idSubsquente, "Atividade nao pode ser nulo ou vazio.");
		Verificador.existeChave(atividades, idPrecedente, "Atividade nao encontrada.");
		Verificador.existeChave(atividades, idSubsquente, "Atividade nao encontrada.");
		atividades.get(idPrecedente).defineProximaAtividade(atividades.get(idSubsquente));
		}
	
	/**
	 * Retira a proxima de uma determinada atividade.
	 * 
	 * @param idPrecedente id da atividade a qual, sera retirado o proximo.
	 */
	public void tiraProximaAtividade(String idPrecedente) {
		Verificador.verificaEntrada(idPrecedente, "Atividade nao pode ser nulo ou vazio.");
		Verificador.existeChave(atividades, idPrecedente, "Atividade nao encontrada.");
		atividades.get(idPrecedente).tiraProximaAtividade();
	}
	
	/**
	 * Conta as proximas atividades a partir de uma atividade.
	 * 
	 * @param idPrecedente id da atividade que servira como ponto de partida para contagem dos proximos.
	 * 
	 * @return retorna o numero de proximas atividades.
	 */
	public int contaProximos(String idPrecedente) {
		Verificador.verificaEntrada(idPrecedente, "Atividade nao pode ser nulo ou vazio.");
		Verificador.existeChave(atividades, idPrecedente, "Atividade nao encontrada.");
		return atividades.get(idPrecedente).contaProximo();
	}
	
	/**
	 * Pega as proximas n atividades a partir de uma dada atividade.
	 * 
	 * @param idAtividade id da atividade qeu servira como ponto de partida.
	 * @param enesimaAtividade numero n de casas de distancia desejada entre as atividades.
	 * 
	 * @return retorna o codigo da atividade encontrada ou erro caso nao exista atividades na posicao final.
	 */
	public String pegaProximo(String idAtividade, int enesimaAtividade) {
		Verificador.verificaEntrada(idAtividade, "Atividade nao pode ser nulo ou vazio.");
		Verificador.existeChave(atividades, idAtividade, "Atividade nao encontrada.");
		Verificador.verificaInteiroPositivo(enesimaAtividade, "EnesimaAtividade nao pode ser negativa ou zero.");
		return atividades.get(idAtividade).pegaProximo(enesimaAtividade);
	}
	
	/**
	 * Pega a atividade com maior risco em uma sequencia a partir de uma atividade x.
	 * 
	 * @param idAtividade id da atividade que servira como ponto de partida.
	 * 
	 * @return retorna o codigo da atividade com maior risco.
	 */
	public String pegaMaiorRiscoAtividades(String idAtividade) {
		Verificador.verificaEntrada(idAtividade, "Atividade nao pode ser nulo ou vazio.");
		Verificador.existeChave(atividades, idAtividade, "Atividade nao encontrada.");
		return atividades.get(idAtividade).pegaMaiorRiscoAtividades();
	}
}
