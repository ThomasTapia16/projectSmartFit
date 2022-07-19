package com.example.demo.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Piso;
import com.example.demo.models.Solicitud;

@Repository
public interface PisoRepository extends JpaRepository<Piso, Long>{
	
	@Query(nativeQuery = true, value = "select p.id from Pisos p where p.sede_id =:id_sede and p.nupero_piso =:npiso")
	Long findPiso(Long id_sede, int npiso);
	@Query(nativeQuery = true, value = "SELECT s.id FROM Sala s INNER JOIN Pisos p where s.piso_id = p.id AND p.sede_id =:idSede")
	List<Long> findIdSala(long idSede);
	
	@Query(nativeQuery = true,value = "SELECT * FROM Pisos  WHERE sede_id=:id")
	List<Piso> findPisoBySedeId(long id);
	
	@Query(nativeQuery = true,value = "SELECT * FROM Pisos  WHERE activo=true")
	String findByActivo();

}
