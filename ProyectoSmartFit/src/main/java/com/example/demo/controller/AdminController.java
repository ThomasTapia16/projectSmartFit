package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.models.Admin;
import com.example.demo.models.Colaborador;
import com.example.demo.models.EntrenamientoMasivo;
import com.example.demo.models.Musculacion;
import com.example.demo.models.Piso;
import com.example.demo.models.Sede;
import com.example.demo.repositorio.ColaboradorRepository;
import com.example.demo.repositorio.PisoRepository;
import com.example.demo.repositorio.SedeRepository;
import com.example.demo.service.SedeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class AdminController {
	@Autowired
	SedeService sedeS;
	@Autowired
	SedeRepository sedeR;
	@Autowired
	PisoRepository pisoR;
	@Autowired
	ColaboradorRepository colR;
/*------------------------------------GET MAPPING-----------------------------------------------*/	
	@GetMapping("/agregar_colaborador")
	public String crearColaborador(Model model)
	{	Colaborador colaborador = new Colaborador();
		List<Sede> sedes = (List<Sede>)sedeR.findAll();
		model.addAttribute("sedes",sedes);
		model.addAttribute("colaborador", colaborador);
		
		return "crearColaborador";
	}
	@GetMapping("/agregar_sede")
	public String addSede(Model model)
	{
		Sede sede = new Sede();
		model.addAttribute("sede", sede);
		return "crearSede";
	}
	@GetMapping("/agregar_sala_musculacion")
	public String addSala(Model model)
	{
		Musculacion sala = new Musculacion();
    	Piso piso = new Piso(); 
    	Sede sede = new Sede();
	 	model.addAttribute("sala", sala);
	 	model.addAttribute("region",sedeS.findRegiones());
	 	model.addAttribute("piso",piso);
	 	model.addAttribute("sede",sede);
	 	
	
        return "crearSalaMusculacion";
	}
	@GetMapping("/agregar_sala_entrenamiento_masivo")
	public String addSalaEM(Model model)
	{
		EntrenamientoMasivo sala = new EntrenamientoMasivo();
    	Piso piso = new Piso(); 
    	Sede sede = new Sede();
	 	model.addAttribute("sala", sala);
	 	model.addAttribute("region",sedeS.findRegiones());
	 	model.addAttribute("piso",piso);
	 	model.addAttribute("sede",sede);
	 	
	
        return "crearSalaEntrenamientoMasivo";
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
/*-------------------------------------POST MAPPING--------------------------------------------*/
	@PostMapping("/agregar_sede")
	public String saveSede(@ModelAttribute("sede") Sede sede)
	{
		int c = 0;
		List<Piso>pisos = new ArrayList();
		sedeR.save(sede);
		while(c < sede.getNpisos())
		{
			 Piso piso = new Piso(sede,c+1);
			 pisoR.save(piso);
			 pisos.add(piso);
			 c++;
		 }
		sede.setPisosSede(pisos);
		pisos.clear();
		sedeR.save(sede);
		return "redirect:/agregar_sede";
	}
	@PostMapping("/agregar_colaborador")
	public String saveColaborador(@ModelAttribute("colaborador") Colaborador col)
	{	
		System.out.println("dsfkao");
		Sede sede = sedeR.getById(col.getSede().getId());
		
		List<Colaborador> addcol = new ArrayList();
		addcol.add(col);
		
		sede.setColaboradoresSede(addcol);
		colR.save(col);
		
		sedeR.save(sede);
		addcol.clear();
		return "redirect:/agregar_colaborador";
	}
}
