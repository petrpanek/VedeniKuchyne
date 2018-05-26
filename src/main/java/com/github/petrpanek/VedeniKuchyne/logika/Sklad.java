package com.github.petrpanek.VedeniKuchyne.logika;

import java.util.HashMap;
import java.util.Map;


public class Sklad {
   
	private Map<String, Potravina> sklad; 
	
    public Sklad () {
    	sklad = new HashMap<String, Potravina>();
    }
    /**
     * Vloží potravinu do skladu
     *
     *@param  potravina  instance potraviny, která se má vložit
     */
   public void vlozPotravinu (Potravina potravina) {
     sklad.put(potravina.getNazev(),potravina);
    }
     /**
     * Vrací  mapu portravin, které jsou ve skladu

     *@return            mapa potravin
     */
    public Map<String, Potravina> getSklad() {
		return sklad;
	}
     /**
     * Hledá věc daného jména a pokud je ve skladu, tak ji vrátí a vymaže ze seznamu

     *@param  název   Jméno potraviny
     *@return            potravina nebo
     *                   hodnota null, pokud tam potravina daného názvu není 
     */
    public Potravina odstranPotravinu (String nazevPotraviny) {
        Potravina potravina = null;
        if (sklad.containsKey(nazevPotraviny)) {
        	potravina = sklad.get(nazevPotraviny);
            sklad.remove(nazevPotraviny);
        }   
        return potravina;
    }
    
    public Potravina vratPotravinu (String nazevPotraviny) {
        Potravina potravina = null;
        if (sklad.containsKey(nazevPotraviny)) {
        	potravina = sklad.get(nazevPotraviny);
        }   
        return potravina;
    }
}
