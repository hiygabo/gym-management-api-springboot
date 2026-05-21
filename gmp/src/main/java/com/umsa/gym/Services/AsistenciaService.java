package com.umsa.gym.Services;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umsa.gym.DTOs.RespuestaAsistencia;
import com.umsa.gym.Models.Asistencia;
import com.umsa.gym.Models.Cliente;
import com.umsa.gym.Models.Suscripcion;
import com.umsa.gym.Repositories.AsistenciaRepository;
import com.umsa.gym.Repositories.ClienteRepository;
import com.umsa.gym.Repositories.SuscripcionRepository;
@Service
public class AsistenciaService {
    @Autowired
    private AsistenciaRepository asistenciaRepositorio;
    @Autowired
    private ClienteRepository clienteRepositorio;

    @Autowired
    private SuscripcionRepository suscripcionRepositorio;

    public RespuestaAsistencia registrarAsistencia(String ci){
        RespuestaAsistencia respuesta = new RespuestaAsistencia();
        Optional<Cliente> clienteOpt = clienteRepositorio.findByCi(ci);
        if(clienteOpt.isEmpty()){
            respuesta.setExito(false);
            respuesta.setMensaje("Cliente no registrado en el sistema");
            return respuesta;

        }
        Cliente cliente = clienteOpt.get();

        Suscripcion sub = suscripcionRepositorio.findFirstByClienteOrderByFechaFinDesc(cliente);
        if (sub == null){
            respuesta.setExito(false);
            respuesta.setMensaje("El cliente no tiene ninguna suscripcion");
            return respuesta;
        }

        LocalDate hoy = LocalDate.now();
        long diasRestantes = ChronoUnit.DAYS.between(hoy, sub.getFechaFin());

        if (diasRestantes < 0){
            respuesta.setExito(false);
            respuesta.setMensaje("Suscripcion vencida, vencio hace: " + Math.abs(diasRestantes) + "dias.");
            respuesta.setNombreCliente(cliente.getNombre());

            return respuesta;
        }

        Asistencia nuevaAsistencia = new Asistencia();
        nuevaAsistencia.setCliente(cliente);
        nuevaAsistencia.setFechaHora(LocalDateTime.now());
        asistenciaRepositorio.save(nuevaAsistencia);

        respuesta.setExito(true);
        respuesta.setMensaje(" ¡Acceso Permitido!");
        respuesta.setNombreCliente(cliente.getNombre() + " " + cliente.getPaterno());
        respuesta.setNombrePlan(sub.getPlanSuscripcion().getNombrePlan());
        respuesta.setDiasRestantes(diasRestantes);

        return respuesta;
    }
}
