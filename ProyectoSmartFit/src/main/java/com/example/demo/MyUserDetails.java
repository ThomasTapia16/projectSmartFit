package com.example.demo;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.models.Colaborador;
import com.example.demo.models.Sede;

public class MyUserDetails implements UserDetails{
	
	private Colaborador colaborador;
	
	public MyUserDetails(Colaborador colaborador) {
		this.colaborador = colaborador;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(colaborador.getRol());
		return Arrays.asList(authority);
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return colaborador.getPassword();
	}
	public Sede getSede()
	{
		return colaborador.getSede();
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return colaborador.getRut();
	}
	public long getid()
	{
		return colaborador.getId();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public String getNombre()
	{
		return colaborador.getNombre();
	}
	public String getApellido()
	{
		return colaborador.getApellido();
	}
	public String getCorreo()
	{
		return colaborador.getCorreo();
	}

}
