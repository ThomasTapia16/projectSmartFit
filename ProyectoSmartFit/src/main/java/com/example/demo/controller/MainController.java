	package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
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
	public String home(){
		
		return "home";
	}
	
}
