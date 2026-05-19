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

import com.umsa.gym.Models.Cliente;
import com.umsa.gym.Services.ClienteService;
@RestController
@RequestMapping("/api/Clientes")
@CrossOrigin(origins = "http://localhost:5173")
public class ClienteController {
    @Autowired
    private ClienteService servicio;

    @GetMapping
    public List<Cliente> listarClientes(){
        return servicio.listarClientes();
    }

    @PostMapping
    public Cliente guardar(@RequestBody Cliente cliente){
        return servicio.guardarCliente(cliente);
    }

    @GetMapping("/{id}")
    public Cliente listarPorId(@PathVariable Long id){
        return servicio.getPorId(id);
    }

    @PutMapping("/{id}")
    public Cliente actualizar(@PathVariable("id") Long id, @RequestBody Cliente cliente){
        return servicio.actualizarCliente(id, cliente);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){
        servicio.eliminarCliente(id);
    }
}
