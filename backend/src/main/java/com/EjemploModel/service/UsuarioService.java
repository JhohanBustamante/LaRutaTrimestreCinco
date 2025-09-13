package com.EjemploModel.service;

import com.EjemploModel.model.Usuario;
import com.EjemploModel.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public void guardar(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);

    }

    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }
}