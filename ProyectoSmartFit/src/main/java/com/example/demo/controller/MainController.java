	package com.example.demo.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.MyUserDetails;
import com.example.demo.models.Piso;
import com.example.demo.models.Sala;
import com.example.demo.models.Sede;
import com.example.demo.repositorio.PisoRepository;
import com.example.demo.repositorio.SalaRepository;
import com.example.demo.repositorio.SedeRepository;

@Controller
public class MainController extends BaseController{
	
	@Autowired
	PisoRepository pisoR;
	@Autowired
	SalaRepository salaR;
	@GetMapping("/")
	public String login()
	{
		return "login";
	}
	@PostMapping("/")
	public String entrar()
	{
		return "redirect:/home";
	}
	
	@GetMapping("/home")
	public String home(Model model){
		
		
		
		
		MyUserDetails user = getLoggedUser();
		Sede sede = user.getSede();
		LinkedList<Sala> salas = new LinkedList<Sala>();
		List<Long> idsalas = pisoR.findIdSala(sede.getId());
		
		
		for(int i = 0 ; i < idsalas.size();i++) {
			Sala sala = salaR.getById(idsalas.get(i));
			salas.add(sala);
		}
		
		for(int i = 0 ; i < salas.size();i++)
			System.out.println(salas.get(i).getEstado());
		model.addAttribute("sede",sede);
		model.addAttribute("salas",salas);
		return "home";
	}
	
	@GetMapping("/salaSeleccionada")
	public String sala()
	{
		return "salaSeleccionada";
	}
	
}
