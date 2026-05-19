package com.umsa.gym.Services;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umsa.gym.Models.Cliente;
import com.umsa.gym.Models.PlanSuscripcion;
import com.umsa.gym.Models.Suscripcion;
import com.umsa.gym.Repositories.ClienteRepository;
import com.umsa.gym.Repositories.PlanSuscripcionRepository;
import com.umsa.gym.Repositories.SuscripcionRepository;
@Service
public class SuscripcionService {
    @Autowired
    private SuscripcionRepository suscripcionRepositorio;
    @Autowired
    private ClienteRepository clienteRepositorio;
    @Autowired
    private PlanSuscripcionRepository planRepositorio;


    public List<Suscripcion> listarSuscripciones(){
        return suscripcionRepositorio.findAll();
    }

    public Suscripcion crearSuscripcion(Suscripcion nuevaSuscripcion){
        if(nuevaSuscripcion.getCliente() == null || nuevaSuscripcion.getCliente().getIdCliente() == null){
            throw new RuntimeException("Suscripcion sin cliente");
        }
        if(nuevaSuscripcion.getPlanSuscripcion() == null || nuevaSuscripcion.getPlanSuscripcion().getIdPlan() == null){
            throw new RuntimeException("Suscripcion sin plan");
        }

        Long idCli = nuevaSuscripcion.getCliente().getIdCliente();
        Cliente nuevoCliente = clienteRepositorio.findById(idCli).orElseThrow(() -> new RuntimeException("Cliente inexistente"));
        Long idPlan = nuevaSuscripcion.getPlanSuscripcion().getIdPlan();
        PlanSuscripcion nuevoPlanSuscripcion = planRepositorio.findById(idPlan).orElseThrow(() -> new RuntimeException("Plan no existente"));

        nuevaSuscripcion.setCliente(nuevoCliente);
        nuevaSuscripcion.setPlanSuscripcion(nuevoPlanSuscripcion);
        LocalDate hoy = LocalDate.now();
        nuevaSuscripcion.setFechaInicio(hoy);
        int añoActual = hoy.getYear();
        LocalDate finDeGestion = LocalDate.of(añoActual, 12, 31);
        nuevaSuscripcion.setFechaFin(finDeGestion);
        return suscripcionRepositorio.save(nuevaSuscripcion);
    }
}
