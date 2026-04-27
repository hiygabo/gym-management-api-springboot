package com.umsa.gym.Services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umsa.gym.Models.Estudiante;
import com.umsa.gym.Repositories.EstudianteRepository;

@Service
public class EstudianteService {
    
    @Autowired
    private EstudianteRepository repositorio;

    public List<Estudiante> getEstudiantes(){
        return repositorio.findAll();
    }

    public Estudiante guardarEstudiante(Estudiante nuevoEstudiante){
        if(nuevoEstudiante.getEstado() == null || nuevoEstudiante.getEstado().isEmpty()){
            nuevoEstudiante.setEstado("Activo");
        }
        if(repositorio.existsByCi(nuevoEstudiante.getCi())){
            throw new RuntimeException("Error el estudiante ya existe");
        }

        return repositorio.save(nuevoEstudiante);
    }

    public Estudiante getPorId(Long id){
        return repositorio.findById(id).orElseThrow(() -> new RuntimeException("No existe el estudiante"));
    }

    public Estudiante actualizarEstudiante(Long id, Estudiante datosActualizados){
        Estudiante estudianteExistente = getPorId(id);

        estudianteExistente.setNombre(datosActualizados.getNombre());
        estudianteExistente.setTelefono(datosActualizados.getTelefono());

        return repositorio.save(estudianteExistente);
    }

    public void eliminarEstudiante(Long id){
        Estudiante estudianteExistente = getPorId(id);
        estudianteExistente.setEstado("Inactivo");

        repositorio.save(estudianteExistente);
    }



}
