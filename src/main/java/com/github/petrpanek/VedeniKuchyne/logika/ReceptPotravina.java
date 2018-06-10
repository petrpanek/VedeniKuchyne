package com.github.petrpanek.VedeniKuchyne.logika;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *  Třída ReceptPotravina - trida implementujici vztah Potraviny a Receptu v aplikaci
 *  Trida obsahuje atributy a metody pro praci s touto vazbou
 *
 *  Tato třída je součástí aplikace receptar.
 *
 * @author     Petr Panek
 * @version    1.0
 */
@Entity
@Table(name = "recept_obsahuje_potraviny")
public class ReceptPotravina {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "recept_id")
	private Recept recept;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "potravina_id")
	private Potravina potravina;
	
	@Column(name = "mnozstvi")
	private int mnozstvi;

	/**
	 * Getter pro recept
	 *
	 * @return Recept recept
	 */
	public Recept getRecept() {
		return recept;
	}

	/**
	 * Setter pro recept
	 *
	 * @param recept Recept
	 */
	public void setRecept(Recept recept) {
		this.recept = recept;
	}

	/**
	 * Getter pro potravinu
	 *
	 * @return Potravina potravina
	 */
	public Potravina getPotravina() {
		return potravina;
	}

	/**
	 * Setter pro potravinu
	 *
	 * @param potravina Potravina
	 */
	public void setPotravina(Potravina potravina) {
		this.potravina = potravina;
	}

	/**
	 * Getter pro mnozstvi potraviny v receptu
	 *
	 * @return int mnozstvi
	 */
	public int getMnozstvi() {
		return mnozstvi;
	}

	/**
	 * Setter pro mnozstvi potraviny v receptu
	 *
	 * @param mnozstvi Mnozstvi potraviny v receptu
	 */
	public void setMnozstvi(int mnozstvi) {
		this.mnozstvi = mnozstvi;
	}
	
}
