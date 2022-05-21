package com.example.demo.controller;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
import com.example.demo.services.sedeS;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	@Autowired
	sedeS sedeS;
	
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
	 
	 //--------------------------------MUSCULACION------------------------------
	 @GetMapping("/crearSalaMusculacion")
	    public String crearSalaMusculacion(Model model)
	    {	
	    	SalaMusculacion sala = new SalaMusculacion();
	    	Piso piso = new Piso(); 
	    	Sede sede = new Sede();
		 	model.addAttribute("sala", sala);
		 	model.addAttribute("region",sedeS.findRegiones());
		 	model.addAttribute("piso",piso);
		 	model.addAttribute("sede",sede);
		 	
		
	        return "crearSalaMusculacion";
	    }
	 	@GetMapping("/getSedes")
		public @ResponseBody String getSedes(@RequestParam String region)
		{
			String json = null;
			List<Object[]> nombresSede = sedeS.findNombres(region);
			//System.out.println(nombresSede.size());
			
			try {
				json = new ObjectMapper().writeValueAsString(nombresSede);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			
			return json;
		}
	 	@GetMapping("/getPiso")
		public @ResponseBody String getPiso(@RequestParam String nombresede)
		{
			String json = null;
			
			
			List<Object[]> list = sedeS.buscarPiso(nombresede);
			
			try {
				json = new ObjectMapper().writeValueAsString(list);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			
			
			return json;
		}
	 	@PostMapping("/crearSalaMusculacion")
	 	public String saveSala(@ModelAttribute("sala") SalaMusculacion sala,@ModelAttribute("sede") Sede sede,@ModelAttribute("piso") Piso piso)
	 	{
	 		
			System.out.println(sede.getNombre());
			System.out.println(piso.getNpiso());
			Sede sedeSave = sedeR.getById(sedeR.idSede(sede.getNombre()));
			Piso pisoSave = pisoR.getById(pisoR.idPiso(sede.getNombre(), piso.getNpiso()));
			
			System.out.println(pisoSave.getId());
			sala.setSede(sedeSave);
			sala.setPiso(pisoSave);
	 		salaR.save(sala);
	 		return "redirect:/crearSalaMusculacion";
	 }
	 	//---------------------------------------------------------------------------------
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