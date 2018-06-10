package com.github.petrpanek.VedeniKuchyne.logika;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *  Třída Potravina - trida implementujici potravinu v aplikaci
 *  Trida obsahuje atributy a metody pro praci s potravinami
 *
 *  Tato třída je součástí aplikace receptar.
 *
 * @author     Petr Panek
 * @version    1.0
 */
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
	
	@OneToMany(mappedBy = "potravina")
	private Set<ReceptPotravina> receptPotraviny = new HashSet<ReceptPotravina>();

	/**
	 * Konstruktor potraviny
	 */
	public Potravina() {
		
	}
	
	/**
	 * Konstruktor potraviny
	 *
	 * @param nazev Jmeno potraviny
	 * @param mnozstvi Mnozstvi potraviny
	 */
	public Potravina(String nazev, int mnozstvi) {
		this.nazev = nazev;
		this.mnozstvi = mnozstvi;
	}

	/**
	 * Getter pro nazev potraviny
	 *
	 * @return String Nazev potraviny
	 */
	public String getNazev() {
		return nazev;
	}
	
	/**
	 * Setter pro nazev potraviny
	 *
	 * @param nazev Nazev potraviny
	 */
	public void setNazev(String nazev) {
		this.nazev = nazev;
	}
	
	/**
	 * Getter pro mnozstvi potraviny
	 *
	 * @return int Mnozstvi potraviny
	 */
	public int getMnozstvi() {
		return mnozstvi;
	}
	
	/**
	 * Setter pro mnozstvi potraviny
	 *
	 * @param mnozstvi Mnozstvi potraviny
	 */
	public void setMnozstvi(int mnozstvi) {
		this.mnozstvi = mnozstvi;
	}
	
	/**
	 * Getter vazeb, ktere ma dana potravina na recepty
	 *
	 * @return Set<ReceptPotravina> Set obsahujici jednotlive vazby
	 */
	public Set<ReceptPotravina> getReceptPotraviny() {
		return receptPotraviny;
	}

	/**
	 * Setter vazeb, ktere ma dana potravina na recepty
	 *
	 * @param potravina Set obsahujici jednotlive vazby
	 */
	public void setReceptPotraviny(Set<ReceptPotravina> potraviny) {
		this.receptPotraviny = potraviny;
	}
	
	/**
	 * Metoda pro pridani vazby potraviny na recept
	 *
	 * @param receptPotravina Nova vazba
	 */
	public void addReceptPotraviny(ReceptPotravina receptPotravina) {
		this.receptPotraviny.add(receptPotravina);
	}
	
	/**
	 * Metoda pro vypis potraviny
	 *
	 * @return String String obsahujici atributy potraviny
	 */
	@Override
	public String toString() {
		return "Potravina [idPotraviny=" + idPotraviny + ", nazev=" + nazev + ", mnozstvi=" + mnozstvi + "]";
	}
	
}
