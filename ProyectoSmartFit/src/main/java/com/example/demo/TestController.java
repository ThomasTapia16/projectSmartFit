package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.DtiService;
import com.example.demo.models.Colaborador;
import com.example.demo.models.DTI;




@Controller
public class TestController {
	
		
	@RequestMapping("/")
	public String login() {
		return "login";
	}
	
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	
	@GetMapping("/crearColaborador")
	public String crearColaborador()
	{	
		
		return "crearColaborador";
	}
	@GetMapping("/crearAdministrador")
	public String crearAdministrador()
	{
		return "crearAdministrador";
	}
	
}
