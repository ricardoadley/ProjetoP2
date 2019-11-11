package testes_de_unidade;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import atividades.ControladorAtividade;
import pesquisa.PesquisaController;

class PesquisaControllerTest {

	PesquisaController pesquisaController;
	
	@BeforeEach
	void iniciar() {	
	pesquisaController = new PesquisaController(new ControladorAtividade());
	}
	
	@Test
	void testCadastraPesquisa() {
		//testando cadastramento
		pesquisaController.cadastraPesquisa("Alo", "meu, deus");
		pesquisaController.cadastraPesquisa("Alo", "meu, deus");
		
		//testando interesse maior do que 255
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.cadastraPesquisa("alo", "mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm"));
		
		//testando erros de vazio e nulo
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.cadastraPesquisa("", "meu, deus"));
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.cadastraPesquisa("Alo", ""));
		assertThrows(NullPointerException.class, () -> pesquisaController.cadastraPesquisa(null, "meu, deus"));
		assertThrows(NullPointerException.class, () -> pesquisaController.cadastraPesquisa("Alo", null));
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.cadastraPesquisa("Alo", "joa, quim, , eu"));
		
		//testando cadastrar com 5 codigos no campo de interesse
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.cadastraPesquisa("Alo", "meu, deus, do, ceu, olha"));
	}

	@Test
	void testAlteraPesquisa() {
		//cadastrando para teste
		pesquisaController.cadastraPesquisa("Alo", "meu, deus");
		pesquisaController.cadastraPesquisa("Alo", "socorro");
		pesquisaController.encerraPesquisa("SOC1", "sim");
		
		//testando alteracao
		pesquisaController.alteraPesquisa("MEU1", "DESCRICAO", "bom dia");
		pesquisaController.alteraPesquisa("MEU1", "CAMPO", "bom, dia, pesquisador");
		
		//testando alterar codigo
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.alteraPesquisa("MEU1", "codigo", "JOA1"));
		
		//testando erros de vazio e nulo
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.alteraPesquisa("MEU1", "DESCRICAO", ""));
		assertThrows(NullPointerException.class, () -> pesquisaController.alteraPesquisa("MEU1", "DESCRICAO", null));
	
		//testando pesquisa inexistente
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.alteraPesquisa("MEU2", "DESCRICAO", "bom dia"));
		
		//testando alterar pesquisa encerrada
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.alteraPesquisa("SOC1", "DESCRICAO", "bom dia"));
	}

	@Test
	void testExibePesquisa() {
		//cadastrando para testes
		pesquisaController.cadastraPesquisa("Alo", "meu, deus");
		pesquisaController.cadastraPesquisa("Alo", "socorro");
		pesquisaController.encerraPesquisa("SOC1", "sim");
		
		//testando
		assertEquals("MEU1 - Alo - meu, deus", pesquisaController.exibePesquisa("MEU1"));
		
		//testando pesquisa inexistente
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.exibePesquisa("JOA1"));
		
		//testando exibir pesquisa encerrada
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.exibePesquisa("SOC1"));
	}

	@Test
	void testAtivaPesquisa() {
		//cadastrando e encerrandopara teste
		pesquisaController.cadastraPesquisa("Alo", "meu, deus");
		pesquisaController.encerraPesquisa("MEU1", "teste");
		
		//testando
		pesquisaController.ativaPesquisa("MEU1");
		
		//testando pesquisa inexistente
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.ativaPesquisa("JOA1"));
		
		//testando ativar pesquisa ja ativa
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.ativaPesquisa("MEU1"));
	}

	@Test
	void testEncerraPesquisa() {
		//encerrando pesquisa
		pesquisaController.cadastraPesquisa("Alo", "meu, deus");
		
		//testando erros de vazio e nulo
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.encerraPesquisa("MEU1", ""));
		assertThrows(NullPointerException.class, () -> pesquisaController.encerraPesquisa("MEU1", null));
		
		//testando encerrar pesquisa inexistente
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.encerraPesquisa("JOA1", "TESTE"));
		
		//testando encerrar pesquisa ja encerrada
		pesquisaController.encerraPesquisa("MEU1", "TESTE");
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.encerraPesquisa("MEU1", "TESTE"));
	}

	@Test
	void testPesquisaEhAtiva() {
		//cadastrando para teste
		pesquisaController.cadastraPesquisa("Alo", "meu, deus");
		
		//testando
		assertTrue(pesquisaController.pesquisaEhAtiva("MEU1"));
		pesquisaController.encerraPesquisa("MEU1", "teste");
		assertFalse(pesquisaController.pesquisaEhAtiva("MEU1"));
		
		//testando erros de vazio e nulo
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.pesquisaEhAtiva(""));
		assertThrows(NullPointerException.class, () -> pesquisaController.pesquisaEhAtiva(null));
		
		//testando pesquisa inexistente
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.pesquisaEhAtiva("JOA1"));
	}

}
