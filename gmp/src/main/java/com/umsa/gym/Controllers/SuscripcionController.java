package com.umsa.gym.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umsa.gym.Models.Suscripcion;
import com.umsa.gym.Repositories.SuscripcionRepository;

@RestController
@RequestMapping("/api/suscripcion")
public class SuscripcionController {

    @Autowired
    private SuscripcionRepository suscripcionRepository;

    @GetMapping
    public List<Suscripcion> getAll() {
        return suscripcionRepository.findAll();
    }
}
