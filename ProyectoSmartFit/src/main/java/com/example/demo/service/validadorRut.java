package com.example.demo.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Colaborador;
import com.example.demo.repositorio.ColaboradorRepository;

@Service
public class validadorRut {

		@Autowired
		ColaboradorRepository colR;
		
		LinkedList <String> ruts;
		public boolean verificador(String rut)
		{
			List<Colaborador> cols = colR.findAll();
			for(int i=0;i<cols.size();i++)
			{
				if(rut.equals(cols.get(i).getRut()))
				{
					return true;
				}
			}
			return false;
			
		}
		
}
