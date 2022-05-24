package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Administrador;


public interface AdminRepository extends JpaRepository<Administrador,Long>{
	Administrador getByRut(String rut);
}
