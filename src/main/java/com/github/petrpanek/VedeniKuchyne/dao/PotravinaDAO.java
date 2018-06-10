package com.github.petrpanek.VedeniKuchyne.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.github.petrpanek.VedeniKuchyne.logika.Potravina;
import com.github.petrpanek.VedeniKuchyne.util.HibernateUtil;

/**
 *  Třída PotravinaDAO - obsahuje metody pro praci s databazi nad potravinami
 *  Používá se pro CRUD operace nad potravinami.
 *
 *  Tato třída je součástí aplikace receptar.
 *
 * @author     Petr Panek
 * @version    1.0
 */
public class PotravinaDAO {
	
	 /**
	 * Metoda uklada potravinu do databaze.
	 *
	 * @param potravina Potravina pro ulozeni
	 */
	public static void addPotravina(Potravina potravina) {
		Transaction trns = null;
		Session session = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			trns = session.beginTransaction();
			
			session.save(potravina);
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
	 * Metoda odstranuje potravinu z databaze.
	 *
	 * @param idPotraviny Potravina pro odstraneni
	 */
	public static void deletePotravina(int idPotraviny) {
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			trns = session.beginTransaction();
			Potravina potravina = (Potravina) session.load(Potravina.class, new Integer(idPotraviny));
			session.delete(potravina);
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
	 * Metoda aktualizuje potravinu v databazi.
	 *
	 * @param potravina Potravina pro aktualizovani
	 */
	public static void updatePotravina(Potravina potravina) {
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			trns = session.beginTransaction();
			session.update(potravina);
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
	 * Metoda slouzi k vytazeni vsech potravin z databaze.
	 *
	 * @return List<Potravina> List se vsemi potravinami v databazi
	 */
	public static List<Potravina> getAllPotraviny() {
		List<Potravina> potraviny = new ArrayList<Potravina>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			session.beginTransaction();
			potraviny = session.createQuery("from Potravina").list();
		} catch (RuntimeException exc) {
			exc.printStackTrace();
		} finally {			
			session.close();
		}
		
		return potraviny;
	}
}
