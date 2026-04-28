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

import com.umsa.gym.Models.Estudiante;
import com.umsa.gym.Services.EstudianteService;
@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteContoller {
    @Autowired
    private EstudianteService servicio;

    @GetMapping
    public List<Estudiante> listarEstduiantes(){
        return servicio.getEstudiantes();
    }

    @PostMapping
    public Estudiante guardar(@RequestBody Estudiante estudiante){
        return servicio.guardarEstudiante(estudiante);
    }

    @GetMapping("/{id}")
    public Estudiante listarPorId(@PathVariable Long id){
        return servicio.getPorId(id);
    }

    @PutMapping("/{id}")
    public Estudiante actualizar(@PathVariable Long id, @RequestBody Estudiante estudiante){
        return servicio.actualizarEstudiante(id, estudiante);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
        servicio.eliminarEstudiante(id);
    }
}
