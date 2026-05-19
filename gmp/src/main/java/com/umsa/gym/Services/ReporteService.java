package com.umsa.gym.Services;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.umsa.gym.Models.Cliente;
import com.umsa.gym.Models.PlanSuscripcion;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
@Service
public class ReporteService {
    public byte[] generarReciboSuscripcion(Cliente cliente, PlanSuscripcion plan){
        try{
            File file = ResourceUtils.getFile("classpath:reports/recibo_suscripcion.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

            Map<String,Object> parametros = new HashMap<>();
            parametros.put("nombreCliente", cliente.getNombre() + " "+ cliente.getPaterno());
            parametros.put("correoCliente", cliente.getCorreo());
            parametros.put("nombrePlan", plan.getNombrePlan());
            parametros.put("telefonoCliente", cliente.getTelefono());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, new JREmptyDataSource());

            return JasperExportManager.exportReportToPdf(jasperPrint);
        }catch(Exception e){
            throw new RuntimeException("Error al generar el PDF de la suscripción: " + e.getMessage());
        }
    }
}
