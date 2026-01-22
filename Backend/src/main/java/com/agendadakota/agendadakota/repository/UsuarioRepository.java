package com.agendadakota.agendadakota.repository;

import com.agendadakota.agendadakota.entities.Rol;
import com.agendadakota.agendadakota.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    long countByRol(Rol rol);

    List<Usuario> findByRol(Rol rol);
}
