package testes_de_unidade;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import problema.Problema;

class TestesProblema { 
	private Problema prob1;
	private Problema prob2;
	private Problema prob3;
	
	@BeforeEach
	void InstanciarObjeto() {
		prob1 = new Problema("problema teste 1",5,"P1");
		prob2 = new Problema("problema teste 2",3,"P2");
		prob3 = new Problema("problema teste 1",5,"P1");
	} 
	@Test
	void testConstroiProblemaDescricaoVazio() {
		assertThrows(IllegalArgumentException.class, () -> new Problema("",5,"P1"));
	}
	@Test
	void testConstroiProblemaDescricaoNull() {
		assertThrows(NullPointerException.class, () -> new Problema(null,5,"P1"));
	}
	@Test
	void testConstroiProbelmaCodigoVazio() {
		assertThrows(IllegalArgumentException.class, () -> new Problema("muito sono",4,""));
	}
	@Test
	void testConstroiProblemaCodigoNull() {
		assertThrows(NullPointerException.class, () -> new Problema("dor nas costas",3,null));
	}
	@Test
	void testToString() {
		assertEquals("P1 - problema teste 1 - 5",prob1.toString());
	}
	@Test  
	void testEqualsObjetosIguais() {
		assertEquals(true,prob1.equals(prob3) && prob3.equals(prob1));
	} 
	@Test
	void testEqualsMesmoObjeto() {
		assertEquals(true,prob1.equals(prob1));
	}
	@Test
	void testEqualsObjetoNull() {
		assertEquals(false,prob1.equals(null)); 
	}
	
	@Test
	void testHashCodeTrue() {
		assertEquals(true,prob1.hashCode() == prob3.hashCode());
	}  
	@Test
	void testHashCodeFalse() {
		assertEquals(false,prob1.hashCode() == prob2.hashCode());
	}
}
