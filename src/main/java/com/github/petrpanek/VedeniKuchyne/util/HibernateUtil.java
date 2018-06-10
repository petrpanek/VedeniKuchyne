package com.github.petrpanek.VedeniKuchyne.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.github.petrpanek.VedeniKuchyne.logika.Potravina;
import com.github.petrpanek.VedeniKuchyne.logika.Recept;
import com.github.petrpanek.VedeniKuchyne.logika.ReceptPotravina;

/**
 *  Třída HibernateUtil - trida pro pripojeni k databazi
 *  Trida obsahuje atributy a metody pro praci s databazi
 *
 *  Tato třída je součástí aplikace receptar.
 *
 * @author     Petr Panek
 * @version    1.0
 */
public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();
	
	/**
	 * Metoda slouzici ke konfiguraci pripojeni skrze Hibernate 
	 */
	private static SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			return new Configuration()
					.configure("hibernate.cfg.xml")
					.addAnnotatedClass(Potravina.class)
					.addAnnotatedClass(Recept.class)
					.addAnnotatedClass(ReceptPotravina.class)
					.buildSessionFactory();
		} catch (Throwable exc) {
			System.err.println("Initial SessionFactory creation failed." + exc);
			throw new ExceptionInInitializerError(exc);
		}
	}
	
	/**
	 * Metoda vracejici SessionFactory
	 * 
	 * @return SessionFactory sessionFactory
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
