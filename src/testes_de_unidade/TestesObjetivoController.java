package testes_de_unidade;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import objetivo.ObjetivoController;

class TestesObjetivoController {

	private ObjetivoController controlador;
	@BeforeEach
	void iniciar() {
		controlador = new ObjetivoController();
	} 
	
	@Test
	void testCadastraObjetivoPerfeito() {
		assertEquals("O1",controlador.cadastraObjetivo("GERAL","descrito", "3", "4"));
	}
	@Test
	void testCadastraObjetivoTipoVazio() {
		assertThrows(IllegalArgumentException.class, () -> controlador.cadastraObjetivo("","descrita toda","3", "2"));
	}
	@Test
	void testCadastraObjetivoTipoNull() {
		assertThrows(NullPointerException.class, () -> controlador.cadastraObjetivo(null, "descrita toda","3", "2"));
	}
	@Test
	void testCadastraObjetivoDescricaoVazio() {
		assertThrows(IllegalArgumentException.class, () -> controlador.cadastraObjetivo("GERAL","", "4", "4"));
	}
	@Test
	void testCadastraObjetivoDescricaoNull() {
		assertThrows(NullPointerException.class, () -> controlador.cadastraObjetivo("GERAL",null,"5", "4"));
	}
	@Test
	void testCadastraObjetivoAderenciaVazio() {
		assertThrows(IllegalArgumentException.class, () -> controlador.cadastraObjetivo("GERAL","descreve vai","","3"));
	}
	@Test
	void testCadastraObjetivoAderenciaNull(){
		assertThrows(NullPointerException.class, () -> controlador.cadastraObjetivo("GERAL","descreve a descricao",null,"4"));
	}
	@Test
	void testCadastraOjetivoViabilidadeVazio() {
		assertThrows(IllegalArgumentException.class, () -> controlador.cadastraObjetivo("GERAL","descreveu","4",""));
	}
	@Test
	void testCadastraObjetivoViabilidadeNull() {
		assertThrows(NullPointerException.class, () -> controlador.cadastraObjetivo("GERAL","af descrevi","5", null));
	}
	@Test
	void testCadastraObjetivoValorInvalidoViabilidade() {
		assertThrows(NumberFormatException.class, () -> controlador.cadastraObjetivo("GERAL","descreve o erro pai","4","L"));
	}
	@Test
	void testCadastraObjetivoValorInvalidoAderencia() {
		assertThrows(NumberFormatException.class, () -> controlador.cadastraObjetivo("GERAL","descreve aqui","J","3"));
	}
	@Test
	void testCadastraObjetivoValorInvalidoTipo() {
		assertThrows(IllegalArgumentException.class, () -> controlador.cadastraObjetivo("geralzao","descrito","3","4"));
	}
	@Test
	void testCadastraObjetivoAderenciaMaiorQueCinco() {
		assertThrows(IllegalArgumentException.class, () -> controlador.cadastraObjetivo("GERAL","descrito","6","4"));
	}
	@Test
	void testCadastraObjetivoAderenciaMenorQueUm() {
		assertThrows(IllegalArgumentException.class, () -> controlador.cadastraObjetivo("GERAL","descrito","0","4"));
	}
	@Test
	void testCadastraObjetivoViabilidadeMaiorQueCinco() {
		assertThrows(IllegalArgumentException.class, () -> controlador.cadastraObjetivo("GERAL","descreve todo","3", "7"));
	}
	@Test
	void testCadastraObjetivoViabilidadeMenorQueCinco() {
		assertThrows(IllegalArgumentException.class, () -> controlador.cadastraObjetivo("GERAL","descrever aqui","5","0"));
	}
	@Test
	void testApagarObjetivoCodigoVazio() {
		assertThrows(IllegalArgumentException.class, () -> controlador.apagarObjetivo(""));
	}
	@Test
	void testApagarObjetivoCodigoNull() {
		assertThrows(NullPointerException.class, () -> controlador.apagarObjetivo(null));
	}
	@Test
	void testApagarObjetivoNaoEcontrado() {
		assertThrows(IllegalArgumentException.class, () -> controlador.apagarObjetivo("O234"));
	}
	@Test
	void testExibirObjetivoCodigoVazio() {
		assertThrows(IllegalArgumentException.class, () -> controlador.exibeObjetivo(""));
	}
	@Test
	void testExibeObjetivoCodigoNull() {
		assertThrows(NullPointerException.class, () -> controlador.exibeObjetivo(null));
	}
	@Test
	void testExibeObjetivoNaoEncontrado() {
		assertThrows(IllegalArgumentException.class, () -> controlador.exibeObjetivo("O123"));
	}
	@Test
	void testExibeObjetivoPerfeito() {
		controlador.cadastraObjetivo("GERAL","descrito para descrever", "2","3");
		assertEquals("O1 - GERAL - descrito para descrever - 5",controlador.exibeObjetivo("O1"));
	}
}
