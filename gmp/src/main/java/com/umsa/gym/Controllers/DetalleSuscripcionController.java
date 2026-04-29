package com.umsa.gym.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umsa.gym.Models.DetalleSuscripcion;
import com.umsa.gym.Repositories.DetalleSuscripcionRepository;

@RestController
@RequestMapping("/api/detalle-suscripcion")
@CrossOrigin(origins = "http://localhost:5173")
public class DetalleSuscripcionController {

    @Autowired
    private DetalleSuscripcionRepository detalleSuscripcionRepository;

    @GetMapping
    public List<DetalleSuscripcion> getAll() {
        return detalleSuscripcionRepository.findAll();
    }
}
