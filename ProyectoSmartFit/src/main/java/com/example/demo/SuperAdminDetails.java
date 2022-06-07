package com.example.demo;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.models.SuperAdmin;

public class SuperAdminDetails implements UserDetails{

	private SuperAdmin superAdmin;
	public SuperAdminDetails(SuperAdmin superAdmin2) {
		// TODO Auto-generated constructor stub
		this.superAdmin = superAdmin2;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
				SimpleGrantedAuthority authority = new SimpleGrantedAuthority(superAdmin.getRol());
				return Arrays.asList(authority);
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return superAdmin.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return superAdmin.getRut();
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

}
