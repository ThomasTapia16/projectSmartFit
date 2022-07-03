
package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.AdminDetails;
import com.example.demo.MyUserDetails;
import com.example.demo.SuperAdminDetails;
import com.example.demo.models.Admin;
import com.example.demo.models.Colaborador;
import com.example.demo.models.SuperAdmin;
import com.example.demo.repositorio.AdminRepository;
import com.example.demo.repositorio.ColaboradorRepository;
import com.example.demo.repositorio.SuperAdminRepository;

public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	ColaboradorRepository colR;
	@Autowired
	AdminRepository admR;
	@Autowired
	SuperAdminRepository saR;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Colaborador colaborador = colR.getUserByRut(username);
		if(colaborador == null)
		{	
			
			Admin admin = admR.getUserByRut(username);
			if(admin == null)
			{
				SuperAdmin sa = saR.getUserByRut(username);
				if(sa == null)
				{
					
				}else {System.out.println("sa");return  new SuperAdminDetails(sa);}
			}else {return new AdminDetails(admin);}
			
			
			
			
		}else {return new MyUserDetails(colaborador);}
		return null;
		
	}

}
