package com.EjemploModel.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.EjemploModel.model.Comunidad;

public interface ComunidadRepository extends JpaRepository<Comunidad, Long> {
}