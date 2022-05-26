package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.MyUserDetails;
import com.example.demo.models.Colaborador;
import com.example.demo.repositorio.ColaboradorRepository;

public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	ColaboradorRepository colR;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Colaborador colaborador = colR.getUserByRut(username);
		if(colaborador == null)
		{
			throw new UsernameNotFoundException("Could not find user");
		}
		return new MyUserDetails(colaborador);
	}

}
