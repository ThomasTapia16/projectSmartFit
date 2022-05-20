package com.example.demo.models;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



/**
 * @author thoma
 *
 */
@Entity
@Table(name=" Colaboradores")
public class Colaborador extends Persona{
	

	@ManyToOne
	private Sede sede;
	private boolean es_ecargado;//ENCARGADO
	public Colaborador() {
		super();
		// TODO Auto-generated constructor stub
	}
	public boolean isEs_ecargado() {
		return es_ecargado;
	}
	public void setEs_ecargado(boolean es_ecargado) {
		this.es_ecargado = es_ecargado;
	}
	public Sede getSede() {
		return sede;
	}
	public void setSede(Sede sede) {
		this.sede = sede;
	}
	
	
	
	
}
