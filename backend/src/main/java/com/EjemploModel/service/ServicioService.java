package com.EjemploModel.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.EjemploModel.dto.ServicioDto;
import com.EjemploModel.model.Servicio;
import com.EjemploModel.model.ServicioUsuario;
import com.EjemploModel.model.Usuario;
import com.EjemploModel.repository.ServicioRepository;
import com.EjemploModel.repository.ServicioUsuarioRepository;
import com.EjemploModel.repository.UsuarioRepository;

@Service
public class ServicioService {

    private final ServicioRepository servicioRepository;
    private final ServicioUsuarioRepository servicioUsuarioRepository;
    private final UsuarioRepository usuarioRepository;

    public ServicioService(
            ServicioRepository servicioRepository,
            ServicioUsuarioRepository servicioUsuarioRepository,
            UsuarioRepository usuarioRepository
    ) {
        this.servicioRepository = servicioRepository;
        this.servicioUsuarioRepository = servicioUsuarioRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // Listar todos los servicios como DTOs
    public List<ServicioDto> listarTodos() {
        return servicioRepository.findAll()
                .stream()
                .map(s -> new ServicioDto(
                        s.getId(),
                        s.getNombre(),
                        s.getDescripcion(),
                        s.getCategoria(),
                        s.getEstado(),
                        s.getFecha()
                ))
                .toList();
    }

    // Guardar nuevo servicio
    public Servicio guardar(Servicio servicio) {
        if (servicio.getFecha() == null) {
            servicio.setFecha(LocalDate.now());
        }
        return servicioRepository.save(servicio);
    }

    // Buscar servicio por nombre
    public Servicio buscarPorNombre(String nombre) {
        return servicioRepository.findByNombre(nombre).orElse(null);
    }

    // Buscar servicio por ID
    public Servicio buscarPorId(Long id) {
        return servicioRepository.findById(id).orElse(null);
    }

    // Eliminar servicio por ID
    public void eliminar(Long id) {
        servicioRepository.deleteById(id);
    }

    // Vincular usuario a un servicio
    public ServicioUsuario vincularUsuarioAServicio(Long usuarioId, Long servicioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Servicio servicio = servicioRepository.findById(servicioId)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

        ServicioUsuario su = new ServicioUsuario();
        su.setUsuario(usuario);
        su.setServicio(servicio);

        return servicioUsuarioRepository.save(su);
    }

 public List<ServicioDto> buscarPorComunidadId(Long comunidadId) {
    return servicioRepository.findByComunidad_Id(comunidadId)  // corregido el parámetro en minúscula
        .stream()
        .map(s -> new ServicioDto(
            s.getId(),
            s.getNombre(),
            s.getDescripcion(),
            s.getCategoria(),
            s.getEstado(),
            s.getFecha()
        ))
        .toList();
}

}
