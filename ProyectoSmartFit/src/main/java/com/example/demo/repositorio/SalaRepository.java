package com.example.demo.repositorio;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Sala;

public interface SalaRepository extends JpaRepository<Sala,Long>{
	
	
}
