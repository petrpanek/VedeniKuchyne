package com.github.petrpanek.VedeniKuchyne;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.github.petrpanek.VedeniKuchyne.logika.Potravina;
import com.github.petrpanek.VedeniKuchyne.logika.Recept;
import com.github.petrpanek.VedeniKuchyne.logika.ReceptPotravina;
import com.github.petrpanek.VedeniKuchyne.util.HibernateUtil;


public class App 
{
	public static void main(String[] args) {
		Session session = null;
		Transaction trns = null;
		
		Recept kulajda = new Recept("Kulajda", "Nakrajet, uvarit", 2.2);
		Potravina houska = new Potravina("Houska", 3);
		
		ReceptPotravina receptPotravina = new ReceptPotravina();
		receptPotravina.setRecept(kulajda);
		receptPotravina.setPotravina(houska);
		receptPotravina.setMnozstvi(2);
		
		session = HibernateUtil.getSessionFactory().openSession();
		trns = session.beginTransaction();
		
		session.save(receptPotravina);
		session.getTransaction().commit();
		
    }
}
