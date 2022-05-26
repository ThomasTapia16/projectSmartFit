package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repositorio.SedeRepository;

@Service
public class SedeService {
	
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