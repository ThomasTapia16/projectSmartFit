package com.example.demo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Sede;

public interface SedeRepository extends JpaRepository<Sede,Long>{
	
	@Query(nativeQuery=true,value = "SELECT nombre FROM Sede s WHERE s.region=:region")
	List<Object[]> byRegion(String region);
	
	@Query(nativeQuery=true,value = "SELECT DISTINCT s.region FROM Sede s ")
	List<Object[]> regiones();
	
	@Query(nativeQuery=true, value = "SELECT p.nupero_piso FROM Pisos p,Sede s WHERE p.sede_id = s.id AND s.nombre=:sede")
	List<Object[]> pisoSede(String sede);
	
	@Query(nativeQuery = true, value="SELECT s.id FROM Sede s WHERE s.nombre=:n")
	Long idSede(String n);
	

}
