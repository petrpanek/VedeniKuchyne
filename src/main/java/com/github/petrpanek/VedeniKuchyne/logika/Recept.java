package com.github.petrpanek.VedeniKuchyne.logika;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "recepty")
public class Recept {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recept_id")
	private int idReceptu;

	@Column(name = "nazev")
	private String nazev;
	
	@Column(name = "postup")
	private String postup;
	
	@Column(name = "obtiznost")
	private double obtiznost;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "recept")
	@Cascade({CascadeType.DELETE})
	private Set<ReceptPotravina> receptPotraviny = new HashSet<ReceptPotravina>();
	
	public Recept() {
		
	}
	
	public Recept(String nazev, String postup, double obtiznost) {
		this.nazev = nazev;
		this.postup = postup;
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
	
	public double getObtiznost() {
		return obtiznost;
	}
	
	public void setObtiznost(double obtiznost) {
		this.obtiznost = obtiznost;
	}
	
	public Set<ReceptPotravina> getReceptPotraviny() {
		return receptPotraviny;
	}
	
	public void setReceptPotraviny(Set<ReceptPotravina> receptPotraviny) {
		this.receptPotraviny = receptPotraviny;
	}
	
	public void addReceptPotravina(ReceptPotravina receptPotravina) {
		this.receptPotraviny.add(receptPotravina);
	}
	
	@Override
	public String toString() {
		return "Recept [idReceptu=" + idReceptu + ", nazev=" + nazev + ", postup=" + postup + ", obtiznost=" + obtiznost
				+ ", receptPotraviny=" + receptPotraviny + "]";
	}
	
}
