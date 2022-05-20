package com.example.demo.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.Administrador;
import com.example.demo.models.Colaborador;
import com.example.demo.models.Piso;
import com.example.demo.models.Sala;
import com.example.demo.models.SalaEntrenamientoMasivo;
import com.example.demo.models.SalaMusculacion;
import com.example.demo.models.Sede;
import com.example.demo.models.SuperAdministrador;
import com.example.demo.repositories.ColaboradorRepository;
import com.example.demo.repositories.DtiRepository;
import com.example.demo.repositories.PisoRepository;
import com.example.demo.repositories.SalaRepository;
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
	
	
	
	
	@Autowired 
    DtiRepository dti;
	@Autowired
	ColaboradorRepository colaboradorR;
	@Autowired
	SedeRepository sedeR;
	@Autowired
	SalaRepository salaR;
	@Autowired
	PisoRepository pisoR;
	
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
	 
	 @GetMapping("/crearSalaMusculacion")
	    public String crearSalaMusculacion(Model model)
	    {	List<Sede> sedes = (List<Sede>)sedeR.findAll();
	    	SalaMusculacion sala = new SalaMusculacion();
		 	model.addAttribute("sala", sala);
		 	model.addAttribute("sedes",sedes);
		
	        return "crearSalaMusculacion";
	    }
	 	@PostMapping("/crearSalaMusculacion")
	 	public String saveSala(@ModelAttribute("sala") SalaMusculacion sala){
		 
	 		salaR.save(sala);
	 		return "redirect:/crearSalaMusculacion";
	 }
	    @GetMapping("/crearSalaEntrenamientoMasivo")
	    public String crearEntrenamientoMasivo(Model model)
	   {	List<Sede> sedes = (List<Sede>)sedeR.findAll();
	   		SalaEntrenamientoMasivo sala = new SalaEntrenamientoMasivo();
	    	model.addAttribute("sala", sala);
	    	model.addAttribute("sedes",sedes);
	        return "crearSalaEntrenamientoMasivo";
	    }
	 @GetMapping("/crearSede")
		public String newSede(Model model)
		{	Sede sede = new Sede();
			model.addAttribute("sede", sede);
			return "crearSede";
		}
	 
	 @PostMapping("/crearSede")
	 public String saveSede(@ModelAttribute("sede") Sede sede){
		 
		 int c = 0;
		 
		 sedeR.save(sede);
		 while(c < sede.getNpisos())
		 {
			 Piso piso = new Piso(sede,c+1);
			 pisoR.save(piso);
			 
			 c++;
		 }
		 return "redirect:/crearSede";
	 }
	 
	 
	@GetMapping("/crearColaborador")
	public String crearColaborador(Model model)
	{	
		
		List<Sede> sedes = (List<Sede>)sedeR.findAll();
		Colaborador col = new Colaborador();
		model.addAttribute("colaborador",col);
		model.addAttribute("sedes",sedes);
		return "crearColaborador";
	}
	 @PostMapping("/crearColaborador")
	 public String saveColaborador(@ModelAttribute("colaborador") Colaborador col){
		 
		 Sede sede = sedeR.getById(col.getSede().getId());
		 
		 //System.out.println(col.isEs_ecargado());
		
		 sede.setColaboradores(col);
		 
		 
		 colaboradorR.save(col);
		 if(col.isEs_ecargado())
		 {
			 sede.setEncargado(col);
		 }
		 //System.out.println(sede.getEncargado().getRut());
		 sedeR.save(sede);
		 return "redirect:/crearColaborador";
	 }
	 
	 @GetMapping("/cerrarSesion")
		public String cerrarSesion()
		{	
			return "login";
		}
	 
}