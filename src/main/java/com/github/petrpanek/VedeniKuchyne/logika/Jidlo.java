package com.github.petrpanek.VedeniKuchyne.logika;

import java.util.Date;

public class Jidlo {
	private String nazev;
	private int pocetPorci;
	private int idJidla;
	private Date casVareni;


public Jidlo(String nazev, int pocetPorci, int idJidla, Date casVareni){
	this.nazev = nazev;
	this.pocetPorci = pocetPorci;
	this.idJidla = idJidla;
	this.casVareni = casVareni;
}


public int getIdJidla() {
	return idJidla;
}


public void setIdJidla(int idJidla) {
	this.idJidla = idJidla;
}


public String getNazev() {
	return nazev;
}


public void setNazev(String nazev) {
	this.nazev = nazev;
}


public int getPocetPorci() {
	return pocetPorci;
}


public void setPocetPorci(int pocetPorci) {
	this.pocetPorci = pocetPorci;
}


public Date getCasVareni() {
	return casVareni;
}


public void setCasVareni(Date casVareni) {
	this.casVareni = casVareni;
}

}

