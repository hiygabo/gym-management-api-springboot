package com.umsa.gym.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umsa.gym.Models.RegistroCliente;
import com.umsa.gym.Services.RegistroClienteService;
@RestController
@RequestMapping("/api/registro-cliente")
@CrossOrigin(origins = "http://localhost:5173")
public class RegistroClienteController {
    @Autowired
    private RegistroClienteService servicio;

    @GetMapping
    public List<RegistroCliente> listarRegistros(){
        return servicio.listarRegistros();
    }

    @PostMapping
    public RegistroCliente crearRegistro(@RequestBody RegistroCliente RegistroCliente){
        return servicio.guardarRegistro(RegistroCliente);
    }
}
