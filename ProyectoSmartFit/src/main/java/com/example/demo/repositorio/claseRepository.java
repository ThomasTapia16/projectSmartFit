package com.example.demo.repositorio;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Clase;

public interface claseRepository extends JpaRepository<Clase,Long>{
	
	@Query("SELECT  c FROM Clase c WHERE c.sala_id:=sala AND c.activo=true")
	Clase findByActivo(long sala);

}
