package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Piso;

public interface PisoRepository extends JpaRepository<Piso,Long>{
	
	@Query(nativeQuery =true, value = "SELECT p.id FROM Pisos p WHERE p.sede_id = (SELECT s.id FROM Sede s WHERE s.nombre=:nombre) AND p.npiso=:npiso")
	Long idPiso(String nombre, int npiso);
}
