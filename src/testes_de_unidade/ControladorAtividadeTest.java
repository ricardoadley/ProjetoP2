package testes_de_unidade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import atividades.ControladorAtividade;

class ControladorAtividadeTest {

	ControladorAtividade controlAtividadeTest;

	@BeforeEach
	void inicia() {

		controlAtividadeTest = new ControladorAtividade();

	}

	@Test
	void testaCadastroAtividade() {

		assertEquals("A1", controlAtividadeTest.cadastraAtividade("Ajudar pessoas com deficiencia", "BAIXO",
				"Risco diferenciado"));
		assertEquals("A2", controlAtividadeTest.cadastraAtividade("Fiscalizar crimes", "ALTO", "Risco alto"));
		assertEquals("A3",
				controlAtividadeTest.cadastraAtividade("Realizar doacoes", "MEDIO", "Risco bastante critico"));
	}

	@Test
	void testaCadastroAtividadeRiscoInvalido() {

		assertThrows(IllegalArgumentException.class, () -> controlAtividadeTest
				.cadastraAtividade("Ajudar pessoas com deficiencia", "MUITO BAIXO", "Risco diferenciado"));
		assertThrows(IllegalArgumentException.class, () -> controlAtividadeTest
				.cadastraAtividade("Ajudar pessoas com deficiencia", "POUCO ALTO", "Risco diferenciado"));
		assertThrows(IllegalArgumentException.class, () -> controlAtividadeTest
				.cadastraAtividade("Ajudar pessoas com deficiencia", "ALTISSIMO", "Risco diferenciado"));

	}

	@Test
	void testaApagaAtividade() {

		controlAtividadeTest.cadastraAtividade("Ajudar pessoas", "ALTO", "Falta de empatia humana");

		controlAtividadeTest.apagaAtividade("A1");

	}

	@Test
	void testaApagaAtividadeCodigoInvalido() {

		assertThrows(IllegalArgumentException.class, () -> controlAtividadeTest.apagaAtividade("A2"));

	}

	@Test
	void testaCadastraItem() {

		controlAtividadeTest.cadastraAtividade("Ajudar pessoas", "ALTO", "Falta de empatia humana");

		controlAtividadeTest.cadastraItem("A1", "Fazer cafe");

	}

	@Test
	void testaCadastraItemCodigoInvalido() {

		assertThrows(IllegalArgumentException.class, () -> controlAtividadeTest.cadastraItem("A1", "Cameras"));

	}

	@Test
	void testaContaItensPendentes() {

		controlAtividadeTest.cadastraAtividade("Ajudar pessoas", "ALTO", "Falta de empatia humana");
		controlAtividadeTest.cadastraItem("A1", "Fazer cafe");
		controlAtividadeTest.cadastraItem("A1", "Cameras");

		assertEquals(2, controlAtividadeTest.contaItensPendentes("A1"));

	}

	@Test
	void testaContaItensPendentesCodigoInvalido() {

		assertThrows(IllegalArgumentException.class, () -> controlAtividadeTest.contaItensPendentes("A67"));

	}

	@Test
	void testaExibeAtividade() {

		controlAtividadeTest.cadastraAtividade("Ajudar pessoas", "ALTO", "Falta de empatia humana");
		controlAtividadeTest.cadastraItem("A1", "Fazer cafe");
		controlAtividadeTest.cadastraItem("A1", "Cameras");

		assertEquals(controlAtividadeTest.exibeAtividade("A1"),
				"Ajudar pessoas (ALTO - Falta de empatia humana)" + " | PENDENTE - Fazer cafe | PENDENTE - Cameras");

	}

	@Test
	void testaExibeAtividadeCodigoInvalido() {

		controlAtividadeTest.cadastraAtividade("Ajudar pessoas", "ALTO", "Falta de empatia humana");

		assertThrows(IllegalArgumentException.class, () -> controlAtividadeTest.exibeAtividade("A839393"));

	}

	@Test
	void testaContaItensRealizados() {

		controlAtividadeTest.cadastraAtividade("Ajudar pessoas", "ALTO", "Falta de empatia humana");

		assertEquals(0, controlAtividadeTest.contaItensRealizados("A1"));
	}

	@Test
	void testExecutaAtividadeComSucesso() {
		controlAtividadeTest.cadastraAtividade("ativiade de teste", "MEDIO", "descrito");
		controlAtividadeTest.cadastraItem("A1", "item de teste");
		controlAtividadeTest.associaPesquisa("PES1", "A1");
		controlAtividadeTest.executaAtividade("A1", 1, 120);
	}

