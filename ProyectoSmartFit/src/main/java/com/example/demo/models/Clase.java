package com.example.demo.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Clase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String nombreClase;
	@Column(name="profesor")
	@OneToMany
	private List<Colaborador> profesores;
	
	@ManyToOne
	private EntrenamientoMasivo sala;
	public Clase() {
		super();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombreClase() {
		return nombreClase;
	}
	public void setNombreClase(String nombreClase) {
		this.nombreClase = nombreClase;
	}
	public List<Colaborador> getProfesores() {
		return profesores;
	}
	public void setProfesores(List<Colaborador> profesores) {
		this.profesores = profesores;
	}
	public EntrenamientoMasivo getSalaActual() {
		return sala;
	}
	public void setSalaActual(EntrenamientoMasivo claseActual) {
		this.sala = claseActual;
	}
	
	
}