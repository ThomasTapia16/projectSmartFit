package com.example.demo.models;

import java.util.List;

import javax.persistence.Column;
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
	private long id;
	
	@ManyToOne
	private Sede sede;
	
	private int nuperoPiso;
	@Column(name="sala")
	@OneToMany
	private List<Sala> salasDelPiso;

	public Piso() {
		super();
	}
	public Piso(Sede sede, int npiso)
	{	
		this.sede =sede;
		this.nuperoPiso = npiso;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getNuperoPiso() {
		return nuperoPiso;
	}
	public void setNuperoPiso(int nuperoPiso) {
		this.nuperoPiso = nuperoPiso;
	}
	public List<Sala> getSalasDelPiso() {
		return salasDelPiso;
	}
	public void setSalasDelPiso(List<Sala> salasDelPiso) {
		this.salasDelPiso = salasDelPiso;
	}
	public Sede getSede() {
		return sede;
	}
	public void setSede(Sede sede) {
		this.sede = sede;
	}
	
	
}
