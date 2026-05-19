package com.umsa.gym.Services;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umsa.gym.Models.Asistencia;
import com.umsa.gym.Models.Cliente;
import com.umsa.gym.Repositories.AsistenciaRepository;
import com.umsa.gym.Repositories.ClienteRepository;
@Service
public class AsistenciaService {
    @Autowired
    private AsistenciaRepository asistenciaRepositorio;
    @Autowired
    private ClienteRepository clienteRepositorio;

    public List<Asistencia> listarAsistencias(){
        return asistenciaRepositorio.findAll();
    }

    public Asistencia crearAsistencia(Asistencia nuevaAsistencia){
        if(nuevaAsistencia.getCliente() == null || nuevaAsistencia.getCliente().getIdCliente() ==  null){
            throw new RuntimeException("sin cliente asignado");
        }

        Long idCli = nuevaAsistencia.getCliente().getIdCliente();
        Cliente clienteEncontrado = clienteRepositorio.findById(idCli).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        nuevaAsistencia.setCliente(clienteEncontrado);
        nuevaAsistencia.setFechaHora(LocalDateTime.now());
        return asistenciaRepositorio.save(nuevaAsistencia);
    }

}
