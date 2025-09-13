package com.EjemploModel.controller;

import com.EjemploModel.model.Comunidad;
import com.EjemploModel.service.ComunidadService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comunidades")
@CrossOrigin(origins = "http://localhost:4200")

public class ComunidadController {

    private final ComunidadService comunidadService;

    public ComunidadController(ComunidadService comunidadService){
        this.comunidadService= comunidadService;
    }

    @GetMapping
    public List<Comunidad> listarComunidades(){
        return comunidadService.listarTodos();
    }

    @GetMapping("/{id}")
    public Comunidad obtenerPorId(@PathVariable Long id){
        return comunidadService.buscarPorId(id);
    }

    @PostMapping
    public Comunidad crear (@RequestBody Comunidad comunidad){
        return comunidadService.guardar(comunidad);
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
