package com.EjemploModel.model;

import java.time.LocalDate;
import java.util.List;

import com.EjemploModel.model.Comunidad.Tipo;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@Table(name = "servicio")
@NoArgsConstructor


public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idCreador;
    private String nombre;
    private String descripcion;
    @ManyToOne
@JoinColumn(name = "comunidad_id")
private Comunidad comunidad;


    @OneToMany(mappedBy = "servicio", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ServicioUsuario> usuariosService;

    @ManyToMany
    @JsonIgnore
    private List<Usuario> usuarios;

@Column(nullable = false)
@Enumerated(EnumType.STRING)
private Categoria categoria;

public enum Categoria {
    NUTRICION("Nutrición"),
    ENTRENAMIENTO("Entrenamiento"),
    SALUD_MENTAL("Salud Mental"),
    BIENESTAR("Bienestar General"),
    PRODUCTIVIDAD("Productividad"),
    DESARROLLO_PERSONAL("Desarrollo Personal"),
    MOTIVACION("Motivación");

    private final String value;

    Categoria(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

           private String estado;
    private LocalDate fecha;
}
