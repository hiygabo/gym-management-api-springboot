package com.umsa.gym.Models;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Estudiante")
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
}
