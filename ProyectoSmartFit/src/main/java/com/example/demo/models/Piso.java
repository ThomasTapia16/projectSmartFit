package com.example.demo.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="pisos")
public class Piso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	private Sede sede;
	@OneToMany(targetEntity=Colaborador.class)
	private List<Colaborador> encargado;
	@OneToMany(targetEntity=Sala.class)
	private List<Sala> salas;
	private int npiso;
	
	
	public Piso(Sede s,int npiso) {
		super();
		this.sede = s;
		this.npiso = npiso;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Sede getSede() {
		return sede;
	}


	public void setSede(Sede sede) {
		this.sede = sede;
	}


	public List<Colaborador> getEncargado() {
		return encargado;
	}


	public void setEncargado(List<Colaborador> encargado) {
		this.encargado = encargado;
	}


	public List<Sala> getSalas() {
		return salas;
	}


	public void setSalas(List<Sala> salas) {
		this.salas = salas;
	}
	
	
}
