package com.umsa.gym.Models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name="Suscripcion")
public class Suscripcion {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_suscripcion")
    private Long idSuscripcion;

    @ManyToOne
    @JoinColumn(name = "id_estudiante", nullable=false)
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "id_plan", nullable=false)
    private PlanSuscripcion planSuscripcion;

    @Column(name="fecha_inicio", nullable=false)
    private LocalDate fechaInicio;

    
    @Column(name="fecha_fin", nullable=false)
    private LocalDate fechaFin;
    

}
