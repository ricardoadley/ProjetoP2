package testes_de_unidade;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pesquisador.Pesquisador;

class TestesPesquisador {

	private Pesquisador p1;
	private Pesquisador p2;
	private Pesquisador p3;

	@BeforeEach
	void inicia() {
		p1 = new Pesquisador("ricardo", "pesquisar", "a ta bom", "ricardo@pesquisador.com", "https://fotinhaminha.jpg",
				1);
		p2 = new Pesquisador("ricardo", "pesquisar", "a ta bom", "ricardo@pesquisador.com", "https://fotinhaminha.jpg",
				2);
		p3 = new Pesquisador("nathan", "tomar cafe", "ele eh jovem ele", "nathan@tomacafe.com",
				"https://fotinhadele.jpg", 3);
	}

	@Test
	void testToString() {
		assertEquals("ricardo (pesquisar) - a ta bom - ricardo@pesquisador.com - https://fotinhaminha.jpg",
				p1.toString());
	}

	@Test
	void testConstroiPesquisadorNomeVazio() {
		assertThrows(IllegalArgumentException.class, () -> new Pesquisador("", "pesquisar", "a ta bom",
				"ricardo@pesquisador.com", "https://fotinhaminha.jpg", 1));
	}

	@Test
	void testConstroiPesquisadorNomeNull() {
		assertThrows(NullPointerException.class, () -> new Pesquisador(null, "pesquisar", "a ta bom",
				"ricardo@pesquisador.com", "https://fotinhaminha.jpg", 1));
	}

	@Test
	void testConstroiPesquisadorFuncaoVazio() {
		assertThrows(IllegalArgumentException.class, () -> new Pesquisador("ricardo", "", "a ta bom",
				"ricardo@pesquisador.com", "https://fotinhaminha.jpg", 1));
	}

	@Test
	void testConstroiPesquisadorFuncaoNull() {
		assertThrows(NullPointerException.class, () -> new Pesquisador(null, "pesquisar", "a ta bom",
				"ricardo@pesquisador.com", "https://fotinhaminha.jpg", 1));
	}

	@Test
	void testConstroiPesquisadorBiografiaVazio() {
		assertThrows(IllegalArgumentException.class, () -> new Pesquisador("ricardo", "pesquisar", "",
				"ricardo@pesquisador.com", "https://fotinhaminha.jpg", 1));
	}

	@Test
	void testConstroiPesquisadorBiografiaNull() {
		assertThrows(NullPointerException.class, () -> new Pesquisador("ricardo", "pesquisar", null,
				"ricardo@pesquisador.com", "https://fotinhaminha.jpg", 1));
	}

	@Test
	void testConstroiPesquisadorEmailVazio() {
		assertThrows(IllegalArgumentException.class,
				() -> new Pesquisador("ricardo", "pesquisar", "a ta bom", "", "https://fotinhaminha.jpg", 2));
	}

	@Test
	void testConstroiPesquisadorEmailNull() {
		assertThrows(NullPointerException.class,
				() -> new Pesquisador("ricardo", "pesquisar", "a ta bom", null, "https://fotinhaminha.jpg", 2));
	}

	@Test
	void testConstroiPesquisadorURLfotoVazio() {
		assertThrows(IllegalArgumentException.class,
				() -> new Pesquisador("ricardo", "pesquisar", "a ta bom", "ricardo@pesquisador.com", "", 2));
	}

	@Test
	void testConstroiPesquisadorURLfotoNull() {
		assertThrows(NullPointerException.class,
				() -> new Pesquisador("ricardo", "pesquisar", "a ta bom", "ricardo@pesquisador.com", null, 3));
	}

	@Test
	void testEqualsObjetosIguais() {
		assertEquals(true, p1.equals(p2));
	}

	@Test
	void testEqualsMesmoObjeto() {
		assertEquals(true, p1.equals(p1));
	}

	@Test
	void testEqualsObjetoNull() {
		assertEquals(false, p1.equals(null));
	}

	@Test
	void testHashCodeIguais() {
		assertEquals(true, p1.hashCode() == p2.hashCode());
	}

	@Test
	void testHashCodeDiferentes() {
		assertEquals(false, p1.hashCode() == p3.hashCode());
	}
}
