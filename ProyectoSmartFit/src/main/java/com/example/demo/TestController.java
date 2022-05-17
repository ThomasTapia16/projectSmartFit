package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class TestController {
	
	
	@RequestMapping("/login")
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
	
	@GetMapping("/crearSala")
	public String crearSala()
	{
		return "crearSala";
	}
	
	@GetMapping("/crearSede")
	public String crearSede()
	{
		return "crearSede";
	}
	
	@GetMapping("/cerrarSesion")
	public String cerrarSesion()
	{
		return "login";
	}
}
