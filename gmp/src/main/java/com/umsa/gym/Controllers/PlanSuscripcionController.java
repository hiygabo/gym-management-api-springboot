package com.umsa.gym.Controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umsa.gym.Models.PlanSuscripcion;
import com.umsa.gym.Repositories.PlanSuscripcionRepository;
@RestController
@RequestMapping("/api/planes")
public class PlanSuscripcionController {
    @Autowired
    private PlanSuscripcionRepository repositorio;

    @GetMapping
    public List<PlanSuscripcion> getPlanes(){
        return repositorio.findAll();
    }
}

