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
@Table(name="REGISTROCLIENTE")
public class RegistroCliente {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_registro")
    private Long idRegistro;

    @Column(name="nombre_cliente")
    private String nombreCliente;
    @Column(name="paterno_cliente")
    private String paternoCliente;
    @Column(name="materno_cliente")
    private String maternoCliente;
    @Column(name="correo")
    private String correoCliente;
    @Column(name="telefono")
    private String telefonoCliente;
    @Column(name="comprobante") 
    private String comprobante;
    @ManyToOne
    @JoinColumn(name="id_plan", nullable=false)
    private PlanSuscripcion plan;
}
