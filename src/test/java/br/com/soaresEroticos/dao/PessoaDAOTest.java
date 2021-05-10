package br.com.soaresEroticos.dao;

import org.junit.Ignore;
import org.junit.Test;

import br.com.soaresEroticos.domain.Pessoa;

public class PessoaDAOTest {
	
	@Test
	@Ignore
	public void salvar() {
		
		Pessoa p1 = new Pessoa();
		
		Long codigoCidade = 7L;
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		
		p1.setBairro("Cidade Nova");
		p1.setCelular("(92)3690-2875");
		p1.setCep("69094-465");
		p1.setComplemento("Complemento");
		p1.setCpf("661.374.661-40");
		p1.setEmail("heloisealiciaclaudiaduarte-73@br.nestle.com");
		p1.setNome("Heloise Alícia Cláudia Duarte");
		p1.setNumero(new Short("223"));
		p1.setRg("37.540.901-4");
		p1.setRua("Rua Coronel Taborda de Miranda");
		p1.setTelefone("(92) 98830-6053");
	//	p1.setCidade(cidade);
		
		PessoaDAO dao = new PessoaDAO();
		
		dao.salvar(p1);
		
		System.out.println("Pessoa salva com sucesso");
	}
}
