package com.github.petrpanek.VedeniKuchyne.logika;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

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
	
	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(
			name = "recept_obsahuje_potraviny",
			joinColumns = { @JoinColumn(name = "recept_id") },
			inverseJoinColumns = { @JoinColumn(name = "potravina_id") }
	)
	Set<Potravina> potraviny = new HashSet<>();
	public Recept() {
		
	}
	
	public Recept(String nazev, String postup, double obtiznost) {
		this.nazev = nazev;
		this.postup = postup;
		this.obtiznost = obtiznost;
	}

	public int getIdReceptu() {
		return idReceptu;
	}
	public void setIdReceptu(int idReceptu) {
		this.idReceptu = idReceptu;
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
	
}
