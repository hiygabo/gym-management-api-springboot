package com.umsa.gym.Controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umsa.gym.Models.Categoria;
import com.umsa.gym.Repositories.CategoriaRepository;
@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaRepository repositorio;

    @GetMapping
    public List<Categoria> getCategorias(){
        return repositorio.findAll();
    }
}
