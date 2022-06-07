package com.example.demo.repositorio;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Admin;


public interface AdminRepository extends JpaRepository<Admin,Long>{
	
	@Query("SELECT a FROM Admin a WHERE a.rut=:rut")
	public Admin getUserByRut(String rut);
	
}
