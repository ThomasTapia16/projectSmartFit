package com.example.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Piso;

public interface PisoRepository extends JpaRepository<Piso, Long>{

}