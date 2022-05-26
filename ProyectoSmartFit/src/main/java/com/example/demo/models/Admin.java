package com.example.demo.models;

import javax.persistence.Entity;

@Entity
public class Admin extends User{

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
		this.rol = "ROLE_ADMIN";
	}
	
	
}
