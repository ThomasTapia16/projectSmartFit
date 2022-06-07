package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.models.Admin;
import com.example.demo.models.Colaborador;
import com.example.demo.models.SuperAdmin;
import com.example.demo.repositorio.AdminRepository;
import com.example.demo.repositorio.SuperAdminRepository;

@Controller
public class SuperAdminController {
	
	
	@Autowired
	AdminRepository admR;
	@Autowired
	SuperAdminRepository saR;
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
	
	//--------------------------------postmapping---------------------
	@PostMapping("/agregar_administrador")
	public String saveAdmin(@ModelAttribute("admin")Admin admin)
	{	
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		
		admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
		admR.save(admin);
		
		return "redirect:/agregar_administrador";
	}
	
	@PostMapping("/agregar_super_administrador")
	public String saveSA(@ModelAttribute("super") SuperAdmin superAdmin)
	{
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		
		superAdmin.setPassword(bCryptPasswordEncoder.encode(superAdmin.getPassword()));
		saR.save(superAdmin);
		
		return "redirect:/agregar_super_administrador";
	}
}
