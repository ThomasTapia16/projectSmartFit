package com.example.demo.repositorio;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Clase;

public interface claseRepository extends JpaRepository<Clase,Long>{
	
	@Query(value = "SELECT  c FROM Clase c WHERE c.activo=1 AND c.sala_id=:sala")
	List<Clase> findClaseBySalaId(long sala);

}
