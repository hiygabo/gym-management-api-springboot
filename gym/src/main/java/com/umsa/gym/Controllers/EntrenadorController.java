package com.umsa.gym.Controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umsa.gym.Models.Entrenador;
import com.umsa.gym.Repositories.EntrenadorRepository;
@RestController
@RequestMapping("/api/Entrenadores")
public class EntrenadorController {
    @Autowired
    private EntrenadorRepository repositorio; 

    @GetMapping
    public List<Entrenador> getEntrenadores(){
        return repositorio.findAll();
    }
}
