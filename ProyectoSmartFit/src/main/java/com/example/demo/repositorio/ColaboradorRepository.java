package com.example.demo.repositorio;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Colaborador;

public interface ColaboradorRepository extends JpaRepository<Colaborador,Long>{
	@Query("SELECT c FROM Colaborador c WHERE c.rut=:rut")
	public Colaborador getUserByRut(String rut);
}
