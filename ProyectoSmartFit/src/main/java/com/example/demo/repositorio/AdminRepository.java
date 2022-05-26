package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Admin;

public interface AdminRepository extends JpaRepository<Admin,Long>{
	
	
}
