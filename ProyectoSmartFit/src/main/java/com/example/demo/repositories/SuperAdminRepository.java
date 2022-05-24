package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.SuperAdministrador;

public interface SuperAdminRepository extends JpaRepository<SuperAdministrador, Long>{
	
	SuperAdministrador getByRut(String rut);
}
