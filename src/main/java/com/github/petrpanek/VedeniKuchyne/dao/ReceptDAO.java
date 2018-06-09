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
	public static List<String> getIngredients(int idReceptu) {
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<String> ingredients = new ArrayList<>();
		
		try {
			trns = session.beginTransaction();
			Query q = session.createQuery("SELECT p.nazev FROM Potravina p JOIN ReceptPotravina rp WHERE p.idPotraviny=rp.potravina AND rp.recept=:idReceptu");
			q.setParameter("idReceptu", new Integer(idReceptu));
			ingredients =  q.list();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return ingredients;
	}
	
	public static List<Integer> getAmount(int idReceptu) {
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Integer> amount = new ArrayList<>();
		
		try {
			trns = session.beginTransaction();
			Query q = session.createQuery("SELECT rp.mnozstvi FROM Recept r JOIN r.receptPotraviny rp WHERE r.idReceptu = :idReceptu");
			q.setParameter("idReceptu", idReceptu);
			amount = q.list();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return amount;
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
	
	public static void updateRecept(Recept recept) {
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			trns = session.beginTransaction();
			session.update(recept);
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
	
	/*
	public static void updateRecept(int idReceptu, String postup, Double obtiznost) {
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			trns = session.beginTransaction();
			Query q = session.createQuery("update Recept set postup := postup, obtiznost := obtiznost where idReceptu := idReceptu");
			q.setParameter("postup", postup);
			q.setParameter("obtiznost", obtiznost);
			q.executeUpdate();
		} catch (RuntimeException exc) {
			if (trns != null) {
				trns.rollback();
			}
			exc.printStackTrace();
		} finally {
			session.close();
		}
		
	}
	*/
	
	
}
