package com.laruta.api.repositories;

import com.laruta.api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRep extends JpaRepository<Usuario, Long> {
}
