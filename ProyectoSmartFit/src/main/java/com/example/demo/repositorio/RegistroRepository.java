package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Registro;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Long>{
	
}