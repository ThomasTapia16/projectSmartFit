package com.example.demo.models;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;

import com.example.demo.repositories.SedeRepository;

@Entity
@Component
public class Sede {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String nombre;
	private String region;
	private String ciudad;
	@OneToMany(targetEntity=Sala.class)
	private List<Sala> salas;
	@OneToMany(targetEntity=Piso.class)
	private List<Piso> pisos;
	@OneToOne
	private Colaborador encargado;
	@OneToMany(targetEntity=Colaborador.class)
	private List<Colaborador> colaboradores;
	private int npisos;
	public Sede() {
		super();
	}
	
	public Sede(long id, String nombre, String region, String ciudad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.region = region;
		this.ciudad = ciudad;
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

	public List<Colaborador> getColaboradores() {
		return colaboradores;
	}

	public void setColaboradores(Colaborador colaboradores) {
		this.colaboradores.add(colaboradores);
	}

	public Colaborador getEncargado() {
		return encargado;
	}

	public void setEncargado(Colaborador encargado) {
		this.encargado = encargado;
	}

	public int getNpisos() {
		return npisos;
	}

	public void setNpisos(int npisos) {
		this.npisos = npisos;
	}
	
	
}
