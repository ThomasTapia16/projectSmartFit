package com.example.demo.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("EM")
public class SalaEntrenamientoMasivo extends Sala{
	
	@ManyToOne
	private Clase clase;

	public SalaEntrenamientoMasivo() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	
	
	
	
}
