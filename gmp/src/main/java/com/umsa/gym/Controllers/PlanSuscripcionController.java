package com.umsa.gym.Controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umsa.gym.Models.PlanSuscripcion;
import com.umsa.gym.Services.PlanSuscripcionService;
@RestController
@RequestMapping("/api/planes")
public class PlanSuscripcionController {
    @Autowired
    private PlanSuscripcionService servicio;

    @GetMapping
    public List<PlanSuscripcion> getPlanes(){
        return servicio.listarPlanes();
    }

    @PostMapping
    public PlanSuscripcion crearPlan(@RequestBody PlanSuscripcion planSuscripcion){
        return servicio.crearPlan(planSuscripcion);
    }
    @PutMapping("/{id}")
    public PlanSuscripcion editarPlan(@PathVariable Long idPlan, @RequestBody PlanSuscripcion planSuscripcion){
        return servicio.actualizarPlan(idPlan, planSuscripcion);
    }

}

