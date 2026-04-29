package com.umsa.gym.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umsa.gym.Models.Suscripcion;
import com.umsa.gym.Services.SuscripcionService;

@RestController
@RequestMapping("/api/suscripcion")
public class SuscripcionController {

    @Autowired
    private SuscripcionService servicio;

    @GetMapping
    public List<Suscripcion> getAll() {
        return servicio.listarSuscripciones();
    }
    @PostMapping
    public Suscripcion crearSuscripcion(Suscripcion suscripcion){
        return servicio.crearSuscripcion(suscripcion);
    }
}
