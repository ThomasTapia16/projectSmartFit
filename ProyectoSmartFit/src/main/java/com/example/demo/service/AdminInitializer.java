package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.SuperAdmin;
import com.example.demo.repositorio.SuperAdminRepository;

@Service
public class AdminInitializer {
	
	@Autowired
	SuperAdminRepository sa;
	@Autowired
	PasswordEncrypter pE;
	public void verificar()
	{
		List<SuperAdmin> admins = sa.findAll();
		if(admins.size() == 0)
		{
			SuperAdmin s = new SuperAdmin();
			s = setAtributos(s);
			sa.save(s);
			
			
		}
	}
	public SuperAdmin setAtributos(SuperAdmin s)
	{
		s.setRut("000000000");
		s.setNombre("admin");
		s.setCorreo("admin@admin");
		s.setApellido("admin");
		s.setPassword(pE.encirptador("admin"));
		s.setRol("ROLE_SUPERADMIN");
		s.setEnabled(true);
		return s;
	}
}
