package com.example.demo;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
@Component
public class SuccesHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		// TODO Auto-generated method stub
		for (String cadena : roles) {
			 System.out.println(cadena);
			 }
		if( roles.contains("ROLE_SUPERADMIN") || roles.contains("ROLE_ADMIN"))
		{	
			
			response.sendRedirect("/homeAdmin");
			
			
		}if( roles.contains("ROLE_COL"))
			response.sendRedirect("/home");
	}

}
