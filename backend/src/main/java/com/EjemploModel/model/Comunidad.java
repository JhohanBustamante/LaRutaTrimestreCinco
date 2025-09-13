package com.EjemploModel.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name= "comunidad")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Comunidad {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Column(nullable = false)
    private String tematica;

    @Column(length = 45)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    
    private Tipo tipo;

    @Column(name = "id_creador")
    private Integer idCreador;

    public enum Tipo {
        CHAT_GRUPAL("Chat Grupal"),
        GRUPO("Grupo"),
        COMUNIDAD("Comunidad"),
        GRAN_COMUNIDAD("Gran Comunidad");

        private final String value;
        Tipo(String value) { this.value = value; }
        public String getValue() { return value; }
    }

    }