package com.github.petrpanek.VedeniKuchyne.logika;

import java.util.HashMap;
import java.util.Map;

public class Recept {
	private String nazev;
	private String postup;
	private double dobaPripravy;
	private int idReceptu;
	private double obtiznost;
	private Map<String, Potravina> suroviny;


	public Recept(String nazev, String postup, double dobaPripravy, int idReceptu, double obtiznost){
		this.nazev = nazev;
		this.postup = postup;
		this.dobaPripravy = dobaPripravy;
		this.idReceptu = idReceptu;
		this.obtiznost = obtiznost;
		suroviny = new HashMap<String, Potravina>();
	}


	public double getObtiznost() {
		return obtiznost;
	}
	
	
	public void setObtiznost(double obtiznost) {
		this.obtiznost = obtiznost;
	}
	
	
	public String getNazev() {
		return nazev;
	}
	
	
	public void setNazev(String nazev) {
		this.nazev = nazev;
	}
	
	
	public String getPostup() {
		return postup;
	}
	
	
	public void setPostup(String postup) {
		this.postup = postup;
	}
	
	
	public double getDobaPripravy() {
		return dobaPripravy;
	}
	
	
	public void setDobaPripravy(double dobaPripravy) {
		this.dobaPripravy = dobaPripravy;
	}
	
	
	public int getIdReceptu() {
		return idReceptu;
	}
	
	
	public void setIdReceptu(int idReceptu) {
		this.idReceptu = idReceptu;
	}

	public void vlozSurovinu (Potravina surovina) {
	    suroviny.put(surovina.getNazev(),surovina);
	}

   public Map<String, Potravina> getSuroviny() {
		return suroviny;
	}
   
   public Potravina odstranSurovinu (String nazevSuroviny) {
       Potravina surovina = null;
       if (suroviny.containsKey(nazevSuroviny)) {
       	surovina = suroviny.get(nazevSuroviny);
           suroviny.remove(nazevSuroviny);
       }   
       return surovina;
   }
   
   public Potravina vratSurovinu (String nazevSuroviny) {
       Potravina surovina = null;
       if (suroviny.containsKey(nazevSuroviny)) {
    	   surovina = suroviny.get(nazevSuroviny);
       }   
       return surovina;
   }

}