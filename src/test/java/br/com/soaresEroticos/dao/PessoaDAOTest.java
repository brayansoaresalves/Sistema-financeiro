package br.com.soaresEroticos.dao;

import org.junit.Ignore;
import org.junit.Test;

import br.com.soaresEroticos.domain.Pessoa;

public class PessoaDAOTest {
	
	@Test
	@Ignore
	public void salvar() {
		
		Pessoa p1 = new Pessoa();
		
		p1.setBairro("Planalto");
		p1.setCelular("9999999999");
		p1.setCep("99999999");
		p1.setComplemento("Estadio JK");
		p1.setCpf("753121212");
		p1.setEmail("brendazeruela@gmail.com");
		p1.setNome("Brenda ");
		p1.setNumero(new Short("157"));
		p1.setRg("54545");
		p1.setRua("Rua princesa Isabel");
		p1.setTelefone("2121212");
		
		PessoaDAO dao = new PessoaDAO();
		
		dao.salvar(p1);
		
		System.out.println("Pessoa salva com sucesso");
	}
}
