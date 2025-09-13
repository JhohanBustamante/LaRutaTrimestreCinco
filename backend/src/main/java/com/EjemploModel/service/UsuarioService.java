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

    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario buscarPorApodo(String apodo) {
        return usuarioRepository.findByApodo(apodo).orElse(null);
    }

    public Usuario buscarPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo).orElse(null);
    }

    public Usuario validacionInicio(String apodo, String correo ,String contrasena){
        return usuarioRepository.findByApodoAndContrasenaOrCorreoAndContrasena(apodo, contrasena, correo, contrasena).orElse(null);
    }

    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario actualizarUsuario(Long id, Usuario datosNuevos) {
    return usuarioRepository.findById(id).map(usuario -> {
        if (datosNuevos.getNombre() != null && !datosNuevos.getNombre().isEmpty()) {
            usuario.setNombre(datosNuevos.getNombre());
        }

        if (datosNuevos.getApodo() != null && !datosNuevos.getApodo().isEmpty()) {
            usuario.setApodo(datosNuevos.getApodo());
        }

        if (datosNuevos.getContrasena() != null && !datosNuevos.getContrasena().isEmpty()) {
            usuario.setContrasena(datosNuevos.getContrasena());
        }

        if (datosNuevos.getApellido() != null && !datosNuevos.getApellido().isEmpty()) {
            usuario.setApellido(datosNuevos.getApellido());
        }

        if (datosNuevos.getCorreo() != null && !datosNuevos.getCorreo().isEmpty()) {
            usuario.setCorreo(datosNuevos.getCorreo());
        }
        if (datosNuevos.getAltura() > 0) {
            usuario.setAltura(datosNuevos.getAltura());
        }

        if (datosNuevos.getRol() != null) {
            usuario.setRol(datosNuevos.getRol());
        }

        return usuarioRepository.save(usuario);
    }).orElse(null);
    }
}