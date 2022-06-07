package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.AdminDetails;
import com.example.demo.MyUserDetails;
import com.example.demo.models.Admin;
import com.example.demo.models.Colaborador;
import com.example.demo.repositorio.AdminRepository;

public class AdminDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	AdminRepository admR;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin admin = admR.getUserByRut(username);
		if(admin == null)
		{
			throw new UsernameNotFoundException("Could not find user");
		}
		return new AdminDetails(admin);
	}

}
