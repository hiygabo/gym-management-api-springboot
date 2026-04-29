package com.umsa.gym.Controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umsa.gym.Models.Registro;
import com.umsa.gym.Repositories.RegistroRepository;
@RestController
@RequestMapping("/api/registros")
@CrossOrigin(origins = "http://localhost:5173")
public class RegistroController {
    @Autowired
    private RegistroRepository repositorio;

    @GetMapping
    public List<Registro> getRegistros(){
        return repositorio.findAll();
    }
    
}
