package com.EjemploModel.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    @Column(unique = true)
    private String correo;
    private String contrasena;
    @Column(unique = true)
    private String apodo;
    private double altura;
    private String localidad;
    private String sexo;
    private String nacimiento;
    private String nombre_2;
    private String apellido_2;
    private String rol;
}