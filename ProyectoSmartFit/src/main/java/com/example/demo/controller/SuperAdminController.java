package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.models.Admin;
import com.example.demo.models.SuperAdmin;

@Controller
public class SuperAdminController {
	
	@GetMapping("/agregar_administrador")
	 public String newAdmin(Model model) 
	{
        Admin admin = new Admin();
	    model.addAttribute("admin", admin);
	    return "crearAdministrador";
	 }
	
	@GetMapping("/agregar_super_administrador")
	public String newSAdmin(Model model)
	{
		SuperAdmin sa = new SuperAdmin();
		model.addAttribute("super", sa);
		return "crearSuperAdministrador";
	}
}
