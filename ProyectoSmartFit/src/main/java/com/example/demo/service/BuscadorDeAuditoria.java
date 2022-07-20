package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.models.Sede;

@Service
public class BuscadorDeAuditoria {
	
	private long sede;

	public long getSede() {
		return sede;
	}

	public void setSede(long sede) {
		this.sede = sede;
	}
	
	
}
