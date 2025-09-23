package com.EjemploModel.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EjemploModel.dto.ServicioDto;
import com.EjemploModel.model.Servicio;
import com.EjemploModel.model.ServicioUsuario;
import com.EjemploModel.model.Usuario;
import com.EjemploModel.repository.ServicioUsuarioRepository;
import com.EjemploModel.repository.UsuarioRepository;
import com.EjemploModel.service.ServicioService;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class ServicioController {

    private final ServicioService servicioService;
    private final UsuarioRepository usuarioRepository;
    private final ServicioUsuarioRepository servicioUsuarioRepository;

    public ServicioController(
            ServicioService servicioService,
            UsuarioRepository usuarioRepository,
            ServicioUsuarioRepository servicioUsuarioRepository) {
        this.servicioService = servicioService;
        this.usuarioRepository = usuarioRepository;
        this.servicioUsuarioRepository = servicioUsuarioRepository;
    }

    // GET: Listar todos los servicios como DTO
    @GetMapping("/servicios")
    public List<ServicioDto> listarServicios() {
        return servicioService.listarTodos();
    }

    // GET: Obtener servicio por ID
    @GetMapping("/servicio/{id}")
    public Servicio obtenerPorId(@PathVariable Long id) {
        return servicioService.buscarPorId(id);
    }
    @GetMapping("/servicio/comunidad/{comunidadId}")
public List<ServicioDto> obtenerServiciosPorComunidad(@PathVariable Long comunidadId) {
    return servicioService.buscarPorComunidadId(comunidadId);
}


    // POST: Crear un nuevo servicio
    @PostMapping("/servicio/crear")
    public ResponseEntity<?> crear(@RequestBody Servicio servicio) {

        if (servicioService.buscarPorNombre(servicio.getNombre()) != null) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of(
                            "estado", false,
                            "mensaje", "Ya existe un servicio con ese nombre"));
        }

        // Verificamos que el usuario creador exista
        Usuario creador = usuarioRepository.findById(servicio.getIdCreador())
                .orElseThrow(() -> new RuntimeException("Usuario creador no encontrado"));

        // Guardamos el servicio
        Servicio guardado = servicioService.guardar(servicio);

        // Vinculamos al creador como miembro del servicio
        ServicioUsuario su = new ServicioUsuario();
        su.setServicio(guardado);
        su.setUsuario(creador);
        servicioUsuarioRepository.save(su);

        return ResponseEntity.ok(Map.of(
                "estado", true,
                "mensaje", "Servicio creado correctamente",
                "servicio", guardado));
    }

    // PUT: Actualizar un servicio existente
    @PutMapping("/servicio/actualizar/{id}")
    public Servicio actualizar(@PathVariable Long id, @RequestBody Servicio servicio) {
        servicio.setId(id);
        return servicioService.guardar(servicio);
    }

    // DELETE: Eliminar un servicio por ID
    @DeleteMapping("/servicio/eliminar/{id}")
    public void eliminar(@PathVariable Long id) {
        servicioService.eliminar(id);
    }
}
