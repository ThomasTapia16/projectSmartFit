package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.Admin;
import com.example.demo.models.Colaborador;
import com.example.demo.models.Registro;
import com.example.demo.models.Sede;
import com.example.demo.models.SuperAdmin;
import com.example.demo.repositorio.AdminRepository;
import com.example.demo.repositorio.RegistroRepository;
import com.example.demo.repositorio.SedeRepository;
import com.example.demo.repositorio.SuperAdminRepository;
import com.example.demo.service.BuscadorDeAuditoria;
import com.example.demo.service.RandomPasswors;
import com.example.demo.service.SendEmail;

@Controller
public class SuperAdminController {
	
	
	@Autowired
	AdminRepository admR;
	@Autowired
	SuperAdminRepository saR;
	@Autowired
	SendEmail mail;
	@Autowired
	RandomPasswors gp;
	@Autowired
	RegistroRepository rR;
	@Autowired
	SedeRepository sR;
	@Autowired
	BuscadorDeAuditoria bA;
	
	@GetMapping("/agregar_administrador")
	public String newSAdmin(Model model)
	{
		SuperAdmin sa = new SuperAdmin();
		model.addAttribute("super", sa);
		return "crearAdministrador";
	}
	@RequestMapping("/auditoria")
	public String getAuditoria(Model model,@Param("nombresede")String nombresede)
	{
		//String nombreSede = "Concepción Centro";
		List<Sede> sedes = sR.findAll();
		model.addAttribute("sedes", sedes);
		List<Registro>registros = rR.findBySede(nombresede);
		
		
		
		model.addAttribute("registros", registros);
		model.addAttribute("nombresede", nombresede);
		return "auditoria";
	}
	//--------------------------------postmapping---------------------
/*	@PostMapping("/auditoria/{id}")
	public String showAuditoria(@PathVariable("id")long id,Model model,@ModelAttribute("sedes")List<Sede>sedes,
				@ModelAttribute("buscador")BuscadorDeAuditoria buscador,
				@ModelAttribute("registros")List<Registro> registros){
		System.out.println(buscador.getSede());
		registros = rR.findBySede(buscador.getSede());
		System.out.println();
		model.addAttribute("registros", registros);
		return "redirect:/auditoria/mostrar";
	}*/
	
	@PostMapping("/agregar_administrador")
	public String saveSA(@ModelAttribute("super") SuperAdmin superAdmin)
	{
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String pwd = gp.getRandomString();
		String pwdreal = pwd;
		superAdmin.setPassword(pwd);
		superAdmin.setPassword(bCryptPasswordEncoder.encode(superAdmin.getPassword()));
		if(superAdmin.isEs_superadmin() == false)
		{
			superAdmin.adminNormal();
		}
		saR.save(superAdmin);
		mail.sendEmail(superAdmin.getCorreo(), superAdmin.getNombre(), pwdreal);
		return "redirect:/agregar_administrador";
	}
}
