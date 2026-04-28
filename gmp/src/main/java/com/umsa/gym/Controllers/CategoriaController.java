package com.umsa.gym.Controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umsa.gym.Models.Categoria;
import com.umsa.gym.Services.CategoriaService;
@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService servicio;

    @GetMapping
    public List<Categoria> getCategorias(){
        return servicio.listarCategorias();
    }
    @PostMapping
    public Categoria crearCategoria(@RequestBody Categoria categoria){
        return servicio.crearCategoria(categoria);
    }
    @PutMapping("/{id}")
    public Categoria actualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoria){
        return servicio.actualizar(id, categoria);
    }
}
