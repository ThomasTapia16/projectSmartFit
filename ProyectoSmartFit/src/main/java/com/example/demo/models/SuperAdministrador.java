package com.example.demo.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity

public class SuperAdministrador extends DTI{

	

	public SuperAdministrador() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void setPermiso() {
		
		this.permiso = 'S';
	}
	public char getPermiso()
	{
		return this.permiso;
	}

}
