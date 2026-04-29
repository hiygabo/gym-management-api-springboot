package com.umsa.gym.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umsa.gym.Models.Asistencia;
import com.umsa.gym.Services.AsistenciaService;

@RestController
@RequestMapping("/api/asistencia")
@CrossOrigin(origins = "http://localhost:5173")
public class AsistenciaController {

    @Autowired
    private AsistenciaService servicio;

    @GetMapping
    public List<Asistencia> getAsistencias() {
        return servicio.listarAsistencias();
    }

    @PostMapping
    public Asistencia registrarAsistencia(Asistencia asistencia){
        return servicio.crearAsistencia(asistencia);
    }
}
