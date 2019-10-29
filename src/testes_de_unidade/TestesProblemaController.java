package testes_de_unidade;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import problema.ProblemaController;

class TestesProblemaController {

	private ProblemaController controladorproblemas;
	
	@BeforeEach
	void iniciar() {
		controladorproblemas = new ProblemaController();
	}
	// assertThrows(NullPointerException.class, () -> new Fornecedor(null, "ricardo@email.com", "80808080"));
	@Test
	void testCadastraProblemaPerfeito() {
		assertEquals("P1",controladorproblemas.cadastraProblema("muito que bem descrito", "4"));
	} 
	@Test
	void testCadastraProblemaDescricaoVazia() {
		assertThrows(IllegalArgumentException.class, () -> controladorproblemas.cadastraProblema("", "2"));
	}
	@Test
	void testCadastraProblemaDescricaoNull() {
		assertThrows(NullPointerException.class, () -> controladorproblemas.cadastraProblema(null,"2"));
	}
	@Test
	void testCadastraProblemasViabilidadeVazia() {
		assertThrows(IllegalArgumentException.class, () -> controladorproblemas.cadastraProblema("muito descrito",""));
	}
	@Test
	void testCadastraProblemasViabilidadeNull() {
		assertThrows(NullPointerException.class, () -> controladorproblemas.cadastraProblema("descreveu todo",null));
	}
	@Test
	void testCadastraProblemaValorInvalidoViabilidade() {
		assertThrows(NumberFormatException.class, () -> controladorproblemas.cadastraProblema("descrito que so ele","k"));
	}
	@Test
	void testCadastraProblemaViabilidadeValorMenorQueZero() {
		assertThrows(IllegalArgumentException.class, () -> controladorproblemas.cadastraProblema("descrito todo","-1"));
	}
	@Test
	void testCadastraProblemaViabilidadeValorMaiorQueCinco() {
		assertThrows(IllegalArgumentException.class, () -> controladorproblemas.cadastraProblema("bora descrever", "6"));
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
		controladorproblemas.cadastraProblema("probleminha amor","4");
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
	
}
