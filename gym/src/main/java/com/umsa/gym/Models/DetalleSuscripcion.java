package com.umsa.gym.Models;
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
@Table(name="DetalleSuscripcion")
public class DetalleSuscripcion {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_detallle", nullable=false)
    private Long idDetalle;

    @ManyToOne
    @JoinColumn(name="id_suscripcion", nullable=false)
    private Suscripcion suscripcion;

    @ManyToOne
    @JoinColumn(name="id_entrenador", nullable=false)
    private Entrenador entrenador;
}
