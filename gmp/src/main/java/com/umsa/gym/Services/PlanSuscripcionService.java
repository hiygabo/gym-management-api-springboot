package com.umsa.gym.Services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umsa.gym.Models.PlanSuscripcion;
import com.umsa.gym.Repositories.PlanSuscripcionRepository;
@Service
public class PlanSuscripcionService {
    @Autowired
    private PlanSuscripcionRepository repositorio;

    public List<PlanSuscripcion> listarPlanes(){
        return repositorio.findAll();
    }

    public PlanSuscripcion crearPlan(PlanSuscripcion nuevoPlan){
        if(repositorio.existsByidPlan(nuevoPlan.getIdPlan())){
            throw new RuntimeException("Plan ya existente");
        }
        return repositorio.save(nuevoPlan);
    }
    public PlanSuscripcion obtenerPorId(Long idPlan){
        return repositorio.findById(idPlan).orElseThrow(() -> new RuntimeException("Plan inexistente"));
    }

    public PlanSuscripcion actualizarPlan(Long idPlan, PlanSuscripcion datosActualizados){
        PlanSuscripcion planExistente = obtenerPorId(idPlan);
        planExistente.setNombrePlan(datosActualizados.getNombrePlan());

        return repositorio.save(planExistente);
    }
}
