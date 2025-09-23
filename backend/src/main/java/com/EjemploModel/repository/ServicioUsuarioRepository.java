package com.EjemploModel.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.EjemploModel.model.ServicioUsuario;


public interface ServicioUsuarioRepository  extends JpaRepository<ServicioUsuario, Long> {

}