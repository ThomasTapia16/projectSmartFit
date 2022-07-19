package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Clase;
import com.example.demo.models.Sala;
import com.example.demo.repositorio.SalaRepository;
import com.example.demo.repositorio.claseRepository;

@Service
public class ClaseService {
	
	@Autowired
	claseRepository cR;
	
	public Clase buscar(long idSala) {
		Clase clase = null;
		System.out.println(idSala);
		List<Clase> clases = cR.findClaseBySalaId(idSala);
		clase = getClaseActiva(clases);
		if(clase != null)
		{	System.out.println("ALO");
			return clase;
		}else {return null;}
	}
	
	public Clase getClaseActiva(List<Clase> clases) {
		
		for(int i = 0 ;i < clases.size() ; i++)
		{
			if(clases.get(i).isActivo() == true)
				return clases.get(i);
		}return null;
	}
	
}
