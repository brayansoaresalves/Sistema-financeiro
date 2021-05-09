package br.com.soaresEroticos.util;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static final SessionFactory fabricaDeSessoes = criarFabricaDeSessoes();

	private static SessionFactory criarFabricaDeSessoes() {
		try {
			// Cria uma conexão a partir do hibernate.config.xml

			Configuration configuracao = new Configuration().configure();

			ServiceRegistry registro = new StandardServiceRegistryBuilder().applySettings(configuracao.getProperties())
					.build();

			SessionFactory fabricaDeSessoes = configuracao.buildSessionFactory(registro);
			return fabricaDeSessoes;

		} catch (Throwable ex) {
			// Mensagem de erro ao conectar
			System.err.println("Erro na conexão." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getFabricadesessoes() {
		return fabricaDeSessoes;

	}

	public static Connection getConexao() {
		Session sessao = fabricaDeSessoes.openSession();

		Connection conexao = sessao.doReturningWork(new ReturningWork<Connection>() {

			@Override
			public Connection execute(Connection conn) throws SQLException { // TODO Auto-generated method stub
				return conn;
			}
		});

		return conexao;
	}

}
