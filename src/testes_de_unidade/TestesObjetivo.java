package testes_de_unidade;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import objetivo.Objetivo;

class TestesObjetivo {

	private Objetivo obj1;
	private Objetivo obj2;
	private Objetivo obj3;
	
	@BeforeEach 
	void inicia() {
		obj1 = new Objetivo("especifico","descrito para descrever", 2,3,"O1");
		obj2 = new Objetivo("geral","descrevi todo", 4,5,"O2");
		obj3 = new Objetivo("especifico","descrito para descrever", 2,3,"O1");
	}
	@Test
	void testToString() {
		assertEquals("O2 - geral - descrevi todo - 9",obj2.toString());
	}
	@Test
	void testEqualsObjetosIguais() {
		assertEquals(true,obj1.equals(obj3) && obj3.equals(obj1));
	}
	@Test
	void testEqualsObjetosDiferentes() {
		assertEquals(false,obj1.equals(obj2) && obj2.equals(obj2));
	}
	@Test
	void testEqualsMesmoObjeto() {
		assertEquals(true,obj1.equals(obj1));
	}
	@Test
	void testEqualsObjetoNull() {
		assertEquals(false,obj1.equals(null));
	}
	@Test
	void testHashCodeTrue() {
		assertEquals(true,obj1.hashCode() == obj3.hashCode());
	}  
	@Test
	void testHashCodeFalse() {
		assertEquals(false,obj1.hashCode() == obj2.hashCode());
	}

}
