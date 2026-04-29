package com.umsa.gym.Services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umsa.gym.Models.DetalleSuscripcion;
import com.umsa.gym.Models.Entrenador;
import com.umsa.gym.Models.Suscripcion;
import com.umsa.gym.Repositories.DetalleSuscripcionRepository;
import com.umsa.gym.Repositories.EntrenadorRepository;
import com.umsa.gym.Repositories.SuscripcionRepository;

@Service
public class DetalleSuscripcionService {
    @Autowired
    private DetalleSuscripcionRepository detalleRepositorio;
    @Autowired
    private SuscripcionRepository suscripcionRepositorio;
    @Autowired
    private EntrenadorRepository entrenadorRepositorio;

    public List<DetalleSuscripcion> listarDetalles(){
        return detalleRepositorio.findAll();
    }

    public DetalleSuscripcion crearDetalle(DetalleSuscripcion nuevoDetalle){
        if(nuevoDetalle.getSuscripcion() == null || nuevoDetalle.getSuscripcion().getIdSuscripcion() == null){
            throw new RuntimeException("Sin suscripcion asignada");
        }
        if(nuevoDetalle.getEntrenador() == null || nuevoDetalle.getEntrenador().getIdEntrenador() == null){
            throw new RuntimeException("Sin entrenador asignado");
        }
        Long idSus = nuevoDetalle.getSuscripcion().getIdSuscripcion();
        Suscripcion suscripcionEncontrada = suscripcionRepositorio.findById(idSus)
            .orElseThrow(() -> new RuntimeException("Suscripcion no encontrada"));
        
        Long idEnt = nuevoDetalle.getEntrenador().getIdEntrenador();
        Entrenador entrenadorEncontrado = entrenadorRepositorio.findById(idEnt)
            .orElseThrow(() -> new RuntimeException("Entrenador no encontrado"));

        if(detalleRepositorio.existsBySuscripcionAndEntrenador(suscripcionEncontrada, entrenadorEncontrado)){
            throw new RuntimeException("Error: Este entrenador ya está asignado a esta suscripción.");
        }

        // 4. Asignar y Guardar
        nuevoDetalle.setSuscripcion(suscripcionEncontrada);
        nuevoDetalle.setEntrenador(entrenadorEncontrado);

        return detalleRepositorio.save(nuevoDetalle);
    }
}