package com.example.demo.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SM")
public class SalaMusculacion extends Sala{

	public SalaMusculacion() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
