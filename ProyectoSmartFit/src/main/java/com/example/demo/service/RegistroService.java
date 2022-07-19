package com.example.demo.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.MyUserDetails;
import com.example.demo.models.Clase;
import com.example.demo.models.Registro;
import com.example.demo.models.Sala;
import com.example.demo.repositorio.RegistroRepository;

@Service
public class RegistroService {

	@Autowired
	RegistroRepository rr;
	public void regitrar(MyUserDetails user, Clase clase, Sala sala)
	{	
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss");
		Registro registro = new Registro();
		registro.setRut(user.getUsername());
		registro.setNombre(user.getNombre());
		registro.setApellido(user.getApellido());
		registro.setAccion("Ocupación de la sala n°"+sala.getNumeroSala()+ " con clase de "+clase.getNombreClase());
		registro.setFecha(LocalDate.now());
		registro.setHora((LocalDateTime.now()).format(myFormatObj));
		rr.save(registro);
	}
}
