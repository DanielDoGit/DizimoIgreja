package telas;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

public class Teste {

	public static void main(String[] args) throws HibernateException, SQLException {
		// TODO Auto-generated method stub
		
		Configuration configuracao = new Configuration();
		SessionFactory fabricadeSessoes = configuracao.buildSessionFactory(); 
		Session sessao = fabricadeSessoes.getCurrentSession();
		sessao.connection().prepareStatement("Insert into teste");

	}

}
