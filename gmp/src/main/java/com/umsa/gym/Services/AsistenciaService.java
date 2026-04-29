package com.umsa.gym.Services;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umsa.gym.Models.Asistencia;
import com.umsa.gym.Models.Estudiante;
import com.umsa.gym.Repositories.AsistenciaRepository;
import com.umsa.gym.Repositories.EstudianteRepository;
@Service
public class AsistenciaService {
    @Autowired
    private AsistenciaRepository asistenciaRepositorio;
    @Autowired
    private EstudianteRepository estudianteRepositorio;

    public List<Asistencia> listarAsistencias(){
        return asistenciaRepositorio.findAll();
    }

    public Asistencia crearAsistencia(Asistencia nuevaAsistencia){
        if(nuevaAsistencia.getEstudiante() == null || nuevaAsistencia.getEstudiante().getIdEstudiante() ==  null){
            throw new RuntimeException("sin estudiante asignado");
        }

        Long idEst = nuevaAsistencia.getEstudiante().getIdEstudiante();
        Estudiante estudianteEncontrado = estudianteRepositorio.findById(idEst).orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        nuevaAsistencia.setEstudiante(estudianteEncontrado);
        nuevaAsistencia.setFechaHora(LocalDateTime.now());
        return asistenciaRepositorio.save(nuevaAsistencia);
    }

}
