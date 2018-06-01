package com.github.petrpanek.VedeniKuchyne.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.github.petrpanek.VedeniKuchyne.logika.Recept;
import com.github.petrpanek.VedeniKuchyne.util.HibernateUtil;

public class ReceptDAO {
	
	public static List<Object[]> getAllRecipes() {
		List<Object[]> recipes = new ArrayList<Object[]>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			session.beginTransaction();
			String queryString = "SELECT a.nazev, a.postup, a.obtiznost, b.mnozstvi, c.nazev "
					+ "FROM Recept a, ReceptPotravina b, Potravina c "
					+ "WHERE c.idPotraviny=b.potravina AND b.recept=a.idReceptu";
			recipes = session.createQuery(queryString).list();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return recipes;
	}
}
