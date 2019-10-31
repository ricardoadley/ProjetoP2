package testes_de_unidade;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import atividades.Item;

class ItemTest {

	Item item1;
	Item item2;
	Item item3;
	Item item4;
	
	@BeforeEach
	public void inicia() {
		item1 = new Item("Fazer cafe", 1);
		item2 = new Item("Pamonha", 2);
		item3 = new Item("Guarana nem tão doce", 3);
		item4 = new Item("Coca muito salgada", 1);
	}
	
	@Test
	void testHashCodeIgual() {
		
		assertTrue(item1.hashCode() == item4.hashCode());
	}
	
	@Test
	void testEqualsIgual() {
		
		assertTrue(item1.equals(item4));
		
	}
	
}
