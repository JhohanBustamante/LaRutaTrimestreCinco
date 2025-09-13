package com.EjemploModel.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name= "comunidades")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Comunidad {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Tematica tematica;

    @Column(length = 45)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @Column(name = "id_creador")
    private Integer idCreador;

    public enum Tematica {
        ACTIVIDAD_FÍSICA("Actividad física"),
        COACH_DEPORTIVO("Coach deportivo"),
        NUTRICIÓN("Nutrición");

        private final String value;
        Tematica(String value) { this.value = value; }
        public String getValue() { return value; }
    }

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