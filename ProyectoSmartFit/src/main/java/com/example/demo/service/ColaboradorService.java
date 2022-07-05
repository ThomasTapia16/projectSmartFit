package com.example.demo.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Colaborador;
import com.example.demo.repositorio.ColaboradorRepository;

@Service
public class ColaboradorService {
	
	@Autowired
	ColaboradorRepository colR;
	
	public List<Colaborador> buscar(long n)
	{
		return colR.getUserBySede_Id(n);
	}

	
	
}
