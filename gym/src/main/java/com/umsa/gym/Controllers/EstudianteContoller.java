package com.umsa.gym.Controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umsa.gym.Models.Estudiante;
import com.umsa.gym.Repositories.EstudianteRepository;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteContoller {
    @Autowired
    private EstudianteRepository repositorio;

    @GetMapping
    public List<Estudiante> getEstudiantes(){
        return repositorio.findAll();
    }
}
