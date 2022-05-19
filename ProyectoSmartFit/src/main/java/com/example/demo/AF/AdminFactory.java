package com.example.demo.AF;

import com.example.demo.models.Administrador;
import com.example.demo.models.SuperAdministrador;

public class AdminFactory implements AbstractFactory{

	@Override
	public Administrador crearAdministrador() {
		// TODO Auto-generated method stub
		return new Administrador();
	}

	@Override
	public SuperAdministrador crearSA() {
		// TODO Auto-generated method stub
		return new SuperAdministrador();
	}

}
