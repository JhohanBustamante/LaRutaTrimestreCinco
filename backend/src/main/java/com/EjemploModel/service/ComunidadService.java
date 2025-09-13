package com.EjemploModel.service;

import com.EjemploModel.model.Comunidad;
import com.EjemploModel.repository.ComunidadRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ComunidadService {
    private final ComunidadRepository comunidadRepository;

    public ComunidadService(ComunidadRepository comunidadRepository){
        this.comunidadRepository = comunidadRepository;
    }

    public List<Comunidad> listarTodos(){
        return comunidadRepository.findAll();
    }

    public Comunidad guardar(Comunidad comunidad){
        return comunidadRepository.save(comunidad);
    }

    public Comunidad buscarPorNombre(String nombre){
        return comunidadRepository.findByNombre(nombre).orElse(null);
    }

    public Comunidad buscarPorId(Long id){
        return comunidadRepository.findById(id).orElse(null);
    }

    public void eliminar (Long id){
        comunidadRepository.deleteById(id);
    }

}
