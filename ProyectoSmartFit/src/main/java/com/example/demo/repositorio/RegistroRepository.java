package com.example.demo.repositorio;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Registro;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Long>{
	
	@Query("SELECT * FROM registro WHERE sede=:nombre")
	List<Registro> findBySede(String nombre);
}