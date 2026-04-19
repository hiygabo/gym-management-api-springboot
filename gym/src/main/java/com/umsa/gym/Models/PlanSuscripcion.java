package com.umsa.gym.Models;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="PlanSuscripcion")
public class PlanSuscripcion {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_plan")
    private Long idPlan;

    @Column(name="nombre")
    private String nombrePlan;
}
