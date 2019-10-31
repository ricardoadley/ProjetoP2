package testes_de_unidade;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pesquisador.PesquisadorController;

class TestesPesquisadorController {

	private PesquisadorController controlador ;
	@BeforeEach
	void iniciar() {
		controlador = new PesquisadorController();
		controlador.cadastraPesquisador("ricardo","pesquisar","toda aqui","ricardo@email.com", "https://fotinhaminha.jpg");
	}
	@Test
	void testCadastraPesquisadorNomeVazio() {
		assertThrows(IllegalArgumentException.class, () -> controlador.cadastraPesquisador("","pesquisar","toda aqui","ricardo@email.com", "https://fotinhaminha.jpg"));
	}
	@Test
	void testCadastraPesquisadorNomeNull() {
		assertThrows(NullPointerException.class, () -> controlador.cadastraPesquisador(null,"pesquisar","toda aqui","ricardo@email.com", "https://fotinhaminha.jpg"));
	}
	//this.mapaEmailPesquisador.put(email,  new Pesquisador(nome, funcao, biografia, email, fotoURL));
	@Test
	void testCadastraPesquisadorFuncaoVazio() {
		assertThrows(IllegalArgumentException.class, () -> controlador.cadastraPesquisador("ricardo","","toda aqui","ricardo@email.com", "https://fotinhaminha.jpg"));
	}
	@Test
	void testCadastraPesquisadorFuncaoNull() {
		assertThrows(NullPointerException.class, () -> controlador.cadastraPesquisador("ricardo",null,"toda aqui","ricardo@email.com", "https://fotinhaminha.jpg"));
	}
	@Test
	void testCadastraPesquisadorBiografiaVazio() {
		assertThrows(IllegalArgumentException.class, () -> controlador.cadastraPesquisador("ricardo","pesquisar","","ricardo@email.com", "https://fotinhaminha.jpg"));
	}
	@Test
	void testCadastraPesquisadorBiografiaNull() {
		assertThrows(NullPointerException.class, () -> controlador.cadastraPesquisador("ricardo","pesquisar",null,"ricardo@email.com", "https://fotinhaminha.jpg"));
	}
	@Test
	void testCadastraPesquisadorEmailVazio() {
		assertThrows(IllegalArgumentException.class, () -> controlador.cadastraPesquisador("ricardo","pesquisar","toda aqui","", "https://fotinhaminha.jpg"));
	}
	@Test
	void testCadastraPesquisadorEmailNull() {
		assertThrows(NullPointerException.class, () -> controlador.cadastraPesquisador("ricardo","pesquisar","toda aqui",null, "https://fotinhaminha.jpg"));
	}
	@Test
	void testCadastraPesquisadorURLfotoVazio() {
		assertThrows(IllegalArgumentException.class, () -> controlador.cadastraPesquisador("ricardo","pesquisar","toda aqui","ricardo@email.com", ""));
	}
	@Test
	void testCadastraPesquisadorURLfotoNull() {
		assertThrows(NullPointerException.class, () -> controlador.cadastraPesquisador("ricardo","pesquisar","toda aqui","ricardo@email.com", null));
	}
	@Test
	void testCadastraPesquisadorURLfotoInvalida() {
		assertThrows(IllegalArgumentException.class, () -> controlador.cadastraPesquisador("ricardo","pesquisar","toda aqui","ricardo@email.com", "fotominha.com"));
	} 
	@Test
	void testAlteraPesquisadorEmailVazio() {
		assertThrows(IllegalArgumentException.class, () -> controlador.alteraPesquisador("","NOME", "boruto Uzumaki"));
	}
	@Test
	void testAlteraPesquisadorEmailNull() {
		assertThrows(NullPointerException.class, () -> controlador.alteraPesquisador(null,"NOME", "boruto Uzumaki"));
	}
	@Test
	void testAlteraPesquisadorAtributoVazio() {
		assertThrows(IllegalArgumentException.class, () -> controlador.alteraPesquisador("ricardo@email.com","", "boruto Uzumaki"));
	}
	@Test
	void testAlteraPesquisadorAtributoNull() {
		assertThrows(NullPointerException.class, () -> controlador.alteraPesquisador("ricardo@email.com",null, "boruto Uzumaki"));
	}
	@Test
	void testAlteraPesquisadorNaoEncontrado() {
		assertThrows(IllegalArgumentException.class, () -> controlador.alteraPesquisador("jose@email.com","EMAIL", "borutoUzumaki@email.com"));
	}
	@Test
	void testAlteraPesquisadorNovoValorNomeVazio() {
		assertThrows(IllegalArgumentException.class, () -> controlador.alteraPesquisador("ricardo@email.com","NOME",""));
	}
	@Test
	void testAlteraPesquisadorNovoValorNomeNull() {
		assertThrows(NullPointerException.class, () -> controlador.alteraPesquisador("ricardo@email.com","NOME",null));
	}
	@Test
	void testAlteraPesquisadorNovoValorFuncaoVazio() {
		assertThrows(IllegalArgumentException.class, () -> controlador.alteraPesquisador("ricardo@email.com","FUNCAO",""));
	}
	@Test 
	void testAlteraPesquisadorNovoValorFuncaoNull() {
		assertThrows(NullPointerException.class, () -> controlador.alteraPesquisador("ricardo@email.com","FUNCAO",null));
	}
	@Test
	void testAlteraPesquisadorNovoValorBiografiaVazio() {
		assertThrows(IllegalArgumentException.class, () -> controlador.alteraPesquisador("ricardo@email.com","BIOGRAFIA",""));
	}
	@Test
	void testAlteraPesquisadorNovoValorBiografiaNull() {
		assertThrows(NullPointerException.class, () -> controlador.alteraPesquisador("ricardo@email.com","BIOGRAFIA",null));
	}
	@Test
	void testAlteraPesquisadorNovoValorEmailVazio() {
		assertThrows(IllegalArgumentException.class, () -> controlador.alteraPesquisador("ricardo@email.com","EMAIL",""));
	} 
	@Test
	void testAlteraPesquisadorNovoValorEmailNull() {
		assertThrows(NullPointerException.class, () -> controlador.alteraPesquisador("ricardo@email.com","EMAIL",null));
	}
	@Test
	void testAlteraPesquisadorNovoValorEmailFormatoInvalido() {
		assertThrows(IllegalArgumentException.class, () -> controlador.alteraPesquisador("ricardo@email.com","EMAIL", "eh assim o email"));
	}
	@Test
	void testAlteraPesquisadorNovoValorFotoVazio() {
		assertThrows(IllegalArgumentException.class, () -> controlador.alteraPesquisador("ricardo@email.com","FOTO",""));
	}
	@Test
	void testAlteraPesquisadorNovoValorFotoNull() {
		assertThrows(NullPointerException.class, () -> controlador.alteraPesquisador("ricardo@email.com","FOTO",null));
	}
	@Test
	void testAlteraPesquisadorNovoValorFotoFormatoInvalido() {
		assertThrows(IllegalArgumentException.class, () -> controlador.alteraPesquisador("ricardo@email.com","FOTO","fotominha.com"));
	}
	@Test
	void testAlteraPesquisadorAtributoInvalido() {
		assertThrows(IllegalArgumentException.class, () -> controlador.alteraPesquisador("ricardo@email.com","VELOCIDADE","muito rapido meu pai"));
	}
	@Test
	void testAlteraPesquisadorInativo() {
		controlador.desativaPesquisador("ricardo@email.com");
		assertThrows(IllegalArgumentException.class, () -> controlador.alteraPesquisador("ricardo@email.com","EMAIL","adoido@email.com"));
	}
	@Test
	void testExibePesquisadorPerfeito() {
		assertEquals("ricardo (pesquisar) - toda aqui - ricardo@email.com - https://fotinhaminha.jpg",controlador.exibePesquisador("ricardo@email.com"));
	}
	@Test
	void testExibePesquisadorEmailVazio() {
		assertThrows(IllegalArgumentException.class, () -> controlador.exibePesquisador(""));
	}
	@Test
	void testExibePesquisadorEmailNull() {
		assertThrows(NullPointerException.class, () -> controlador.exibePesquisador(null));
	}
	@Test
	void testExibePesquisadorInativo() {
		controlador.desativaPesquisador("ricardo@email.com");
		assertThrows(IllegalArgumentException.class, () -> controlador.exibePesquisador("ricardo@email.com"));
	}
	@Test
	void testAtivaPesquisadorEmailVazio() {
		assertThrows(IllegalArgumentException.class, () -> controlador.ativaPesquisador(""));
	}
	@Test
	void testAtivaPesquisadorEmailNull() {
		assertThrows(NullPointerException.class, () -> controlador.ativaPesquisador(null));
	}
	@Test
	void testAtivaPesquisadorJaAtivo() {
		assertThrows(IllegalArgumentException.class, () -> controlador.ativaPesquisador("ricardo@email.com"));
	}
	@Test
	void testAtivaPesquisadorNaoEncontrado() {
		assertThrows(IllegalArgumentException.class, () -> controlador.ativaPesquisador("narutinho@email.com"));
	}
	@Test
	void testDesativaPesquisadorEmailVazio() {
		assertThrows(IllegalArgumentException.class, () -> controlador.desativaPesquisador(""));
	}
	@Test
	void testDesativaPesquisadorEmailNull() {
		assertThrows(NullPointerException.class, () -> controlador.desativaPesquisador(null));
	}
	@Test
	void testDesativaPesquisadorJaDesativado() {
		controlador.desativaPesquisador("ricardo@email.com");
		assertThrows(IllegalArgumentException.class, () -> controlador.desativaPesquisador("ricardo@email.com"));
	}
	@Test
	void testDesativaPesquisadorNaoEncontrado() {
		assertThrows(IllegalArgumentException.class, () -> controlador.desativaPesquisador("narutinho@email.com"));
	}
	@Test
	void testPesquisadorEhAtivoEmailVazio() {
		assertThrows(IllegalArgumentException.class, () -> controlador.pesquisadorEhAtivo(""));
	}
	@Test
	void testPesquisadorEhAtivoEmailNull() {
		assertThrows(NullPointerException.class, () -> controlador.pesquisadorEhAtivo(null));
	}
	@Test
	void testPesquisadorEhAtivoPesquisadorNaoEncontrado() {
		assertThrows(IllegalArgumentException.class, () -> controlador.pesquisadorEhAtivo("narutinho@email.com"));
	}
	@Test
	void testPesquisadorEhAtivoTrue() {
		assertEquals(true,controlador.pesquisadorEhAtivo("ricardo@email.com"));
	}
	@Test
	void testPesquisadorEhAtivoFalse() {
		controlador.desativaPesquisador("ricardo@email.com");
		assertEquals(false,controlador.pesquisadorEhAtivo("ricardo@email.com"));
	}	
}
