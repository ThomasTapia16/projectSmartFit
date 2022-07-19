package com.example.demo.repositorio;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Solicitud;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Long>{
	
	@Query("SELECT s FROM Solicitud s WHERE s.estado:=a")
	List<Solicitud> findByEstado(boolean a);
}
