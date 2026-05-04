package com.umsa.gym.Services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umsa.gym.Models.Entrenador;
import com.umsa.gym.Repositories.EntrenadorRepository;
@Service
public class EntrenadorService {
    @Autowired
    private EntrenadorRepository repositorio;

    public List<Entrenador> listarEntrenadores(){
        return repositorio.findAll();
    }

    public Entrenador crearEntrenador(Entrenador nuevoEntrendador){
        if(repositorio.existsByIdEntrenador(nuevoEntrendador.getIdEntrenador())){
            throw new RuntimeException("Entrendor ya existente");
        }
        nuevoEntrendador.setEstado("Activo");
        return repositorio.save(nuevoEntrendador);
    }

    public Entrenador obtenerPorId(Long idEntrenador){
        return repositorio.findById(idEntrenador).orElseThrow(()-> new RuntimeException("Entrenador no encontrado"));
    }

    public Entrenador actualizarEntrenador(Long idEntrenador, Entrenador datosActualizados){
        Entrenador entrenadorExistente = obtenerPorId(idEntrenador);

        entrenadorExistente.setNombreEntrenador(datosActualizados.getNombreEntrenador());
        entrenadorExistente.setTurno(datosActualizados.getTurno());


        return repositorio.save(entrenadorExistente);
    }
    public void eliminarEntrenador(Long idEntrenador){
        Entrenador entrenadorExistente = obtenerPorId(idEntrenador);

        entrenadorExistente.setEstado("Inactivo");

        repositorio.save(entrenadorExistente);
    }

    
}
