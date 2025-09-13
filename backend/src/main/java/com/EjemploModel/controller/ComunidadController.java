package com.EjemploModel.controller;

import com.EjemploModel.model.Comunidad;
import com.EjemploModel.service.ComunidadService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.Map;


@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@Controller

public class ComunidadController {

    private final ComunidadService comunidadService;

    public ComunidadController(ComunidadService comunidadService){
        this.comunidadService= comunidadService;
    }

    @GetMapping("/api/comunidades")
    public List<Comunidad> listarComunidades(){
        return comunidadService.listarTodos();
    }

    @GetMapping("/comunidad/{id}")
    public Comunidad obtenerPorId(@PathVariable Long id){
        return comunidadService.buscarPorId(id);
    }

       @PostMapping("/conunidad/crear")
    public ResponseEntity crear (@RequestBody Comunidad comunidad){

        if (comunidadService.buscarPorNombre(comunidad.getNombre()) != null){
        return ResponseEntity
                .badRequest()
                .body(Map.of(
                    "estado", false,
                    "mensaje", "Ya existe una comunidad con ese nombre"
                ));
        }
                Comunidad guardada= comunidadService.guardar(comunidad);

                return ResponseEntity
                        .ok(Map.of(
                            "estado", true,
                            "mensaje", "Comunidad creada correctamente",
                            "comunidad", guardada
                            ));
                    
    }
    
    @PutMapping("/{id}")
    public Comunidad actualizar(@PathVariable Long id, @RequestBody Comunidad comunidad){
        comunidad.setId(id);
        return comunidadService.guardar(comunidad);
    }
    
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        comunidadService.eliminar(id);
    }
}