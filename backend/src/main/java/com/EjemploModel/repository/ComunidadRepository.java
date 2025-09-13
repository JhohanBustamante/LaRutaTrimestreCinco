package com.EjemploModel.repository;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import com.EjemploModel.model.Comunidad;

public interface ComunidadRepository extends JpaRepository<Comunidad, Long> {
     Optional<Comunidad> findByNombre(String nombre);
}