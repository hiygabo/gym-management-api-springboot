package com.umsa.gym.Models;
import java.time.LocalDateTime;

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
@Table(name = "Asistencia")
public class Asistencia {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_asistencia", nullable=false)
    private Long idAsistencia;

    @ManyToOne
    @JoinColumn(name="id_estudiante", nullable=false)
    private Estudiante estudiante;

    @Column(name="fecha_hora", nullable=false)
    private LocalDateTime fechaHora;

}
