package br.com.soaresEroticos.test;

import org.hibernate.Session;
import org.junit.Ignore;
import org.junit.Test;

import br.com.soaresEroticos.util.HibernateUtil;

public class HibernateUtilTest {
	@Test
	public void conectar(){
		Session sessao = HibernateUtil.getFabricadesessoes().openSession();
		sessao.close();
		HibernateUtil.getFabricadesessoes().close();
	}
}
