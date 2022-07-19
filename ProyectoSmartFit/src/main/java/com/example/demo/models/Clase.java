package com.example.demo.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
	private boolean activo;
	@ElementCollection
	private List<String>profesores = new ArrayList<>();
	
	
	@ManyToOne
	private EntrenamientoMasivo sala;
	public Clase() {
		super();
		this.setActivo(true);
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

	public List<String> getProfesores() {
		return profesores;
	}
	public void setProfesores(List<String> profesores) {
		this.profesores = profesores;
	}
	public EntrenamientoMasivo getSala() {
		return sala;
	}
	public void setSala(EntrenamientoMasivo sala) {
		this.sala = sala;
	}
	
	public void llenarLista(String nombre)
	{
		this.profesores.add(nombre);
	}
	
	public EntrenamientoMasivo getSalaActual() {
		return sala;
	}
	public void setSalaActual(EntrenamientoMasivo claseActual) {
		this.sala = claseActual;
	}
	
	public void desactivar()
	{
		this.setActivo(false);
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public void leerCol()
	{
		for(int i = 0 ; i<this.profesores.size();i++)
			System.out.println(profesores.get(i));
	}
	
}
