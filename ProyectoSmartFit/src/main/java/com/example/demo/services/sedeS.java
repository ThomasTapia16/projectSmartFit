package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repositories.SedeRepository;

@Service
public class sedeS {
	
	@Autowired
	SedeRepository sedeR;
	
	public List<Object[]> findRegiones()
	{
		return sedeR.regiones();
	}
	
	public List<Object[]> findNombres(String n){
		return sedeR.byRegion(n);
	}
	
	public List<Object[]> buscarPiso(String s){
		return sedeR.pisoSede(s);
	}
	
	

}
