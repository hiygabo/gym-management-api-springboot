package com.umsa.gym.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name = "ENTRENADOR")
public class Entrenador {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_entrenador")
    private Long idEntrenador;

    @Column(name="nombre")
    private String nombreEntrenador;

    @Column(name="turno")
    private String turno;
    @Column (name="estado")
    private String estado;
}
