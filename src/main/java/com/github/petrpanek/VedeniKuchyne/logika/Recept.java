package com.github.petrpanek.VedeniKuchyne.logika;

public class Recept {
	private String nazev;
	private String postup;
	private double dobaPripravy;
	private int idReceptu;
	private double obtiznost;


public Recept(String nazev, String postup, double dobaPripravy, int idReceptu, double obtiznost){
	this.nazev = nazev;
	this.postup = postup;
	this.dobaPripravy = dobaPripravy;
	this.idReceptu = idReceptu;
	this.obtiznost = obtiznost;
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

}