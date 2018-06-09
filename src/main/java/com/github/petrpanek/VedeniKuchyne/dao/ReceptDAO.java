package com.github.petrpanek.VedeniKuchyne.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.github.petrpanek.VedeniKuchyne.logika.Potravina;
import com.github.petrpanek.VedeniKuchyne.logika.Recept;
import com.github.petrpanek.VedeniKuchyne.logika.ReceptPotravina;
import com.github.petrpanek.VedeniKuchyne.util.HibernateUtil;

public class ReceptDAO {
	
	public static List<Object[]> getAllRecipes() {
		List<Object[]> recipes = new ArrayList<Object[]>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			session.beginTransaction();
			Query q = session.createQuery("SELECT r.idReceptu, r.nazev, r.postup, r.obtiznost FROM Recept r");
			recipes = q.list();
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return recipes;
	}
	
	public static List<String> getIngredients(int idReceptu) {
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<String> ingredients = new ArrayList<>();
		
		try {
			trns = session.beginTransaction();
			Query q = session.createQuery("SELECT p.nazev FROM Recept r JOIN r.receptPotraviny rp JOIN rp.potravina p WHERE r.idReceptu = :idReceptu");
			q.setParameter("idReceptu", idReceptu);
			ingredients = q.list();
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
	
	public static void updateRecept(int idReceptu, String nazev, String postup, Double obtiznost) {
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			trns = session.beginTransaction();
			Query q = session.createQuery("UPDATE Recept set nazev = :nazev, postup = :postup, obtiznost = :obtiznost WHERE idReceptu = :idReceptu");
			q.setParameter("idReceptu", idReceptu);
			q.setParameter("nazev", nazev);
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
	
	public static void saveRecept(String nazev, String postup, String[] suroviny, String[] pocet, double obtiznost) {
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			Recept recept = new Recept(nazev, postup, obtiznost);
			
			for (int i = 0; i < suroviny.length; i++) {
				trns = session.beginTransaction();
				
				ReceptPotravina receptPotravina = new ReceptPotravina();
				receptPotravina.setRecept(recept);
				Potravina potravina = new Potravina(suroviny[i], 100);
				receptPotravina.setPotravina(potravina);
				receptPotravina.setMnozstvi(Integer.parseInt(pocet[i]));
				
				session.save(receptPotravina);
				session.getTransaction().commit();
			}
			
			session.close();
		} catch (RuntimeException exc) {
			exc.printStackTrace();
		}
	}
	
}
