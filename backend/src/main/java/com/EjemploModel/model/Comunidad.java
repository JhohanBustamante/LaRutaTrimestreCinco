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
    @Column( unique= true, nullable= false)
    private String nombre;
    @Column (unique= false, nullable= false)
    private String descripcion;
    @Column (unique= false, nullable= false)
    private String especialidad;
    @Column (unique= false, nullable= false)
    private String estado;
}