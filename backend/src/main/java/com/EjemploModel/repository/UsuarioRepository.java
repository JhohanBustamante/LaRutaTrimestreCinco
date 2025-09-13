package com.EjemploModel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.EjemploModel.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}