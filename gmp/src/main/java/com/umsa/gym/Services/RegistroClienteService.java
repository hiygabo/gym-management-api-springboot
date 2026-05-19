package com.umsa.gym.Services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umsa.gym.Models.Cliente;
import com.umsa.gym.Models.DetalleSuscripcion;
import com.umsa.gym.Models.RegistroCliente;
import com.umsa.gym.Models.Suscripcion;
import com.umsa.gym.Repositories.ClienteRepository;
import com.umsa.gym.Repositories.DetalleSuscripcionRepository;
import com.umsa.gym.Repositories.RegistroClienteRepository;
import com.umsa.gym.Repositories.SuscripcionRepository;

import jakarta.transaction.Transactional;
@Service
public class RegistroClienteService {
    @Autowired
    private RegistroClienteRepository repositorio;
    @Autowired
    private SuscripcionRepository repositorioSuscripcion;
    @Autowired
    private DetalleSuscripcionRepository repositorioDetalle;
    @Autowired
    private ClienteRepository repositorioCliente;
    @Autowired
    private EmailService emailService;
    @Autowired
    private ReporteService reporteService;

    public List<RegistroCliente> listarRegistros(){
        return repositorio.findAll();
    }

    public RegistroCliente guardarRegistro(RegistroCliente nuevoRegistro){
        if(nuevoRegistro.getIdRegistro() != null && repositorio.existsById(nuevoRegistro.getIdRegistro())){
            throw new RuntimeException("Ya existe el registro");
        }
        return repositorio.save(nuevoRegistro);
    }
    @Transactional
    public void aprobarRegistro (Long idRegistro){
        RegistroCliente registro = repositorio.findById(idRegistro)
        .orElseThrow(() -> new RuntimeException("Registro no encontrado"));

        // Validaciones claras para evitar errores en runtime
        if(registro.getPlan() == null || registro.getPlan().getIdPlan() == null){
            throw new RuntimeException("Registro sin plan asignado: no se puede aprobar");
        }
        if(registro.getCorreoCliente() == null || registro.getCorreoCliente().isBlank()){
            throw new RuntimeException("Registro sin correo: no se puede notificar al cliente");
        }

        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setNombre(registro.getNombreCliente());
        nuevoCliente.setPaterno(registro.getPaternoCliente());
        nuevoCliente.setMaterno(registro.getMaternoCliente());
        nuevoCliente.setCorreo(registro.getCorreoCliente());
        nuevoCliente.setTelefono(registro.getTelefonoCliente());
        nuevoCliente.setCi("SIN_CI");
        nuevoCliente.setEstado("Activo");
        Cliente clienteGuardado = repositorioCliente.save(nuevoCliente);

        Suscripcion nuevaSuscipcion = new Suscripcion();
        nuevaSuscipcion.setCliente(clienteGuardado);
        nuevaSuscipcion.setPlanSuscripcion(registro.getPlan());
        LocalDate hoy = LocalDate.now();
        nuevaSuscipcion.setFechaInicio(hoy);
        int añoActual = hoy.getYear();
        LocalDate finDeGestion = LocalDate.of(añoActual, 12, 31);
        nuevaSuscipcion.setFechaFin(finDeGestion);

        Suscripcion suscripcionGuardada = repositorioSuscripcion.save(nuevaSuscipcion);

        DetalleSuscripcion nuevoDetalle = new DetalleSuscripcion();
        nuevoDetalle.setSuscripcion(suscripcionGuardada);
        repositorioDetalle.save(nuevoDetalle);
        byte[] pdfGenerado = reporteService.generarReciboSuscripcion(clienteGuardado, registro.getPlan());

        try {
            emailService.enviarCorreoAprobacion(clienteGuardado.getCorreo(), clienteGuardado.getNombre(), pdfGenerado);
        } catch (Exception e) {
            System.err.println("Advertencia: El cliente se guardó, pero el correo falló: " + e.getMessage());
        }
        repositorio.delete(registro);
    }
    @Transactional
    public void rechazarRegistro(Long idRegistro){
        RegistroCliente registro = repositorio.findById(idRegistro)
        .orElseThrow(()-> new RuntimeException("Registro no encontrado"));

        emailService.enviarCorreoRechazo(registro.getCorreoCliente(), registro.getNombreCliente());

        repositorio.delete(registro);

    }

}
