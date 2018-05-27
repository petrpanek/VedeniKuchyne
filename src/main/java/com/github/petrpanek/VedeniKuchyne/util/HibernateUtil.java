package com.github.petrpanek.VedeniKuchyne.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.github.petrpanek.VedeniKuchyne.logika.Potravina;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();
	
	private static SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			return new Configuration()
					.configure("hibernate.cfg.xml")
					.addAnnotatedClass(Potravina.class)
					.buildSessionFactory();
		} catch (Throwable exc) {
			System.err.println("Initial SessionFactory creation failed." + exc);
			throw new ExceptionInInitializerError(exc);
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}