	@Test
	void testExecutaAtividadeCodigoAtividadeVazio() {
		assertThrows(IllegalArgumentException.class, () -> controlAtividadeTest.executaAtividade("", 1, 120));
	}

	@Test
	void testExecutaAtividadeCodigoAtividadeNull() {
		assertThrows(NullPointerException.class, () -> controlAtividadeTest.executaAtividade(null, 1, 120));
	}

	@Test
	void testExecutaAtividadeCodigoItemNegativo() {
		assertThrows(IllegalArgumentException.class, () -> controlAtividadeTest.executaAtividade("A1", -3, 120));
	}

	@Test
	void testExecutaAtividadeDuracaoNegativa() {
		assertThrows(IllegalArgumentException.class, () -> controlAtividadeTest.executaAtividade("A1", 1, -20));
	}

	@Test
	void testCadastraResultadoAtividadeComSucesso() {
		controlAtividadeTest.cadastraAtividade("Atividade de teste", "MEDIO", "aqui descreve");
		assertEquals(1, controlAtividadeTest.cadastraResultado("A1", "deu certo"));
	}

	@Test
	void testCadastraResultadoAtividadeCodigoAtividadeVazio() {
		assertThrows(IllegalArgumentException.class, () -> controlAtividadeTest.cadastraResultado("", "resolveu todo"));
	}

	@Test
	void testCadastraResultadoAtividadeCodigoAtividadeNull() {
		assertThrows(NullPointerException.class, () -> controlAtividadeTest.cadastraResultado(null, "resolveu todo"));
	}

	@Test
	void testCadastraResultadoAtividadeResultadoVazio() {
		controlAtividadeTest.cadastraAtividade("Atividade de teste", "MEDIO", "aqui descreve");
		assertThrows(IllegalArgumentException.class, () -> controlAtividadeTest.cadastraResultado("A1", ""));
	}

	@Test
	void testCadastraResultadoAtividadeResultadoNull() {
		controlAtividadeTest.cadastraAtividade("Atividade de teste", "MEDIO", "aqui descreve");
		assertThrows(NullPointerException.class, () -> controlAtividadeTest.cadastraResultado("A1", null));
	}

	@Test
	void testCadastraResultadoAtividadeNaoEncontrada() {
		controlAtividadeTest.cadastraAtividade("Atividade de teste", "MEDIO", "aqui descreve");
		assertThrows(IllegalArgumentException.class,
				() -> controlAtividadeTest.cadastraResultado("A1456789", "voce que lute"));
	}

	@Test
	void testRemoveResultadoAtividadeComSucesso() {
		controlAtividadeTest.cadastraAtividade("Atividade de teste", "MEDIO", "aqui descreve");
		controlAtividadeTest.cadastraResultado("A1", "esse aqui resolveu");
		assertEquals(true, controlAtividadeTest.removeResultado("A1", 1));
	}

	@Test
	void testRemoveResultadoAtividadeCodigoAtividadeVazio() {
		assertThrows(IllegalArgumentException.class, () -> controlAtividadeTest.removeResultado("", 1));
	}

	@Test
	void testRemoveResultadoAtividadeCodigoAtividadeNull() {
		assertThrows(NullPointerException.class, () -> controlAtividadeTest.removeResultado(null, 1));
	}

	@Test
	void testRemoveResultadoAtividadeNumeroDoResultadoNegativo() {
		assertThrows(IllegalArgumentException.class, () -> controlAtividadeTest.removeResultado("A1", -1));
	}

	@Test
	void testRemoveResultadoAtividadeNaoEncontrada() {
		assertThrows(IllegalArgumentException.class, () -> controlAtividadeTest.removeResultado("A23456789", 3));
	}

	@Test
	void testListaResultadosComSucesso() {
		controlAtividadeTest.cadastraAtividade("atividade de teste", "MEDIO", "ta descrito");
		controlAtividadeTest.cadastraResultado("A1", "pode ser um resultado duvidoso");
		controlAtividadeTest.cadastraResultado("A1", "aqui um segundo resultado");
		assertEquals("pode ser um resultado duvidoso | aqui um segundo resultado",
				controlAtividadeTest.listaResultados("A1"));
	}

	@Test
	void testListaResultadosCodigoAtividadeVazio() {
		assertThrows(IllegalArgumentException.class, () -> controlAtividadeTest.listaResultados(""));
	}

