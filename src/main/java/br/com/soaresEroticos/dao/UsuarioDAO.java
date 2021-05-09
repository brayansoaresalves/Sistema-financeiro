package br.com.soaresEroticos.dao;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.soaresEroticos.domain.Usuario;
import br.com.soaresEroticos.util.HibernateUtil;

public class UsuarioDAO extends GenericDAO<Usuario>{

	
	public Usuario autenticar(String cpf, String senha) {
		Session sessao = HibernateUtil.getFabricadesessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Usuario.class);
			consulta.createAlias("pessoa","p");
			consulta.add(Restrictions.eqOrIsNull("p.cpf", cpf));
			
			SimpleHash hash = new SimpleHash("md5", senha);
			consulta.add(Restrictions.eqOrIsNull("senha", hash.toHex()));
			Usuario resultado = (Usuario) consulta.uniqueResult();
			
			
			return resultado; 
		}catch(RuntimeException erro){
			
			throw erro;
			
		}finally {
			sessao.close();
		}
	}
}
