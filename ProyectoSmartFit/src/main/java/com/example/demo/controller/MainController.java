	package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.MyUserDetails;
import com.example.demo.models.Sala;

@Controller
public class MainController extends BaseController{
	
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
		
		
		
		
		MyUserDetails user = getLoggedUser();
		
//		user.getSede().getPisosSede().get		
		
		return "home";
	}
	
}
