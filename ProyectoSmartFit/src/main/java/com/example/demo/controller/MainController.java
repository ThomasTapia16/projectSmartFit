	package com.example.demo.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.example.demo.models.Colaborador;
import com.example.demo.models.EntrenamientoMasivo;
import com.example.demo.models.Piso;
import com.example.demo.models.Sala;
import com.example.demo.models.Sede;
import com.example.demo.repositorio.ColaboradorRepository;
import com.example.demo.repositorio.EMRepository;
import com.example.demo.repositorio.PisoRepository;
import com.example.demo.repositorio.SalaRepository;
import com.example.demo.repositorio.SedeRepository;
import com.example.demo.repositorio.claseRepository;
import com.example.demo.service.ChangePwd;
import com.example.demo.service.PasswordEncrypter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Controller("/")
public class MainController extends BaseController{
	
	@Autowired
	PisoRepository pisoR;
	@Autowired
	SalaRepository salaR;
	@Autowired
	claseRepository claseR;
	@Autowired
	EMRepository emR;
	@Autowired
	ColaboradorRepository colR;
	@Autowired
	ChangePwd changeP;
	@Autowired
	PasswordEncrypter encripter;
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
		
		if(user != null) {
		
		
		Sede sede = user.getSede();
		//LinkedList<Sala> salas = new LinkedList<Sala>();
		
		
		List<EntrenamientoMasivo> salas = emR.findAllById(pisoR.findIdSala(sede.getId()));
		
		
		model.addAttribute("sede",sede);
		model.addAttribute("salas",salas);
		
		}else if(user == null)
		{	
			SuperAdminDetails sa = getLoggedSA();
			
		}
		
		return "home";
	}
	
	@GetMapping("/salaSeleccionada/{id}")
	public String sala(@PathVariable("id") Long idSala, Model model, RedirectAttributes attribute)
	{	String profesor = null;
		EntrenamientoMasivo sala = emR.getById(idSala);
		Clase clase = new Clase();
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH");
	     
		model.addAttribute("profe",profesor);
		model.addAttribute("hora",dtf.format(LocalDateTime.now()));
		model.addAttribute("sala",sala);
		model.addAttribute("piso",sala.getPiso());
		System.out.println(dtf.format(LocalDateTime.now()));
		model.addAttribute("clase",clase);
		return "seleccionSala";
	}
	@PostMapping("/salaSeleccionada/{id}")
	public String ocuparSala(@ModelAttribute("sala")EntrenamientoMasivo sala
			,@ModelAttribute("clase")Clase clase,@ModelAttribute("profe")String profesor,@ModelAttribute("hora")String hora)
	{	
		
		System.out.println(sala.getPiso());
		
		sala.setOcupado();
		sala.setNumeroSala(sala.getNumeroSala());
		clase.llenarLista(profesor);
		
		salaR.save(sala);
		clase.getProfesores();
		return "redirect:/home";
	}
	@GetMapping("/loginAdmin")
	public String getAdminLog()
	{
		return "loginAdmin";
	}
	@GetMapping("/cambio_contraseña")
	public String changePwd(Model model)
	{	
		MyUserDetails user = getLoggedUser();
		Colaborador col = colR.getById(user.getid());
		changeP.setCol(col);
		model.addAttribute("changeP", changeP);
		
	
		return "cambiopwd";
	}
	@PostMapping("/cambio_contraseña")
	public String changePwd(@ModelAttribute("changeP")ChangePwd cP)
	{	MyUserDetails user = getLoggedUser();
		Colaborador col = colR.getById(user.getid());
		
		if(cP.ooh()==true)
		{
			
		
			col.setPassword(encripter.encirptador(cP.pwd));
			colR.save(col);
		}
		
		
	
		return "redirect:/home";
	}
	

}
