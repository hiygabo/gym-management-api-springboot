package com.umsa.gym.Tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.umsa.gym.Repositories.ClienteRepository;

import jakarta.transaction.Transactional;
@Component
public class MantenimientoScheduler {
    @Autowired
    private ClienteRepository repositorioCliente;

    @Scheduled(cron = "0 * * * * ?")
    @Transactional
    public void desactivarDedudores(){
        try{
            repositorioCliente.ejecutarProcDeudores();
            System.out.println("Ejecutando procedimiento");
        }catch(Exception e){
            System.err.println("Error al ejecutar revision" + e.getMessage());
        }
    }

}
