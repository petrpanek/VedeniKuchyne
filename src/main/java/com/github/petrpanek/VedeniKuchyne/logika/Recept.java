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

/**
 *  Třída Recept - trida implementujici recepty v aplikaci
 *  Trida obsahuje atributy a metody pro praci s recepty
 *
 *  Tato třída je součástí aplikace receptar.
 *
 * @author     Petr Panek
 * @version    1.0
 */
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
	
	/**
	 * Konstruktor receptu
	 */
	public Recept() {
		
	}
	
	/**
	 * Konstruktor receptu
	 *
	 * @param nazev Jmeno receptu
	 * @param postup Postup receptu
	 * @param obtiznost Obtiznost receptu
	 */
	public Recept(String nazev, String postup, double obtiznost) {
		this.nazev = nazev;
		this.postup = postup;
		this.obtiznost = obtiznost;
	}

	/**
	 * Getter pro nazev receptu
	 *
	 * @return String Nazev receptu
	 */
	public String getNazev() {
		return nazev;
	}
	
	/**
	 * Setter pro nazev receptu
	 *
	 * @param nazev Nazev receptu
	 */
	public void setNazev(String nazev) {
		this.nazev = nazev;
	}
	
	/**
	 * Getter pro postup receptu
	 *
	 * @return String Postup receptu
	 */
	public String getPostup() {
		return postup;
	}
	
	/**
	 * Setter pro postup receptu
	 *
	 * @param postup Postup receptu
	 */
	public void setPostup(String postup) {
		this.postup = postup;
	}
	
	/**
	 * Getter pro obtiznost receptu
	 *
	 * @return String Obtiznost receptu
	 */
	public double getObtiznost() {
		return obtiznost;
	}
	
	/**
	 * Setter pro obtiznost receptu
	 *
	 * @param obtizsnost Obtiznost receptu
	 */
	public void setObtiznost(double obtiznost) {
		this.obtiznost = obtiznost;
	}
	
	/**
	 * Getter vazeb, ktere ma dany recept na potraviny
	 *
	 * @return Set<ReceptPotravina> Set obsahujici jednotlive vazby
	 */
	public Set<ReceptPotravina> getReceptPotraviny() {
		return receptPotraviny;
	}
	
	/**
	 * Setter vazeb, ktere ma dany receptu na potraviny
	 *
	 * @param receptPotraviny Set obsahujici jednotlive vazby
	 */
	public void setReceptPotraviny(Set<ReceptPotravina> receptPotraviny) {
		this.receptPotraviny = receptPotraviny;
	}
	
	/**
	 * Metoda pro pridani vazby receptu na potravinu
	 *
	 * @param receptPotravina Nova vazba
	 */
	public void addReceptPotravina(ReceptPotravina receptPotravina) {
		this.receptPotraviny.add(receptPotravina);
	}
	
	/**
	 * Metoda pro vypis receptu
	 *
	 * @return String String obsahujici atributy receptu
	 */
	@Override
	public String toString() {
		return "Recept [idReceptu=" + idReceptu + ", nazev=" + nazev + ", postup=" + postup + ", obtiznost=" + obtiznost
				+ ", receptPotraviny=" + receptPotraviny + "]";
	}
	
}
