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
	
	@ManyToMany(mappedBy = "potravina")
	private Set<ReceptPotravina> receptPotraviny = new HashSet<ReceptPotravina>();

	public Potravina() {
		
	}
	
	public Potravina(String nazev, int mnozstvi) {
		this.nazev = nazev;
		this.mnozstvi = mnozstvi;
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
	
	public Set<ReceptPotravina> getReceptPotraviny() {
		return receptPotraviny;
	}

	public void setReceptPotraviny(Set<ReceptPotravina> potraviny) {
		this.receptPotraviny = potraviny;
	}
	
	public void addReceptPotraviny(ReceptPotravina receptPotravina) {
		this.receptPotraviny.add(receptPotravina);
	}
	
	@Override
	public String toString() {
		return "Potravina [idPotraviny=" + idPotraviny + ", nazev=" + nazev + ", mnozstvi=" + mnozstvi + "]";
	}
	
}
