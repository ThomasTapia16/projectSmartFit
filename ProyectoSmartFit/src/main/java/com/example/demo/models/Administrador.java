package com.example.demo.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity

public class Administrador extends DTI{

	

	public Administrador() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setPermiso()
	{
		this.permiso = 'A';
	}
	public char getPermiso()
	{
		return this.permiso;
	}

}
