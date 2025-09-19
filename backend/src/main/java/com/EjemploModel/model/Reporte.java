package com.EjemploModel.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "comunidad")
@Entity
@Data
public class Reporte {
    @Id
    private Long id;
    @Column
    private LocalDate fecha;
    @Column
    private String categoria;
    @Column
    private String estado;
    @Column
    private Double cantidad;
}
