package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Colaborador extends User{
	
	@ManyToOne
	private Sede sede;
	private boolean es_encargado;
	
	public boolean isEs_encargado() {
		return es_encargado;
	}

	public void setEs_encargado(boolean es_encargado) {
		this.es_encargado = es_encargado;
	}

	public Colaborador() {
		super();
		// TODO Auto-generated constructor stub
		this.rol = "ROLE_COL";
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}
	
}
