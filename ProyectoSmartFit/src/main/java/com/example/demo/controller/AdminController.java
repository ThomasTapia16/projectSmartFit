package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.AdminDetails;
import com.example.demo.MyUserDetails;
import com.example.demo.SuperAdminDetails;
import com.example.demo.models.Admin;
import com.example.demo.models.Colaborador;
import com.example.demo.models.EntrenamientoMasivo;
import com.example.demo.models.Musculacion;
import com.example.demo.models.Piso;
import com.example.demo.models.Rechazo;
import com.example.demo.models.Sala;
import com.example.demo.models.Sede;
import com.example.demo.models.Solicitud;
import com.example.demo.models.SuperAdmin;
import com.example.demo.repositorio.AdminRepository;
import com.example.demo.repositorio.ColaboradorRepository;
import com.example.demo.repositorio.PisoRepository;
import com.example.demo.repositorio.SalaRepository;
import com.example.demo.repositorio.SedeRepository;
import com.example.demo.repositorio.SolicitudRepository;
import com.example.demo.repositorio.SuperAdminRepository;
import com.example.demo.service.ChangePwd;

import com.example.demo.service.PasswordEncrypter;
import com.example.demo.service.RandomPasswors;
import com.example.demo.service.SedeService;
import com.example.demo.service.SendEmail;
import com.example.demo.service.validadorRut;
//import com.example.demo.service.verificadorRut;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class AdminController extends BaseController{
	@Autowired
	SedeService sedeS;
	@Autowired
	SedeRepository sedeR;
	@Autowired
	PisoRepository pisoR;
	@Autowired
	SolicitudRepository sol;
	@Autowired
	ColaboradorRepository colR;
	@Autowired
	SalaRepository salaR;
	@Autowired
	SendEmail mail;
	@Autowired
	RandomPasswors gp;
	@Autowired
	PasswordEncrypter encripter;
	@Autowired
	validadorRut vR;
	@Autowired
	ChangePwd changeP;
	@Autowired
	SuperAdminRepository saR;
	@Autowired
	AdminRepository aR;
