package com.github.petrpanek.VedeniKuchyne.logika;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "potraviny")
public class Potravina {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "potravina_id")
	private int idPotraviny;
	
	@Column(name = "nazev")
	private String nazev;
	
	@Column(name = "mnozstvi")
	private int mnozstvi;
	
	@ManyToMany(mappedBy = "recepty")
	private Set<Recept> recepty = new HashSet<>();
	
	public Potravina () {
		
	}
	
	public Potravina(String nazev, int mnozstvi) {
		this.nazev = nazev;
		this.mnozstvi = mnozstvi;
	}

	@Override
	public String toString() {
		return "Potravina [idPotraviny=" + idPotraviny + ", nazev=" + nazev + ", mnozstvi=" + mnozstvi + "]";
	}

	public int getIdPotraviny() {
		return idPotraviny;
	}

	public void setIdPotraviny(int idPotraviny) {
		this.idPotraviny = idPotraviny;
	}

	public String getNazev() {
		return nazev;
	}
	
	public void setNazev(String nazev) {
		this.nazev = nazev;
	}
	
	public int getMnozstvi() {
		return mnozstvi;
	}
	
	public void setMnozstvi(int mnozstvi) {
		this.mnozstvi = mnozstvi;
	}
	

	
}
