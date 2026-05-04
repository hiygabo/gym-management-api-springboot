package com.umsa.gym.Controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umsa.gym.Models.Entrenador;
import com.umsa.gym.Services.EntrenadorService;
@RestController
@RequestMapping("/api/Entrenadores")
@CrossOrigin(origins = "http://localhost:5173")
public class EntrenadorController {
    @Autowired
    private EntrenadorService servicio; 

    @GetMapping
    public List<Entrenador> getEntrenadores(){
        return servicio.listarEntrenadores();
    }
    @PostMapping
    public Entrenador crearEntrenador(@RequestBody Entrenador entrenador){
        return servicio.crearEntrenador(entrenador);
    }
    @PutMapping("/{idEntrenador}")
    public Entrenador actualizarEntrenador(@PathVariable Long idEntrenador, @RequestBody Entrenador entrenador){
        return servicio.actualizarEntrenador(idEntrenador, entrenador);
    }
    @DeleteMapping("/{idEntrenador}")
    public void eliminarEntrenador(@PathVariable Long idEntrenador){
        servicio.eliminarEntrenador(idEntrenador);
    }

    
}
