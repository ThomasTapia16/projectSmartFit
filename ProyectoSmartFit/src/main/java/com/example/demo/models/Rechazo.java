package com.example.demo.models;

public class Rechazo {
	
	private long idSolicitud;
	private String motivo;
	public Rechazo() {};
	public long getIdSolicitud() {
		return idSolicitud;
	}
	public void setIdSolicitud(long idSolicitud) {
		this.idSolicitud = idSolicitud;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
}
