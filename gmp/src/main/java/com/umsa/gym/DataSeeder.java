package com.umsa.gym; // Ajusta el paquete según dónde lo guardes

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.umsa.gym.Models.Usuario;
import com.umsa.gym.Repositories.UsuarioRepository;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (usuarioRepository.count() == 0) {
            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("123456")); 
            admin.setRol("ADMIN");

            usuarioRepository.save(admin);
            System.out.println(" Se ha creado el usuario administrador supremo: admin / 123456");
        }
    }
}