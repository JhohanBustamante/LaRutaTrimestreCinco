package com.EjemploModel.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EjemploModel.model.Servicio;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {

    Optional<Servicio> findByNombre(String nombre);

    List<Servicio> findByIdCreador(Long idCreador);

    List<Servicio> findByComunidad_Id(Long comunidadId);

}
