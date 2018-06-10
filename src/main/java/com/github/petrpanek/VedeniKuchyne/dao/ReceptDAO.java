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

/**
 *  Třída ReceptDAO - obsahuje metody pro praci s databazi nad recepty
 *  Používá se pro CRUD operace nad recepty.
 *
 *  Tato třída je součástí aplikace receptar.
 *
 * @author     Petr Panek
 * @version    1.0
 */
public class ReceptDAO {
	
	/**
	 * Metoda slouzi k vytazeni vsech receptu z databaze.
	 *
	 * @return List<Object[]> List se vsemi recepty v databazi
	 */
	public static List<Object[]> getAllRecipes() {
		Transaction trns = null;
		List<Object[]> recipes = new ArrayList<Object[]>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			trns = session.beginTransaction();
			Query q = session.createQuery("SELECT r.idReceptu, r.nazev, r.postup, r.obtiznost FROM Recept r");
			recipes = q.list();
		} catch (RuntimeException exc) {
			if (trns != null) {
				trns.rollback();
			}
			exc.printStackTrace();
		} finally {
			session.close();
		}
		
		return recipes;
	}
	
	/**
	 * Metoda slouzi k vytazeni vsech ingredienci z databaze.
	 *
	 * @param idReceptu ID receptu, pro ktery chceme obdrzet ingredience
	 * @return List<String> List se vsemi ingrediencemi v databazi
	 */
	public static List<String> getIngredients(int idReceptu) {
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<String> ingredients = new ArrayList<>();
		
		try {
			trns = session.beginTransaction();
			Query q = session.createQuery("SELECT p.nazev FROM Recept r JOIN r.receptPotraviny rp JOIN rp.potravina p WHERE r.idReceptu = :idReceptu");
			q.setParameter("idReceptu", idReceptu);
			ingredients = q.list();
		} catch (RuntimeException exc) {
			if (trns != null) {
				trns.rollback();
			}
			exc.printStackTrace();
		} finally {
			session.close();
		}
		
		return ingredients;
	}
	
	/**
	 * Metoda slouzi k vytazeni poctu ingredienci z databaze.
	 *
	 * @param idReceptu ID receptu, pro ktery chceme obdrzet pocty ingredienci
	 * @return List<Integer> List se vsemi pocty ingredienci pro dany recept v databazi
	 */
	public static List<Integer> getAmount(int idReceptu) {
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Integer> amount = new ArrayList<>();
		
		try {
			trns = session.beginTransaction();
			Query q = session.createQuery("SELECT rp.mnozstvi FROM Recept r JOIN r.receptPotraviny rp WHERE r.idReceptu = :idReceptu");
			q.setParameter("idReceptu", idReceptu);
			amount = q.list();
		} catch (RuntimeException exc) {
			if (trns != null) {
				trns.rollback();
			}
			exc.printStackTrace();
		} finally {
			session.close();
		}
		
		return amount;
	}
	
	/**
	 * Metoda slouzi k odstraneni receptu z databaze.
	 *
	 * @param idReceptu ID receptu, ktery chceme odstranit
	 * @return List<String> List se vsemi ingrediencemi v databazi
	 */
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
	
	/**
	 * Metoda slouzi k aktualizaci receptu v databazi.
	 *
	 * @param idReceptu ID receptu, ktery chceme aktualizovat
	 * @param nazev Novy nazev receptu
	 * @param postup Novy postup receptu
	 * @param obtiznost Nova obtiznost receptu
	 */
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
	
	/**
	 * Metoda slouzi k ulozeni receptu do databaze.
	 *
	 * @param nazev Nazev receptu
	 * @param postup Postup receptu
	 * @param suroviny Suroviny obsazene v receptu
	 * @param pocet Pocet jednotlivych surovin v receptu
	 * @param obtiznost Obtiznost receptu
	 */
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
