package testes_de_unidade;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import atividades.Atividade;
import pesquisa.Pesquisa;


class AtividadeTest {

	private Atividade atividade1;
	private Atividade atividade2;
	private Atividade atividade3;
	private Pesquisa pesquisa1;

	@BeforeEach 
	public void iniciaAtividades() { 
		atividade1 = new Atividade("the flash", "BAIXO", "meio descrito", 0, "A1");
		atividade2 = new Atividade("game of thrones", "MEDIO", "pouco descrito", 1, "A1");
		atividade3 = new Atividade("supernatural", "ALTO", "MUITO DESCRITO!", 2, "A3");
		pesquisa1 = new Pesquisa("descobrir como faz teste", "computacao e mt bom", "COM1");
	}

	@Test
	void testConstroiAtividadeDescricaoVazio() {
	assertThrows(IllegalArgumentException.class, () -> new Atividade("","MEDIO","descrevendo",0,"A1"));
	} 
	@Test
	void testConstroiAtividadeDescricaoNull() {
		assertThrows(NullPointerException.class, () -> new Atividade(null,"MEDIO","descreve aqui",0,"A2"));
	}
	@Test
	void testConstroiAtividadeNivelRiscoVazio() {
		assertThrows(IllegalArgumentException.class, () -> new Atividade("assistir boruto","","eh muito bom",0,"A3"));
	}
	@Test
	void testConstroiAtividadeNivelRiscoNull() {
		assertThrows(NullPointerException.class, () -> new Atividade("assistir naruto",null,"eh bom eu gosto",0,"A4"));
	}
	@Test
	void testConstroiAtividadeDescricaoRiscoVazio() {
		assertThrows(IllegalArgumentException.class, () -> new Atividade("maratonar pokemon","BAIXO","",0,"A5"));
	}
	@Test
	void testConstroiAtividadeDescricaoRiscoNull() {
		assertThrows(NullPointerException.class, () -> new Atividade("procurar serie nova","ALTO",null,0,"A6"));
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
		Atividade atividade4 = new Atividade("the flash", "BAIXO", "meio descrito", 0, null);
		assertFalse(atividade1.equals(atividade4));
		
	}
	
	@Test
	void testEqualsCodigoNull2() {
		Atividade atividade4 = new Atividade("the flash", "BAIXO", "meio descrito", 0, null);
		assertFalse(atividade4.equals(atividade1));
		
	}
	
	@Test
	void testEqualsCodigoNull3() {
		Atividade atividade5 = new Atividade("supergirl", "ALTO", "descritissimo", 0, null);
		Atividade atividade4 = new Atividade("the flash", "BAIXO", "meio descrito", 0, null);
		
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
	
	@Test
	void testAssociaPesquisa() {
		
		atividade1.associaPesquisa("COM1");
		assertTrue(atividade1.contemPesquisasAssociadas());
		
	}
	
	@Test
	void testDesassociaPesquisa() {
		
		atividade1.associaPesquisa("COM1");
		atividade1.desassociaPesquisa("COM1");
		assertFalse(atividade1.contemPesquisasAssociadas());
		
	}
}
