package com.example.demo.repositorio;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Colaborador;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador,Long>{
	@Query("SELECT c FROM Colaborador c WHERE c.rut=:rut")
	public Colaborador getUserByRut(String rut);
	
	//@Query("SELECT c.rut FROM Colaborador c")
	//List<Object[]> findAllRut();
	@Query( value = "SELECT * FROM Colaborador c WHERE c.sede_id:=id")
	List<Colaborador> getUserBySede_Id(long id);
}
