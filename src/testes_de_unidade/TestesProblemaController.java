package testes_de_unidade;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import problema.ProblemaController;

class TestesProblemaController {

	private ProblemaController controladorproblemas;

	@BeforeEach
	void iniciar() {
		controladorproblemas = new ProblemaController();
	}

	@Test
	void testCadastraProblemaPerfeito() {
		assertEquals("P1", controladorproblemas.cadastraProblema("muito que bem descrito", "4"));
	}

	@Test
	void testCadastraProblemaDescricaoVazia() {
		assertThrows(IllegalArgumentException.class, () -> controladorproblemas.cadastraProblema("", "2"));
	}

	@Test
	void testCadastraProblemaDescricaoNull() {
		assertThrows(NullPointerException.class, () -> controladorproblemas.cadastraProblema(null, "2"));
	}

	@Test
	void testCadastraProblemasViabilidadeVazia() {
		assertThrows(IllegalArgumentException.class, () -> controladorproblemas.cadastraProblema("muito descrito", ""));
	}

	@Test
	void testCadastraProblemasViabilidadeNull() {
		assertThrows(NullPointerException.class, () -> controladorproblemas.cadastraProblema("descreveu todo", null));
	}

	@Test
	void testCadastraProblemaValorInvalidoViabilidade() {
		assertThrows(NumberFormatException.class,
				() -> controladorproblemas.cadastraProblema("descrito que so ele", "k"));
	}

	@Test
	void testCadastraProblemaViabilidadeValorMenorQueZero() {
		assertThrows(IllegalArgumentException.class,
				() -> controladorproblemas.cadastraProblema("descrito todo", "-1"));
	}

	@Test
	void testCadastraProblemaViabilidadeValorMaiorQueCinco() {
		assertThrows(IllegalArgumentException.class,
				() -> controladorproblemas.cadastraProblema("bora descrever", "6"));
	}

	@Test
	void testApagaProblemaCodigoVazio() {
		assertThrows(IllegalArgumentException.class, () -> controladorproblemas.apagarProblema(""));
	}

	@Test
	void testApagaProblemaCodigoNull() {
		assertThrows(NullPointerException.class, () -> controladorproblemas.apagarProblema(null));
	}

	@Test
	void testApagaProblemaProblemaNaoEncontrado() {
		assertThrows(IllegalArgumentException.class, () -> controladorproblemas.apagarProblema("P54"));
	}

	@Test
	void testExibeProblemaPerfeito() {
		controladorproblemas.cadastraProblema("probleminha amor", "4");
		assertEquals("P1 - probleminha amor - 4", controladorproblemas.exibeProblema("P1"));
	}

	@Test
	void testExibeProblemaCodigoVazio() {
		assertThrows(IllegalArgumentException.class, () -> controladorproblemas.exibeProblema(""));
	}

	@Test
	void testExibeProblemaCodigoNull() {
		assertThrows(NullPointerException.class, () -> controladorproblemas.exibeProblema(null));
	}

	@Test
	void testExibeProblemaNaoEncontrado() {
		assertThrows(IllegalArgumentException.class, () -> controladorproblemas.exibeProblema("P99"));
	}

	@Test
	void testProcuraPalavraTermoVazio() {
		assertThrows(IllegalArgumentException.class, () -> controladorproblemas.procuraPalavra(""));
	}

	@Test
	void testProcuraPalavraTermoNull() {
		assertThrows(NullPointerException.class, () -> controladorproblemas.procuraPalavra(null));
	}

	@Test
	void testProcuraPalavraPerfeito() {
		controladorproblemas.cadastraProblema("camisa do naruto eh muito cara", "4");
		controladorproblemas.cadastraProblema("a UFCG me deixou sem tempo de assistir naruto", "5");
		ArrayList<String> retorno = new ArrayList<String>();
		retorno.add("P2: a UFCG me deixou sem tempo de assistir naruto");
		retorno.add("P1: camisa do naruto eh muito cara");
		assertEquals(true, retorno.equals(controladorproblemas.procuraPalavra("naruto")));
	}
}
