package com.example.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	SuccesHandler handler;
	  String[] resources = new String[]{
	            "/include/**","/css/**","/icons/**","/img/**","/js/**","/layer/**"
	    };
	@Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }
     
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
     
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
   
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers(resources).permitAll()
        .antMatchers("/").permitAll()
        .antMatchers("/login").permitAll()
        .antMatchers("/home").access("hasRole('COL') or hasRole('SUPERADMIN') or hasRole('ADMIN') or hasRole('ENCARGADO')")
        .antMatchers("/agregar_sede").access("hasRole('ADMIN') or hasRole('SUPERADMIN')")
        .antMatchers("/agregar_colaborador").access("hasRole('SUPERADMIN') or hasRole('ADMIN')")
        .antMatchers("/agregar_sala_musculacion").access("hasRole('ADMIN') or hasRole('SUPERADMIN')")
        .antMatchers("/agregar_sala_entrenamiento_masivo").access("hasRole('ADMIN') or hasRole('SUPERADMIN') or hasRole('COL')")
        .antMatchers("/agregar_administrador").access("hasRole('SUPERADMIN')")
        .antMatchers("/agregar_super_administrador").access("hasRole('SUPERADMIN')")
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/")
            .permitAll()
            .successHandler(handler)
            
            .usernameParameter("username")
            .passwordParameter("password")
            .and()
        .logout()
            .permitAll()
            .logoutSuccessUrl("/login?logout");
            
        http.csrf().disable();
        
    }
	
}