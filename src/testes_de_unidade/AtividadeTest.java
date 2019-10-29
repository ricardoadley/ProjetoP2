package testes_de_unidade;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Period;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import atividades.Atividade;
import junit.framework.Assert;

class AtividadeTest {

	private Atividade atividade1;
	private Atividade atividade2;
	private Atividade atividade3;

	@BeforeEach 
	public void iniciaAtividades() {
		atividade1 = new Atividade("the flash", "BAIXO", "meio descrito", Period.ofDays(8), "A1");
		atividade2 = new Atividade("game of thrones", "MEDIO", "pouco descrito", Period.ofDays(9), "A1");
		atividade3 = new Atividade("supernatural", "ALTO", "MUITO DESCRITO!", Period.ofDays(10), "A3");
	}
	
	@Test
	void testToString() {
		atividade1.adicionaItem("Fazer cafe");
		
		assertEquals("the flash (BAIXO - meio descrito) | PENDENTE - Fazer cafe", atividade1.toString());
		
	}

	@Test
	void testHashCodeIgual() {
		
		assertTrue(atividade1.hashCode() == atividade2.hashCode());
		
	}
	
	@Test
	void testHashCodeDiferente() {
		
		assertTrue(atividade1.hashCode() != atividade3.hashCode());
		
	}
	
	@Test
	void testEqualsIgual() {
		
		assertTrue(atividade1.equals(atividade2));
		
	}
	
	@Test
	void testEqualsDiferente() {
		
		assertFalse(atividade1.equals(atividade3));
		
	}
	 
	@Test
	void testEqualsMesmoObjeto() {
		
		assertTrue(atividade1.equals(atividade1));

		
	}
	
	@Test
	void testEqualsObjetoNulo() {
		
		assertFalse(atividade1.equals(null));
		
	}

	@Test
	void testEqualsCodigoNull() {
		
		Atividade atividade4 = new Atividade("the flash", "BAIXO", "meio descrito", Period.ofDays(8), null);
		assertFalse(atividade1.equals(atividade4));
		
	}
	
	@Test
	void testEqualsCodigoNull2() {
		
		Atividade atividade4 = new Atividade("the flash", "BAIXO", "meio descrito", Period.ofDays(8), null);
		assertFalse(atividade4.equals(atividade1));
		
	}
	
	@Test
	void testEqualsCodigoNull3() {
		
		Atividade atividade5 = new Atividade("supergirl", "ALTO", "descritissimo", Period.ofDays(8), null);
		Atividade atividade4 = new Atividade("the flash", "BAIXO", "meio descrito", Period.ofDays(8), null);
		
		assertTrue(atividade4.equals(atividade5));
		
	}
	
	@Test
	void testContaItensPendentes() {
		
		atividade1.adicionaItem("Fazer cafe");
		atividade1.adicionaItem("Tarefa");
		atividade1.adicionaItem("Monitoramento");
		atividade1.adicionaItem("Análise de dados");
		atividade1.adicionaItem("Quebrar paredes");

		assertEquals(5, atividade1.contaItensPendentes());
		
	}
	
	@Test
	void testContaItensRealizados() {
		
		atividade1.adicionaItem("Fazer cafe");
		atividade1.adicionaItem("Tarefa");

		assertEquals(0, atividade1.contaItensRealizados());
		
	}
	
	
	
}
