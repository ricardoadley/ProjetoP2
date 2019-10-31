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
		this.pesquisa1 =  new Pesquisa("Alo", "meu, deus, do, ceu", "MEU1");
		this.pesquisa2 =  new Pesquisa("Alo", "meu, deus, do, ceu", "MEU2");
		this.pesquisa3 =  new Pesquisa("joao", "meu, deus, do, ceu", "MEU3");
		this.pesquisa4 =  new Pesquisa("Alo", "meu, deus, do, ceu", "MEU1"); 
	}
//	Verificador.verificaEntrada(descricao, "Descricao nao pode ser nula ou vazia.");
//	Verificador.verificaEntrada(campo, "Formato do campo de interesse invalido.");
//	Verificador.verificaEntrada(codigo,"Codigo nao pode ser nulo ou vazio");
	@Test
	void testConstroiPesquisaDescricaoVazio() {
		assertThrows(IllegalArgumentException.class, () -> new Pesquisa("","naruto,pokemon,java,c++","MEU1"));
	}
	@Test
	void testConstroiPesquisaDescricaoNull() {
		assertThrows(NullPointerException.class, () -> new Pesquisa(null,"naruto,pokemon,java,c++","MEU1"));
	}
	@Test
	void testConstroiPesquisaCampoVazio() {
		assertThrows(IllegalArgumentException.class, () -> new Pesquisa("assistir serie","","MEU1"));
	}
	@Test
	void testConstroiPesquisaCampoNull() {
		assertThrows(NullPointerException.class, () -> new Pesquisa("assistir borutinho",null,"MEU1"));
	}
	@Test
	void testToString() {
		//testando
		assertEquals("MEU1 - Alo - meu, deus, do, ceu", pesquisa1.toString());
	}
	
	@Test
	void testEquals() {
		//testando mesma pesquisa
		assertTrue(pesquisa1.equals(pesquisa1));
		
		//testando pesquisas iguais
		assertTrue(pesquisa1.equals(pesquisa4));
		
		//testando pesquisas diferentes
		assertFalse(pesquisa1.equals(pesquisa2));
		
		//testando objeto nulo
		assertFalse(pesquisa1.equals(null));
		
		//testando objeto de tipo diferente
		assertFalse(pesquisa1.equals(new Object()));
		
		//testando pesquisa com codigo null
		assertFalse(new Pesquisa("Alo", "meu", null).equals(new Pesquisa("Alo", "meu", "MEU1")));
	}
	
	@Test
	void testHashCode() {
		//testando pesquisas iguais
		assertTrue(pesquisa1.hashCode() == pesquisa4.hashCode());
		
		//testando pesquisas diferentes
		assertFalse(pesquisa1.hashCode() == pesquisa2.hashCode());
			
	} 
}
