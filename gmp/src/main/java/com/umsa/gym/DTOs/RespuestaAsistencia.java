package com.umsa.gym.DTOs;

import lombok.Data;


@Data
public class RespuestaAsistencia {
    private boolean exito;
    private String mensaje;
    private String nombreCliente;
    private String nombrePlan;
    private long diasRestantes;

    
}
