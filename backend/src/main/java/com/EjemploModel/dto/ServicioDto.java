package com.EjemploModel.dto;

import java.time.LocalDate;

import com.EjemploModel.model.Servicio;
import com.EjemploModel.model.Servicio.Categoria; // Importa el enum

public record ServicioDto(
    Long id,
    String nombre,
    String descripcion,
    Servicio.Categoria categoria,
    String estado,
    LocalDate fecha,
    Long idComunidad,  
    Long idCreador

) {}
