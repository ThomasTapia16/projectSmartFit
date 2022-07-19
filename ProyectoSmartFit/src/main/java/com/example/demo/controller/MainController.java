	package com.example.demo.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.AdminDetails;
import com.example.demo.MyUserDetails;
import com.example.demo.SuperAdminDetails;
import com.example.demo.models.Clase;
import com.example.demo.models.Colaborador;
import com.example.demo.models.EntrenamientoMasivo;
import com.example.demo.models.Piso;
import com.example.demo.models.Registro;
import com.example.demo.models.Sala;
import com.example.demo.models.Sede;
import com.example.demo.models.Solicitud;
import com.example.demo.repositorio.ColaboradorRepository;
import com.example.demo.repositorio.EMRepository;
import com.example.demo.repositorio.PisoRepository;
import com.example.demo.repositorio.SalaRepository;
import com.example.demo.repositorio.SedeRepository;
import com.example.demo.repositorio.SolicitudRepository;
import com.example.demo.repositorio.claseRepository;
import com.example.demo.service.AdminInitializer;
import com.example.demo.service.ChangePwd;
import com.example.demo.service.ClaseService;
import com.example.demo.service.ColaboradorService;
import com.example.demo.service.PasswordEncrypter;
import com.example.demo.service.RegistroService;
import com.example.demo.service.SedeService;
import com.example.demo.service.SendEmail;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Controller("/")
@SessionAttributes 
public class MainController extends BaseController{
	
	@Autowired
	PisoRepository pisoR;
	@Autowired
	SalaRepository salaR;
	@Autowired
	claseRepository claseR;
	@Autowired
	EMRepository emR;
	@Autowired
	ColaboradorRepository colR;
	@Autowired
	ChangePwd changeP;
	@Autowired
	PasswordEncrypter encripter;
	@Autowired
	ColaboradorService cS;
	@Autowired
	AdminInitializer adI;
	@Autowired
	RegistroService registro;
	@Autowired
	SolicitudRepository solR;
	@Autowired
	SendEmail mail;
	
	
	@GetMapping("/")
	public String login()
	{	
		adI.verificar();
		return "login";
	}
	
	@PostMapping("/")
	public String entrar()
	{	
		
		return "redirect:/home";
	}
	@GetMapping("cerrarSesion")
	public String logOut()
	{	SecurityContextHolder.getContext().setAuthentication(null);
		return "login";
	}
	
	@GetMapping("/enviarSolicitud")
	public String cargarSolicitud(Model model) {
		Solicitud solicitud = new Solicitud();
		
		model.addAttribute("solicitud",solicitud);
		
		return "crearSolicitud";
	}
	@PostMapping("/enviarSolicitud")
	public String cargarSolicitud(@ModelAttribute("solicitud")Solicitud solicitud) {
		MyUserDetails user = getLoggedUser();
		solicitud.setCorreoEncargadoSede(user.getCorreo());
		solicitud.setSede(user);
		solR.save(solicitud);
		mail.sendEmailSolicitud(user.getCorreo(),solicitud);
		return "crearSolicitud";
	}
	
	
	
	@GetMapping("/home")
	public String home(Model model){
		
		
		
		
		
		MyUserDetails user = getLoggedUser();
		
		if(user != null) {
		
		
		Sede sede = user.getSede();
		//LinkedList<Sala> salas = new LinkedList<Sala>();
		
		
		List<EntrenamientoMasivo> salas = emR.findAllById(pisoR.findIdSala(sede.getId()));
		List<Piso> pisos = pisoR.findPisoBySedeId(sede.getId());
		model.addAttribute("pisos",pisos);
		model.addAttribute("sede",sede);
		model.addAttribute("salas",salas);
		
		}else if(user == null)
		{	
			SuperAdminDetails sa = getLoggedSA();
			
		}
		
		return "home";
	}
	
