package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Persona;
import com.example.demo.models.Sede;

@Repository
public interface ColaboradorRepository extends JpaRepository<Persona,String>{
	
	
}
