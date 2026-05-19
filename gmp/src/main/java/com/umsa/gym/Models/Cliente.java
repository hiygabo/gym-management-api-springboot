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
@Table(name = "CLIENTE") 
public class Cliente {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;

    @Column(name = "nombre", nullable=false, length=100)
    private String nombre;


    @Column(name = "paterno", nullable=false, length=100)
    private String paterno;

    @Column(name = "materno", nullable=false, length=100)
    private String materno;

    @Column(name= "ci", nullable=false, length=100)
    private String ci;

    @Column(name="telefono", nullable=false, length=20)
    private String telefono; // Cambiado a String por seguridad

    @Column(name="correo", nullable=false, length=100)
    private String correo;
    
    @Column(name="estado", nullable=false, length=100)
    private String estado;

    @JsonIgnore
    @OneToMany(mappedBy="cliente")
    private List<Suscripcion> suscripciones;

    @JsonIgnore 
    @OneToMany(mappedBy = "cliente")
    private List<Asistencia> asistencias;
}