	@Autowired
	ClaseService cr;
	@Autowired
	claseRepository Cr;
	@GetMapping("/salaSeleccionada/{id}")
	public String sala(@PathVariable("id") Long idSala, Model model, RedirectAttributes attribute)
	{	
		

		EntrenamientoMasivo sala = emR.getById(idSala);
		
		Clase claseActual = cr.buscar(sala.getId());
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH");
		if(claseActual == null) {
		Clase clase = new Clase();
	
		MyUserDetails user = getLoggedUser();
		
		List<Colaborador> colaboradores = cS.buscar(user.getSede().getId());
		//System.out.println(colaboradores.size());
		
		//model.addAttribute("colaboradores",cS.porsede(1));
		model.addAttribute("profe",colaboradores);
		model.addAttribute("hora",dtf.format(LocalDateTime.now()));
		model.addAttribute("sala",sala);
		model.addAttribute("piso",sala.getPiso());
		System.out.println(dtf.format(LocalDateTime.now()));
		model.addAttribute("clase",clase);
		model.addAttribute("motivo",new String());
		}else {
			
			System.out.println("XD");
			model.addAttribute("sala",sala);
			model.addAttribute("clase",claseActual);
			model.addAttribute("hora",dtf.format(LocalDateTime.now()));

		}
		return "seleccionSala";
	}
	@PostMapping("/desocuparSala/{id}")
	public String desocuparSala(@ModelAttribute("clase")Clase clase,@ModelAttribute("sala")EntrenamientoMasivo sala)
	{	System.out.println(sala.getId());
		clase  = cr.buscar(sala.getId());
		sala.setDesocupado();
		clase.setActivo(false);
		salaR.save(sala);
		Cr.save(clase);
		
		return "redirect:/home";
	}
	@GetMapping("/piso/{id}")
	public String getPiso(@PathVariable("id")Long idPiso, Model model) {
		
		MyUserDetails user = getLoggedUser();
		List<Colaborador> colaboradores = cS.buscar(user.getSede().getId());
		Piso piso = pisoR.getById(idPiso);
		int numero = piso.getNuperoPiso();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH");
		model.addAttribute("profe",colaboradores);
		model.addAttribute("piso",piso);
		model.addAttribute("sede",user.getSede());
		model.addAttribute("hora",dtf.format(LocalDateTime.now()));
		return "pisoSeleccionado";
	}
	@PostMapping("/piso/{id}")
	public String getPiso(@PathVariable("id")Long idPiso,@ModelAttribute("piso")Piso piso) {
		System.out.println(piso.getSede());
		pisoR.save(piso);
		return "redirect:/home";
	}
	
	
	@PostMapping("salaSeleccionada/deshabilitar/{id}")
	public String deshabilitar(@PathVariable("id")Long idSala,@ModelAttribute("sala")EntrenamientoMasivo sala,
			@ModelAttribute("motivo")String motivo)
	{
		System.out.println(motivo);
		sala.setDeshabilitado();
		
		emR.save(sala);
		return "redirect:/salaSeleccionada/{id}";
	}
	@PostMapping("/salaSeleccionada/{id}")
	public String ocuparSala(@ModelAttribute("sala")EntrenamientoMasivo sala
			,@ModelAttribute("clase")Clase clase,@ModelAttribute("hora")String hora
			)
	{	
		MyUserDetails user = getLoggedUser();
		clase.leerCol();
		System.out.println(sala.getPiso());
		clase.setSala(sala);
		claseR.save(clase);
		sala.setOcupado();
		sala.setNumeroSala(sala.getNumeroSala());
		
		
		salaR.save(sala);
		registro.regitrar(user,clase,sala);
		return "redirect:/home";
	}
	@PostMapping("/habilitar/{id}")
	public String habilitarSala(@PathVariable("id")Long id)
	{
		EntrenamientoMasivo sala = emR.getById(id);
		sala.setDesocupado();
		emR.save(sala);
		
		return "redirect:/home";
	}
	@GetMapping("/loginAdmin")
	public String getAdminLog()
	{
		return "loginAdmin";
	}
	@GetMapping("/cambio_contraseña")
	public String changePwd(Model model)
	{	
		MyUserDetails user = getLoggedUser();
		Colaborador col = colR.getById(user.getid());
		changeP.setCol(col);
		model.addAttribute("changeP", changeP);
		
	
		return "cambiopwd";
	}

	@PostMapping("/cambio_contraseña")
	public String changePwd(@ModelAttribute("changeP")ChangePwd cP)
	{	MyUserDetails user = getLoggedUser();
		Colaborador col = colR.getById(user.getid());
		
		if(cP.ooh()==true)
		{
			
		
			col.setPassword(encripter.encirptador(cP.pwd));
			colR.save(col);
		}
		
		
	
		return "redirect:/home";
	}
	

}
