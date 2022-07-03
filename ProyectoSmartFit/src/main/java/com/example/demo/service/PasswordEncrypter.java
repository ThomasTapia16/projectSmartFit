package com.example.demo.service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class PasswordEncrypter {
	
	public String encirptador(String pwd)
	{
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		pwd = bCryptPasswordEncoder.encode(pwd);
		return pwd;
	}

}
