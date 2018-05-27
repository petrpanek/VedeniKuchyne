package com.github.petrpanek.VedeniKuchyne.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.github.petrpanek.VedeniKuchyne.logika.Potravina;
import com.github.petrpanek.VedeniKuchyne.util.HibernateUtil;

public class PotravinaDAO {
	
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
	
	public static List<Potravina> getAllPotraviny() {
		List<Potravina> potraviny = new ArrayList<Potravina>();
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			trns = session.beginTransaction();
			potraviny = session.createQuery("from Potravina").list();
		} catch (RuntimeException exc) {
			exc.printStackTrace();
		} finally {			
			session.close();
		}
		
		return potraviny;
	}
}
