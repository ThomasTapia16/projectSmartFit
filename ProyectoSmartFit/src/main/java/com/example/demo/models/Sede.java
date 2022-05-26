package com.example.demo.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Sede {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String nombre;
	private String region;
	private String ciudad;
	private int npisos;
	@Column(name="colaborador")
	@OneToMany(targetEntity=Colaborador.class)
	private List<Colaborador> colaboradoresSede;
	@Column(name="sala")
	@OneToMany(targetEntity=Sala.class)
	private List<Sala> salasSede;
	@Column(name="piso")
	@OneToMany(targetEntity=Piso.class)
	private List<Piso> pisosSede;
	
	
	public Sede() {
		super();
	}
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public List<Colaborador> getColaboradoresSede() {
		return colaboradoresSede;
	}
	public void setColaboradoresSede(List<Colaborador> colaboradoresSede) {
		this.colaboradoresSede = colaboradoresSede;
	}
	public List<Sala> getSalasSede() {
		return salasSede;
	}
	public void setSalasSede(List<Sala> salasSede) {
		this.salasSede = salasSede;
	}
	public int getNpisos() {
		return npisos;
	}
	public void setNpisos(int npisos) {
		this.npisos = npisos;
	}
	public List<Piso> getPisosSede() {
		return pisosSede;
	}
	public void setPisosSede(List<Piso> pisosSede) {
		this.pisosSede = pisosSede;
	}
	
	
}