/*------------------------------------GET MAPPING-----------------------------------------------*/	
	@GetMapping("/agregar_colaborador")
	public String crearColaborador(Model model)
	{	Colaborador colaborador = new Colaborador();
		List<Sede> sedes = (List<Sede>)sedeR.findAll();
		boolean flag = true;
		model.addAttribute("flag",flag);
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
	@GetMapping("/cambio_contraseña_admin")
 	public String cambioPWDADMIN(Model model)
 	{
		AdminDetails user = getLoggedA();
		Admin a = aR.getById(user.getId());
		changeP.setA(a);
		model.addAttribute("changeP", changeP);
		
	
		return "cambiopwda";
 	}
 	@GetMapping("/cambio_contraseña_superadmin")
 	public String cambioPWDSUPERADMIN(Model model)
 	{
 		SuperAdminDetails user = getLoggedSA();
		SuperAdmin sa = saR.getById(user.getId());
		changeP.setSa(sa);
		model.addAttribute("changeP", changeP);
		
	
		return "cambiopwdsa";
 	}
 	@PostMapping("/cambio_contraseña_admin")
 	public String changepwda(@ModelAttribute("changeP")ChangePwd cP)
 	{
 		AdminDetails user = getLoggedA();
 		Admin sa = aR.getById(user.getId());
 		if(cP.ooh()==true)
		{
			
		
			sa.setPassword(encripter.encirptador(cP.pwd));
			aR.save(sa);
		}
 		return "redirect:/homeAdmin";
 	}
 	@PostMapping("/cambio_contraseña_superadmin")
 	public String changepwdsa(@ModelAttribute("changeP")ChangePwd cP)
 	{	
 		SuperAdminDetails user = getLoggedSA();
 		SuperAdmin sa = saR.getById(user.getId());
 		if(cP.ooh()==true)
		{
			
		
			sa.setPassword(encripter.encirptador(cP.pwd));
			saR.save(sa);
		}
 		return "redirect:/homeAdmin";
 	}
	@PostMapping("/agregar_sede")
	public String saveSede(@ModelAttribute("sede") Sede sede)
	{
		int c = 0;
		List<Piso>pisos = new ArrayList<Piso>();
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
	public String saveColaborador(@ModelAttribute("colaborador") Colaborador col,Model model)
	{	
		if(vR.verificador(col.getRut()) == false) {
		String pwd = gp.getRandomString();
		String pwdreal = pwd;
		col.setPassword(pwd);
		System.out.println(col.getPassword());
		col.setPassword(encripter.encirptador(col.getPassword()));
		System.out.println(col.getPassword());
		Sede sede = sedeR.getById(col.getSede().getId());
		if(col.isEs_encargado() == true)
		{
			col.hacerEncargado();
		}
		List<Colaborador> addcol = new ArrayList();
		addcol.add(col);
		
		sede.setColaboradoresSede(addcol);
		colR.save(col);
		mail.sendEmail(col.getCorreo(), col.getNombre(), pwdreal);
		sedeR.save(sede);
		addcol.clear();
		}else {System.out.println("ese wn ya existe");
			model.addAttribute("flag",vR.verificador(col.getRut()));
		}
		return "redirect:/agregar_colaborador";
	}
	@PostMapping("/agregar_sala_musculacion")
 	public String saveMusculacion(@ModelAttribute("sala") Musculacion sala,
			@ModelAttribute("sede")Sede sede, @ModelAttribute("piso")Piso piso)
 	{
		Sede sedeF = sedeR.getById(sedeR.idSede(sede.getNombre()));
		
		Piso pisoSala =  pisoR.getById(pisoR.findPiso(sedeF.getId(), piso.getNuperoPiso()));
		sala.setPiso(pisoSala);
		salaR.save(sala);
		
		sedeR.save(sedeF);
		return "redirect:/agregar_sala_musculacion";
 	}
	@PostMapping("/agregar_sala_entrenamiento_masivo")
	public String saveSEM(@ModelAttribute("sala") EntrenamientoMasivo sala,
			@ModelAttribute("sede")Sede sede, @ModelAttribute("piso")Piso piso)
	{	
		

		System.out.println(sede.getNombre());
		Sede sedeF = sedeR.getById(sedeR.idSede(sede.getNombre()));
		System.out.println(sedeF.getNombre());
		System.out.println(piso.getNuperoPiso());
		Piso pisoSala =  pisoR.getById(pisoR.findPiso(sedeF.getId(), piso.getNuperoPiso()));
		sala.setPiso(pisoSala);
		salaR.save(sala);
		
		sedeR.save(sedeF);
		return "redirect:/agregar_sala_entrenamiento_masivo";
	}
	
	
	
	@GetMapping("/homeAdmin")
	public String iniciarAdmin(Model model)
	{
		boolean a = true;
		
		SuperAdminDetails sa = getLoggedSA();
		List<Solicitud> solicitudes = sol.findByEstado(a);
		model.addAttribute("tipo",a);
		model.addAttribute("solicitudes",solicitudes);
		return "home";
		
	}
	
	@GetMapping("/rechazado/{id}")
	public String getRechazado(@PathVariable("id")Long idSoli,Model model) {
		
		
		Rechazo rechazo = new Rechazo();
		Solicitud soli = sol.getById(idSoli);
		model.addAttribute("solicitud",soli);
		model.addAttribute("rechazo",rechazo);
		
		
		return "rechazado";
		
	}
	@PostMapping("/rechazado/{id}")
	public String rechazarSolicitud(@PathVariable("id")Long idSoli,@ModelAttribute("rechazo")Rechazo rechazo,@ModelAttribute("soli")Solicitud soli) {
		
		Solicitud solic = sol.getById(idSoli);
		mail.sendEmailRechazo(solic.getCorreoEncargadoSede(), idSoli, rechazo.getMotivo());
		solic.setEstado(false);
		sol.save(solic);
		return "redirect:/homeAdmin";
	}
	@GetMapping("/aceptado/{id}")
	public String getAceptado() {
		
		
		return "home";
		
	}
	
	@PostMapping("/aceptado/{id}")
	public String aceptarSolicirud(@PathVariable("id")Long idSolictud)
	{	
		Solicitud soli = sol.getById(idSolictud);
		Colaborador colaborador = new Colaborador();
		colaborador.setRut(soli.getRut());
		colaborador.setNombre(soli.getNombre());
		colaborador.setApellido(soli.getApellido());
		colaborador.setCorreo(soli.getCorreo());
		colaborador.setSede(soli.getSede());
		String pwd = gp.getRandomString();
		String real = pwd;
		colaborador.setPassword(pwd);
		colaborador.setPassword(encripter.encirptador(colaborador.getPassword()));
		
		mail.sendEmail(colaborador.getCorreo(), colaborador.getNombre(), real);
		Sede sede = sedeR.getById(colaborador.getSede().getId());
		
		List<Colaborador> addcol = new ArrayList();
		addcol.add(colaborador);
		
		sede.setColaboradoresSede(addcol);
		colR.save(colaborador);
		
		sedeR.save(sede);
		soli.solicitudProcesada();
		sol.save(soli);
		return "redirect:/homeAdmin";
	}

	
	
}
