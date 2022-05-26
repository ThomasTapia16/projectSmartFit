package com.example.demo.models;

import javax.persistence.Entity;

@Entity
public class SuperAdmin extends User{
	
	public SuperAdmin() {
		super();
		// TODO Auto-generated constructor stub
		this.rol = "ROLE_SUPERADMIN";
	}
}
