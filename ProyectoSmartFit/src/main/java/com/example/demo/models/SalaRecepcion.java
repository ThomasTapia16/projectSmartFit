package com.example.demo.models;

import java.util.LinkedList;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("R")
public class SalaRecepcion extends Sala{
	private LinkedList<Colaborador> encargadosRecepcion = new LinkedList<>();
	

	
}
 