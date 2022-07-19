package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.example.demo.MyUserDetails;

@Entity
public class Solicitud {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String rut;
	private String nombre;
	private String apellido;
	private String correo;
	private String rol = "ROLE_COL";
	@ManyToOne
	private Sede sede;
	private boolean estado = true;
	private boolean es_encargado;
	private String correoEncargadoSede;
	
	public Solicitud(){}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

	public boolean isEs_encargado() {
		return es_encargado;
	}

	public void setEs_encargado(boolean es_encargado) {
		this.es_encargado = es_encargado;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public void setSede(MyUserDetails encargado)
	{
		this.sede = encargado.getSede();
	}
	public void solicitudProcesada()
	{
		this.estado = false;
	}

	public String getCorreoEncargadoSede() {
		return correoEncargadoSede;
	}

	public void setCorreoEncargadoSede(String correoEncargadoSede) {
		this.correoEncargadoSede = correoEncargadoSede;
	}
	
}
