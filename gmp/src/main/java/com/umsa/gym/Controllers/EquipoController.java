package com.umsa.gym.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umsa.gym.Models.Equipo;
import com.umsa.gym.Services.EquipoService;

@RestController
@RequestMapping("/api/equipo")
public class EquipoController {

    @Autowired
    private EquipoService servicio;

    @GetMapping
    public List<Equipo> getEquipos(){
        return servicio.listarEquipos();
    }

    @PostMapping
    public Equipo crearEquipo(@RequestBody Equipo equipo){
        return servicio.crearEquipo(equipo);
    }

    @PutMapping("/{id}")
    public Equipo actualizarEquipo(@PathVariable("id") Long idMaquina, @RequestBody Equipo equipo){
        return servicio.actualizarEquipo(idMaquina, equipo);
    }

    @DeleteMapping("/{id}")
    public Equipo eliminarEquipo(@PathVariable("id") Long idMaquina){
        return servicio.eliminarEquipo(idMaquina);
    }
}