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
@Table(name = "REGISTRO")
public class Registro {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_registro")
    private Long idRegistro;

    @Column(name="nombre_estudiante")
    private String nombreEstudiante;

    @Column(name="registro_universitario")
    private String registroUniversitario;

    @Column(name="seguro_medico")
    private String registroMedico;

    @Column(name="correo_institucional")
    private String correoInstitucional;

    @Column(name="estado_solicitud")
    private String estadoSolucitud;
}
