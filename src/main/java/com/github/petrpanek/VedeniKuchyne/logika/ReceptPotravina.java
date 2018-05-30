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

	public Recept getRecept() {
		return recept;
	}

	public void setRecept(Recept recept) {
		this.recept = recept;
	}

	public Potravina getPotravina() {
		return potravina;
	}

	public void setPotravina(Potravina potravina) {
		this.potravina = potravina;
	}

	public int getMnozstvi() {
		return mnozstvi;
	}

	public void setMnozstvi(int mnozstvi) {
		this.mnozstvi = mnozstvi;
	}
	
	
}
