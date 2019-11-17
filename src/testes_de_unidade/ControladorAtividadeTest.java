package testes_de_unidade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import atividades.ControladorAtividade;
import pesquisa.Pesquisa;

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
	void testaContaItensRealizadosCodigoInvalido() {

		assertThrows(IllegalArgumentException.class, () -> controlAtividadeTest.contaItensRealizados("A1929192"));

	}

	@Test
	void testaCadastraResultado() {

		controlAtividadeTest.cadastraAtividade("Ajudar pessoas", "ALTO", "Falta de empatia humana");
		assertEquals(1, controlAtividadeTest.cadastraResultado("A1", "Deu certo"));

	}

	@Test
	void testaRemoveResultado() {

		controlAtividadeTest.cadastraAtividade("Ajudar pessoas", "ALTO", "Falta de empatia humana");
		controlAtividadeTest.cadastraResultado("A1", "Deu certo");

		assertEquals(true, controlAtividadeTest.removeResultado("A1", 1));

	}

	@Test
	void testaPegaResultado() {

		controlAtividadeTest.cadastraAtividade("Ajudar pessoas", "ALTO", "Falta de empatia humana");
		controlAtividadeTest.cadastraItem("A1", "Cafe");

		assertEquals(0, controlAtividadeTest.getDuracao("A1"));

	}

	@Test
	void testaAssociaPesquisaCodiPesquisaNulo() {

		controlAtividadeTest.cadastraAtividade("Ajudar pessoas", "ALTO", "Falta de empatia humana");

		assertThrows(NullPointerException.class, () -> controlAtividadeTest.associaPesquisa(null, "A1"));

	}

	@Test
	void testaAssociaPesquisaCodiAtividadeNulo() {

		controlAtividadeTest.cadastraAtividade("Ajudar pessoas", "ALTO", "Falta de empatia humana");

		assertThrows(NullPointerException.class, () -> controlAtividadeTest.associaPesquisa("Cafe", null));

	}

	

}
