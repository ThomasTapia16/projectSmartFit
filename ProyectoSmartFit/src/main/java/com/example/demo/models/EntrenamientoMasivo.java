package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class EntrenamientoMasivo extends Sala{
	
	@Transient
	private Clase clase;
	public EntrenamientoMasivo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Clase getClase() {
		return clase;
	}
	public void setClase(Clase clase) {
		this.clase = clase;
	}
	
	
}
