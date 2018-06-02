package com.github.petrpanek.VedeniKuchyne.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.github.petrpanek.VedeniKuchyne.logika.Recept;
import com.github.petrpanek.VedeniKuchyne.util.HibernateUtil;

public class ReceptDAO {
	
	public static List<Object[]> getAllRecipes() {
		List<Object[]> recipes = new ArrayList<Object[]>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			session.beginTransaction();
			String queryString = "SELECT a.idReceptu, a.nazev, a.postup, a.obtiznost, b.mnozstvi, c.nazev "
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
	
	// NEFUNGUJE!!! NEVIM PROC...
	public static List<Object[]> getIngredients(int id) {
		List<Object[]> ingredients = new ArrayList<Object[]>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			session.beginTransaction();
			String query = "SELECT p.nazev FROM Potravina p JOIN ReceptPotravina rp WHERE rp.recept.idReceptu=:id";
			ingredients = session.createQuery(query).setInteger("id", 6).list();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return ingredients;
	}
	
	public static void deleteRecept(int idReceptu) {
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			trns = session.beginTransaction();
			Query q = session.createQuery("from Recept where idReceptu = :idReceptu");
			q.setParameter("idReceptu", idReceptu);
			Recept recept = (Recept) q.list().get(0);
			session.delete(recept);
			session.getTransaction().commit();
		} catch (RuntimeException exc) {
			if (trns != null) {
				trns.rollback();
			} 
			exc.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	
}
