package com.umsa.gym.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umsa.gym.Models.RegistroCliente;
import com.umsa.gym.Repositories.RegistroClienteRepository;
@Service
public class RegistroClienteService {
    @Autowired
    private RegistroClienteRepository repositorio;

    public List<RegistroCliente> listarRegistros(){
        return repositorio.findAll();
    }

    public RegistroCliente guardarRegistro(RegistroCliente nuevoRegistro){
        if(repositorio.existsByidRegistro(nuevoRegistro.getIdRegistro())){
            throw new RuntimeException("Ya existe el registro");
        }
        return repositorio.save(nuevoRegistro);
    }

}
