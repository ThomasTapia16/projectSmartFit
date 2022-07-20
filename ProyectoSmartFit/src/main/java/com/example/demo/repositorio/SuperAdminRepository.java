package com.example.demo.repositorio;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.SuperAdmin;

public interface SuperAdminRepository extends JpaRepository<SuperAdmin,Long>{
	
	@Query("SELECT sa FROM super_admin sa WHERE sa.rut=:rut")
	public SuperAdmin getUserByRut(String rut);
}
