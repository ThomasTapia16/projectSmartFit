package com.example.demo.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.Administrador;
import com.example.demo.models.Sede;
import com.example.demo.models.SuperAdministrador;
import com.example.demo.repositories.DtiRepository;
import com.example.demo.repositories.SedeRepository;

@Controller
public class MainController {
	
		
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
	
	
	@Autowired 
    DtiRepository dti;
	@Autowired
	SedeRepository sedeR;
	
	 @GetMapping("/crearAdministrador")
	 public String newAdmin(Model model) {
	        Administrador admin = new Administrador();
	        model.addAttribute("admin", admin);
	        return "crearAdministrador";
	 }
	 
	 
     
	   @PostMapping("/crearAdministrador")
	    public String saveAdmin(@ModelAttribute("admin") Administrador a) {
	        
		   a.setPermiso();
	        dti.save(a);
	        
	        return "redirect:/crearAdministrador";
	    }
	   
	   
	 @GetMapping("/crearSuperAdministrador")
	 public String newSAdmin(Model model){	
		 SuperAdministrador sa = new SuperAdministrador();
		 model.addAttribute("super", sa);
		 return "crearSuperAdministrador";
	 }
	 
	 @PostMapping("/crearSuperAdministrador")
	    public String saveSAdmin(@ModelAttribute("super") SuperAdministrador sa) {
	        
		 	sa.setPermiso();
	        dti.save(sa);
	        return "redirect:/crearSuperAdministrador";
	    }
	 
	 @GetMapping("/crearSala")
		public String crearSala()
		{	
		 	
			return "crearSala";
		}
	 @GetMapping("/crearSede")
		public String newSede(Model model)
		{	Sede sede = new Sede();
			model.addAttribute("sede", sede);
			return "crearSede";
		}
	 
	 @PostMapping("/crearSede")
	 public String saveSede(@ModelAttribute("sede") Sede sede){
		 
		 sedeR.save(sede);
		 return "redirect:/crearSede";
	 }
	 @GetMapping("/cerrarSesion")
		public String cerrarSesion()
		{	
			return "login";
		}
	 
	 
	 
}