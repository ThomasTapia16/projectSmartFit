	package com.example.demo.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.AdminDetails;
import com.example.demo.MyUserDetails;
import com.example.demo.SuperAdminDetails;
import com.example.demo.models.Clase;
import com.example.demo.models.EntrenamientoMasivo;
import com.example.demo.models.Piso;
import com.example.demo.models.Sala;
import com.example.demo.models.Sede;
import com.example.demo.repositorio.EMRepository;
import com.example.demo.repositorio.PisoRepository;
import com.example.demo.repositorio.SalaRepository;
import com.example.demo.repositorio.SedeRepository;
import com.example.demo.repositorio.claseRepository;

@Controller
public class MainController extends BaseController{
	
	@Autowired
	PisoRepository pisoR;
	@Autowired
	SalaRepository salaR;
	@Autowired
	claseRepository claseR;
	@Autowired
	EMRepository emR;
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
		
		
		
		
		boolean a = false;
		SuperAdminDetails sa = getLoggedSA();
		
		if(sa == null) {
		MyUserDetails user = getLoggedUser();
		 a = true;
		Sede sede = user.getSede();
		//LinkedList<Sala> salas = new LinkedList<Sala>();
		
		
		List<EntrenamientoMasivo> salas = emR.findAllById(pisoR.findIdSala(sede.getId()));
		
		
		model.addAttribute("sede",sede);
		model.addAttribute("salas",salas);
		model.addAttribute("b",a);
		}
		return "home";
	}
	
	@GetMapping("/salaSeleccionada/{id}")
	public String sala(@PathVariable("id") Long idSala, Model model, RedirectAttributes attribute)
	{	
		EntrenamientoMasivo sala = emR.getById(idSala);
		Clase clase = new Clase();
		model.addAttribute("sala",sala);
		model.addAttribute("piso",sala.getPiso());
		
		model.addAttribute("clase",clase);
		return "/salaSeleccionada";
	}
	@PostMapping("/salaSeleccionada/{id}")
	public String ocuparSala(@ModelAttribute("sala")EntrenamientoMasivo sala
			,@ModelAttribute("clase")Clase clase)
	{	
		
		System.out.println(sala.getPiso());
		sala.setOcupado();
		sala.setNumeroSala(sala.getNumeroSala());
		
		
		salaR.save(sala);
		
		return "redirect:/home";
	}

}
