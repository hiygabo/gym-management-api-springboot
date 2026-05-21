package com.umsa.gym.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umsa.gym.DTOs.RespuestaAsistencia;
import com.umsa.gym.Services.AsistenciaService;

@RestController
@RequestMapping("/api/asistencia")
@CrossOrigin(origins = "http://localhost:5173")
public class AsistenciaController {

    @Autowired
    private AsistenciaService servicio;

    @PostMapping("/marcar")
    public RespuestaAsistencia marcarAsistencia(@RequestBody String ci) {
        String ciLimpio = ci.replace("\"", "").trim(); 
        return servicio.registrarAsistencia(ciLimpio);
    }

}
