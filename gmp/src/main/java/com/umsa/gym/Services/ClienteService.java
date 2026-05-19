package com.umsa.gym.Services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umsa.gym.Models.Cliente;
import com.umsa.gym.Repositories.ClienteRepository;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository repositorio;

    public List<Cliente> listarClientes(){
        return repositorio.findAll();
    }

    public Cliente guardarCliente(Cliente nuevoCliente){
        if(nuevoCliente.getEstado() == null || nuevoCliente.getEstado().isEmpty()){
            nuevoCliente.setEstado("Activo");
        }
        if(repositorio.existsByCi(nuevoCliente.getCi())){
            throw new RuntimeException("Error el Cliente ya existe");
        }

        return repositorio.save(nuevoCliente);
    }

    public Cliente getPorId(Long id){
        return repositorio.findById(id).orElseThrow(() -> new RuntimeException("No existe el Cliente"));
    }

    public Cliente actualizarCliente(Long id, Cliente datosActualizados){
        Cliente ClienteExistente = getPorId(id);

        ClienteExistente.setNombre(datosActualizados.getNombre());
        ClienteExistente.setTelefono(datosActualizados.getTelefono());

        return repositorio.save(ClienteExistente);
    }

    public void eliminarCliente(Long id){
        Cliente ClienteExistente = getPorId(id);
        ClienteExistente.setEstado("Inactivo");

        repositorio.save(ClienteExistente);
    }
}