	@Test
	void testListaResultadosCodigoAtividadeNull() {
		assertThrows(NullPointerException.class, () -> controlAtividadeTest.listaResultados(null));
	}

	@Test
	void testListaResultadosAtividadeNaoExiste() {
		assertThrows(IllegalArgumentException.class, () -> controlAtividadeTest.listaResultados("A2345678"));
	}

	@Test
	void testGetDuracaoAtividadeComSucesso() {
		controlAtividadeTest.cadastraAtividade("atividade de teste", "MEDIO", "ta descrito");
		assertEquals(0, controlAtividadeTest.getDuracao("A1"));
	}

	@Test
	void testGetDuracaoAtividadeVazio() {
		assertThrows(IllegalArgumentException.class, () -> controlAtividadeTest.getDuracao(""));
	}

	@Test
	void testGetDuracaoAtividadeNull() {
		assertThrows(NullPointerException.class, () -> controlAtividadeTest.getDuracao(null));
	}

	@Test
	void testGetDuracaoAtividadeNaoExiste() {
		assertThrows(IllegalArgumentException.class, () -> controlAtividadeTest.getDuracao("A1234567"));
	}

	@Test
	void testaContaItensRealizadosCodigoInvalido() {

		assertThrows(IllegalArgumentException.class, () -> controlAtividadeTest.contaItensRealizados("A1929192"));

	}

	@Test
	void testAssociaPesquisaCodigoAtividadeVazio() {
		assertThrows(IllegalArgumentException.class, () -> controlAtividadeTest.associaPesquisa("PES1", ""));
	}

	@Test
	void testAssociaPesquisaCodigoAtividadeNull() {
		assertThrows(NullPointerException.class, () -> controlAtividadeTest.associaPesquisa("PES1", null));
	}

	@Test
	void testAssociaPesquisaCodigoPesquisaVazio() {
		assertThrows(IllegalArgumentException.class, () -> controlAtividadeTest.associaPesquisa("", "A1"));
	}

	@Test
	void testAssociaPesquisaCodigoPesquisaNull() {
		assertThrows(NullPointerException.class, () -> controlAtividadeTest.associaPesquisa(null, "A1"));
	}

	@Test
	void testAssociaPesquisaAtividadeNaoExiste() {
		assertThrows(IllegalArgumentException.class, () -> controlAtividadeTest.associaPesquisa("PES1", "A2345"));
	}

	@Test
	void testDesassociaAtividadeCodigoAtividadeVazio() {
		assertThrows(IllegalArgumentException.class, () -> controlAtividadeTest.desassociaPesquisa("PES1", ""));
	}

	@Test
	void testDesassociaAtividadeCodigoAtividadeNull() {
		assertThrows(NullPointerException.class, () -> controlAtividadeTest.desassociaPesquisa("PES1", null));
	}

	@Test
	void testDesassociaAtividadeCodigoPesquisaVazio() {
		assertThrows(IllegalArgumentException.class, () -> controlAtividadeTest.desassociaPesquisa("", "A1"));
	}

	@Test
	void testDesassociaAtividadeCodigoPesquisaNull() {
		assertThrows(NullPointerException.class, () -> controlAtividadeTest.desassociaPesquisa(null, "A1"));
	}

	@Test
	void testDesassociaAtividadeNaoExiste() {
		assertThrows(IllegalArgumentException.class, () -> controlAtividadeTest.desassociaPesquisa("PES1", "A23455"));
	}

	@Test
	void testProcuraPalavraTermoVazio() {
		assertThrows(IllegalArgumentException.class, () -> controlAtividadeTest.procuraPalavra(""));
	}

	@Test
	void testProcuraPalavraTermoNull() {
		assertThrows(NullPointerException.class, () -> controlAtividadeTest.procuraPalavra(null));
	}

	@Test
	void testProcuraPalavraPerfeito() {
		controlAtividadeTest.cadastraAtividade("aqui tem boruto", "MEDIO", "essa semana nao tem ep novo :(");
		controlAtividadeTest.cadastraAtividade("eh importante", "ALTO", "descricao do item borutozinho");
		ArrayList<String> retorno = new ArrayList<String>();
		retorno.add("A2: descricao do item borutozinho");
		retorno.add("A1: aqui tem boruto");
		assertEquals(true, retorno.equals(controlAtividadeTest.procuraPalavra("boruto")));
	}
}
