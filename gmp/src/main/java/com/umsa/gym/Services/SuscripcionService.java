package com.umsa.gym.Services;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umsa.gym.Models.Estudiante;
import com.umsa.gym.Models.PlanSuscripcion;
import com.umsa.gym.Models.Suscripcion;
import com.umsa.gym.Repositories.EstudianteRepository;
import com.umsa.gym.Repositories.PlanSuscripcionRepository;
import com.umsa.gym.Repositories.SuscripcionRepository;
@Service
public class SuscripcionService {
    @Autowired
    private SuscripcionRepository suscripcionRepositorio;
    @Autowired
    private EstudianteRepository estudianteRepositorio;
    @Autowired
    private PlanSuscripcionRepository planRepositorio;


    public List<Suscripcion> listarSuscripciones(){
        return suscripcionRepositorio.findAll();
    }

    public Suscripcion crearSuscripcion(Suscripcion nuevaSuscripcion){
        if(nuevaSuscripcion.getEstudiante() == null || nuevaSuscripcion.getEstudiante().getIdEstudiante() == null){
            throw new RuntimeException("Suscripcion sin estudiante");
        }
        if(nuevaSuscripcion.getPlanSuscripcion() == null || nuevaSuscripcion.getPlanSuscripcion().getIdPlan() == null){
            throw new RuntimeException("Suscripcion sin plan");
        }

        Long idEst = nuevaSuscripcion.getEstudiante().getIdEstudiante();
        Estudiante nuevoEstudiante = estudianteRepositorio.findById(idEst).orElseThrow(() -> new RuntimeException("Estudiante inexistente"));
        Long idPlan = nuevaSuscripcion.getPlanSuscripcion().getIdPlan();
        PlanSuscripcion nuevoPlanSuscripcion = planRepositorio.findById(idPlan).orElseThrow(() -> new RuntimeException("Plan no existente"));

        nuevaSuscripcion.setEstudiante(nuevoEstudiante);
        nuevaSuscripcion.setPlanSuscripcion(nuevoPlanSuscripcion);
        LocalDate hoy = LocalDate.now();
        nuevaSuscripcion.setFechaInicio(hoy);
        int añoActual = hoy.getYear();
        LocalDate finDeGestion = LocalDate.of(añoActual, 12, 31);
        nuevaSuscripcion.setFechaFin(finDeGestion);
        return suscripcionRepositorio.save(nuevaSuscripcion);
    }
}
