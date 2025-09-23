package com.EjemploModel.dto;

import java.time.LocalDate;


public record ServicioDto(
    Long id,
    String nombre,
    String descripcion,
    String categoria,
    String estado,
    LocalDate fecha
) {}
