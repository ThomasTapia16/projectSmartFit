package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Sala {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private int numeroSala;
//	@ManyToOne
//	private Sede sede;
	@ManyToOne
	private Piso piso;
	@Transient
	private String estado;
	public Sala() {
		super();
		this.estado = "desocupado";
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getNumeroSala() {
		return numeroSala;
	}
	public void setNumeroSala(int numeroSala) {
		this.numeroSala = numeroSala;
	}
	public Piso getPiso() {
		return piso;
	}
	public void setPiso(Piso piso) {
		this.piso = piso;
	}
	
//	public Sede getSede() {
//		return sede;
//	}
//	public void setSede(Sede sede) {
//		this.sede = sede;
//	}
	
	
	
}
