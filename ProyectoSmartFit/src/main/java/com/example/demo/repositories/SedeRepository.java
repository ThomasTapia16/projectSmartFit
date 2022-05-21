package com.example.demo.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Sede;

@Repository
public interface SedeRepository extends JpaRepository<Sede,Long>{
	
	@Query(nativeQuery=true,value = "SELECT nombre FROM Sede s WHERE s.region=:region")
	List<Object[]> byRegion(String region);
	
	@Query(nativeQuery=true,value = "SELECT DISTINCT s.region FROM Sede s ")
	List<Object[]> regiones();
	
	@Query(nativeQuery=true, value = "SELECT p.npiso FROM Pisos p,Sede s WHERE p.sede_id = s.id AND s.nombre=:sede")
	List<Object[]> pisoSede(String sede);
	
	@Query(nativeQuery = true, value="SELECT s.id FROM Sede s WHERE s.nombre=:n")
	Long idSede(String n);
	
	
}
