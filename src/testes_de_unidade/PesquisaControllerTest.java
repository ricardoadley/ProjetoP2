package testes_de_unidade;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import atividades.ControladorAtividade;
import pesquisa.PesquisaController;

class PesquisaControllerTest {

	private PesquisaController pesquisaController;
	private ControladorAtividade controladorAtividade;
	
	@BeforeEach
	void iniciar() {	
	controladorAtividade = new ControladorAtividade();
	pesquisaController = new PesquisaController(controladorAtividade);
	controladorAtividade.cadastraAtividade("Atividade de teste","MEDIO", "eh um risco arriscado");
	pesquisaController.cadastraPesquisa("Pesquisa Teste Certo","testes,certo");
	}
	
	@Test
	void testCadastraPesquisaComSucesso() {
		assertEquals("MEU1",pesquisaController.cadastraPesquisa("Alo", "meu, deus"));
	}
	@Test
	void testCadastraPesquisaInteresseMaiorQue255() {
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.cadastraPesquisa("alo", "mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm"));	
	}
	@Test
	void testCadastraPesquisaDescricaoVazio() {
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.cadastraPesquisa("", "meu, deus"));
	}
	@Test
	void testCadastraPesquisaDescricaoNull() {
		assertThrows(NullPointerException.class, () -> pesquisaController.cadastraPesquisa(null, "meu, deus"));	
	}
	@Test
	void testCadastraPesquisaCampoInteresseVazio() {
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.cadastraPesquisa("Alo", ""));
	}
	@Test
	void testCadastraPesquisaCampoInteresseNull() {
		assertThrows(NullPointerException.class, () -> pesquisaController.cadastraPesquisa("Alo", null));
	}
	@Test
	void testCadastraPesquisaParteCampoDeInteresseVazio() {
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.cadastraPesquisa("Alo", "joa, quim, , eu"));
	}
	@Test 
	void testCadastraPesquisaMaisDe4CampoDeInteresse(){
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.cadastraPesquisa("Alo", "meu, deus, do, ceu, olha"));
	}
	@Test
	void testAlteraPesquisaComSucesso() {
		pesquisaController.cadastraPesquisa("Alo", "meu, deus");
		pesquisaController.alteraPesquisa("MEU1", "DESCRICAO", "bom dia");
		pesquisaController.alteraPesquisa("MEU1", "CAMPO", "bom, dia, pesquisador");
	}
	@Test
	void testAlteraPesquisaCampoCodigo() {
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.alteraPesquisa("MEU1", "CODIGO", "JOA1"));
	}
	@Test
	void testAlteraPesquisaCampoDescricaoNovoConteudoVazio() {
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.alteraPesquisa("MEU1", "DESCRICAO", ""));
	}
	@Test
	void testAlteraPesquisaCampoDescricaoNovoConteudoNull() {
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.alteraPesquisa("MEU1", "DESCRICAO", null));
	}
	@Test
	void testAlteraPesquisaCampoDeInteresseVazio() {
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.alteraPesquisa("MEU1","CAMPO",""));
	}
	@Test
	void testAlteraPesquisaCampoDeInteresseNull() {
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.alteraPesquisa("MEU1","CAMPO",null));
	}
	@Test
	void testAlteraPesquisaInexistente() {
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.alteraPesquisa("MEU2", "DESCRICAO", "bom dia"));
	}
	@Test
	void testAlteraPesquisaEncerrada() {
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.alteraPesquisa("SOC1", "DESCRICAO", "bom dia"));
	}
	@Test
	void testExibePesquisaComSucesso() {
		pesquisaController.cadastraPesquisa("Alo", "meu, deus");
		assertEquals("MEU1 - Alo - meu, deus", pesquisaController.exibePesquisa("MEU1"));
	}
	@Test
	void testExibePesquisaInexistente() {
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.exibePesquisa("JOA1"));
	}
	@Test
	void testExibePesquisaEncerrada() {
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.exibePesquisa("SOC1"));
	}
	@Test
	void testAtivaPesquisaComSucesso() {
		//cadastrando e encerrandopara teste
		pesquisaController.cadastraPesquisa("Alo", "meu, deus");
		pesquisaController.encerraPesquisa("MEU1", "teste");
		pesquisaController.ativaPesquisa("MEU1");
	}
	@Test
	void testAtivaPesquisaInexistente() {
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.ativaPesquisa("JOA1"));
	}
	@Test
	void testAtivaPesquisaJaAtiva() {
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.ativaPesquisa("MEU1"));
	}	
	@Test
	void testEncerraPesquisaComSucesso() {
		//encerrando pesquisa
		pesquisaController.cadastraPesquisa("Alo", "meu, deus");
		pesquisaController.encerraPesquisa("MEU1","testando doido");
	}
	@Test
	void testEncerraPesquisaMotivoVazio() {
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.encerraPesquisa("MEU1", ""));
	}
	@Test
	void testEncerraPesquisaMotivoNull() {
		assertThrows(NullPointerException.class, () -> pesquisaController.encerraPesquisa("MEU1", null));
	}
	@Test
	void testEncerraPesquisaInexistente() {
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.encerraPesquisa("JOA1", "TESTE"));
	}
	@Test
	void testEncerraPesquisaEncerrada() {
		pesquisaController.cadastraPesquisa("Alo", "meu, deus");
		pesquisaController.encerraPesquisa("MEU1", "TESTE");
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.encerraPesquisa("MEU1", "TESTE"));
	}
	@Test
	void testPesquisaEhAtivaTrue() {
		assertTrue(pesquisaController.pesquisaEhAtiva("TES1"));
	}
	@Test
	void testPesquisaEhAtivaFalse() {
		pesquisaController.encerraPesquisa("TES1", "teste");
		assertFalse(pesquisaController.pesquisaEhAtiva("TES1"));
	}
	@Test
	void testPesquisaEhAtivaCodigoPesquisaVazio() {
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.pesquisaEhAtiva(""));
	}
	@Test
	void testPesquisaEhAtivaCodigoPesquisaNull() {
		assertThrows(NullPointerException.class, () -> pesquisaController.pesquisaEhAtiva(null));
	}
	@Test
	void testPesquisaEhAtivaPesquisaInexistente() {
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.pesquisaEhAtiva("JOA1"));
	}	
	@Test
	void testAssociaAtividadeComSucesso() {
		assertEquals(true,pesquisaController.associaAtividade("TES1","A1"));
	}
	@Test
	void testAssociaAtividadeCampoCodigoPesquisaVazio() {
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.associaAtividade("","A1"));
	}
	@Test
	void testAssociaAtividadeCampoCodigoPesquisaNull() {
		assertThrows(NullPointerException.class, () -> pesquisaController.associaAtividade(null,"A1"));
	}
	@Test
	void testAssociaAtividadeCampoCodigoAtividadeVazio() {
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.associaAtividade("TES1",""));
	}
	@Test
	void testAssociaAtividadeCompoCodigoAtividadeNull() {
		assertThrows(NullPointerException.class, () -> pesquisaController.associaAtividade("TES1",null));		
	}
	@Test
	void testAssociaAtividadePesquisaNaoEncontrada() {
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.associaAtividade("PES1","A1"));
	}
	@Test
	void testAssociaAtividadeNaoEncontrada() {
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.associaAtividade("TES1","A23"));
	}
	@Test
	void testAssociaAtividadePesquisaDesativada() {
		pesquisaController.encerraPesquisa("TES1","para testar");
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.associaAtividade("TES1","A1"));
	}
	@Test
	void testDesassociaAtividadeComSucesso() {
		pesquisaController.associaAtividade("TES1","A1");
		assertEquals(true, pesquisaController.desassociaAtividade("TES1","A1"));
	}
	@Test
	void testDesassociaAtividadeCodigoPesquisaVazio() {
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.desassociaAtividade("","A1"));
	}
	@Test
	void testDesassociaAtividadeCodigoPesquisaNull() {
		assertThrows(NullPointerException.class, () -> pesquisaController.desassociaAtividade(null,"A1"));
	}
	@Test
	void testDesassociaAtividadeCodigoAtividadeVazio() {
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.desassociaAtividade("TES1",""));
	}
	@Test
	void testDesassociaAtividadeCodigoAtividadeNull() {
		assertThrows(NullPointerException.class, () -> pesquisaController.desassociaAtividade("TES1",null));
	}
	@Test
	void testDesassociaAtividadePesquisaNaoEncontrada() {
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.desassociaAtividade("PES1","A1"));
	}
	@Test
	void testDesassociaAtividadeNaoEncontrada() {
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.desassociaAtividade("TES1","A24"));
	}
	@Test
	void testDesassociaAtividadePesquisaDesativada() {
		pesquisaController.encerraPesquisa("TES1","para testar");
		assertThrows(IllegalArgumentException.class, () -> pesquisaController.desassociaAtividade("TES1","A1"));
	}
}
