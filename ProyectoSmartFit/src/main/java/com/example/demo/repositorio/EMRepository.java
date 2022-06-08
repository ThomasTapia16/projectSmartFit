package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.EntrenamientoMasivo;

public interface EMRepository extends JpaRepository<EntrenamientoMasivo,Long>{

}
