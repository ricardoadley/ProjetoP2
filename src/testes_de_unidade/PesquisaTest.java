package testes_de_unidade;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pesquisa.Pesquisa;

class PesquisaTest {

	Pesquisa pesquisa1;
	Pesquisa pesquisa2;
	Pesquisa pesquisa3;
	Pesquisa pesquisa4;

	@BeforeEach
	public void iniciaPesquisas() {
		this.pesquisa1 = new Pesquisa("Alo", "meu, deus, do, ceu", "MEU1");
		this.pesquisa2 = new Pesquisa("Alo", "meu, deus, do, ceu", "MEU2");
		this.pesquisa3 = new Pesquisa("joao", "meu, deus, do, ceu", "MEU3");
		this.pesquisa4 = new Pesquisa("Alo", "meu, deus, do, ceu", "MEU1");
	}

	@Test
	void testConstroiPesquisaDescricaoVazio() {
		assertThrows(IllegalArgumentException.class, () -> new Pesquisa("", "naruto,pokemon,java,c++", "MEU1"));
	}

	@Test
	void testConstroiPesquisaDescricaoNull() {
		assertThrows(NullPointerException.class, () -> new Pesquisa(null, "naruto,pokemon,java,c++", "MEU1"));
	}

	@Test
	void testConstroiPesquisaCampoVazio() {
		assertThrows(IllegalArgumentException.class, () -> new Pesquisa("assistir serie", "", "MEU1"));
	}

	@Test
	void testConstroiPesquisaCampoNull() {
		assertThrows(NullPointerException.class, () -> new Pesquisa("assistir borutinho", null, "MEU1"));
	}

	@Test
	void testToString() {
		// testando
		assertEquals("MEU1 - Alo - meu, deus, do, ceu", pesquisa1.toString());
	}

	@Test
	void testEqualsMesmaPesquisa() {
		assertTrue(pesquisa1.equals(pesquisa1));
	}

	@Test
	void testEqualsPesquisasIguais() {
		assertTrue(pesquisa1.equals(pesquisa4));
	}

	@Test
	void testEqualsPesquisasDiferentes() {
		assertFalse(pesquisa1.equals(pesquisa2));
	}

	@Test
	void testEqualsObjetoNull() {
		assertFalse(pesquisa1.equals(null));
	}

	@Test
	void testEqualsObjetosTiposDiferentes() {
		assertFalse(pesquisa1.equals(new Object()));
	}

	@Test
	void testEqualsPesquisaCodigoNull() {
		assertFalse(new Pesquisa("Alo", "meu", null).equals(new Pesquisa("Alo", "meu", "MEU1")));
	}

	@Test
	void testHashCodePesquisasIguais() {
		assertTrue(pesquisa1.hashCode() == pesquisa4.hashCode());
	}

	@Test
	void testHashCodePesquisasDiferentes() {
		assertFalse(pesquisa1.hashCode() == pesquisa2.hashCode());
	}
}
