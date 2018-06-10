package com.github.petrpanek.VedeniKuchyne;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.github.petrpanek.VedeniKuchyne.dao.ReceptDAO;

public class ReceptDAOTest {
	
	@Test
	public void testReceptDAO() {
		// Ulozeni Receptu
		ReceptDAO.saveRecept("Kulajda", "Uvarit, nakrajet, promichat", new String[] {"voda"}, new String[] {"1"}, 2.2);
		List<Object[]> recepty = ReceptDAO.getAllRecipes();
		String nazev = recepty.get(recepty.size() - 1)[1].toString();
		int idReceptu = Integer.parseInt(recepty.get(recepty.size() - 1)[0].toString());
		
		Assert.assertEquals("Kulajda", nazev);
		
		// Zjisteni poctu surovin
		List<Integer> pocetSurovin = ReceptDAO.getAmount(idReceptu);
		
		Assert.assertEquals(Integer.valueOf(1), pocetSurovin.get(0));
		
		// Zjisteni jmena suroviny
		List<String> suroviny = ReceptDAO.getIngredients(idReceptu);
		
		Assert.assertEquals("voda", suroviny.get(0));
		
		// Update receptu
		ReceptDAO.updateRecept(idReceptu, "Kulajda", "Uvarit, nakrajet", 1.0);
		recepty = ReceptDAO.getAllRecipes();
		
		Assert.assertEquals("Uvarit, nakrajet", recepty.get(recepty.size() - 1)[2].toString());
		
		// Delete receptu
		ReceptDAO.deleteRecept(idReceptu);
		recepty = ReceptDAO.getAllRecipes();
		nazev = recepty.get(recepty.size() - 1)[1].toString();
		
		Assert.assertNotEquals("Kulajda", nazev);
		
	}

}
