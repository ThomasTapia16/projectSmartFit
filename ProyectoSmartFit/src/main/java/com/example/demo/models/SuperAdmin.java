package com.example.demo.models;

import javax.persistence.Entity;

@Entity
public class SuperAdmin extends User{
	
	private boolean es_superadmin;
	public SuperAdmin() {
		super();
		// TODO Auto-generated constructor stub
		this.rol = "ROLE_SUPERADMIN";
	}
	public boolean isEs_superadmin() {
		return es_superadmin;
	}
	public void setEs_superadmin(boolean es_superadmin) {
		this.es_superadmin = es_superadmin;
	}
	public void adminNormal()
	{
		this.rol = "ROLE_ADMIN";
	}
	
}
