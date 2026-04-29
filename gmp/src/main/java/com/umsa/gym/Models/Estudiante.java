package com.umsa.gym.Models;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name = "ESTUDIANTE")
public class Estudiante {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_estudiante")
    private Long idEstudiante;

    @Column(name = "nombre", nullable=false, length=100)
    private String nombre;

    @Column(name= "ci", nullable=false, length=100)
    private String ci;

    @Column(name="telefono", nullable=false, length=100)
    private Integer telefono;

    @Column(name = "registro_universitario", nullable=false, length=100)
    private String registroUniversitario;

    @Column(name="correo", nullable=false, length=100)
    private String correo;
    
    @Column(name="estado", nullable=false, length=100)
    private String estado;

    @JsonIgnore
    @OneToMany(mappedBy="estudiante")
    private List<Suscripcion> suscripciones;


    @JsonIgnore 
    @OneToMany(mappedBy = "estudiante")
    private List<Asistencia> asistencias;
}
