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
@Table(name="Equipo")
public class Equipo {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_maquina")
    private Long idMaquina;

    @ManyToOne
    @JoinColumn(name="id_categoria", nullable=false)
    private Categoria categoria;

    @Column(name="nombre", nullable=false)
    private String nombre;
    @Column(name="marca", nullable=false)
    private String marca;
    @Column(name="cantidad", nullable=false)
    private Integer cantidad;
}
