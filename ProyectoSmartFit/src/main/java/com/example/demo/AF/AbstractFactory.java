package com.example.demo.AF;

import com.example.demo.models.Administrador;
import com.example.demo.models.SuperAdministrador;

public interface AbstractFactory {
	
	public Administrador crearAdministrador();
	public SuperAdministrador crearSA();
}
