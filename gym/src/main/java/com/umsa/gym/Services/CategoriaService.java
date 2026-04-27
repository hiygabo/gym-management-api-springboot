package com.umsa.gym.Services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umsa.gym.Models.Categoria;
import com.umsa.gym.Repositories.CategoriaRepository;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository repositorio;

    public List<Categoria> listarCategorias(){
        return repositorio.findAll();
    }

    public Categoria obtenerPorId(Long idCategoria){
        return repositorio.findById(idCategoria).orElseThrow(() -> new RuntimeException("Categoria Inexistente"));
    }   

    public Categoria actualizar(Long idCategoria, Categoria datosActualizados){
        Categoria categoriaExistente = obtenerPorId(idCategoria);

        categoriaExistente.setNombreCategoria(datosActualizados.getNombreCategoria());

        return repositorio.save(categoriaExistente);
    }

    public Categoria crearCategoria(Categoria nuevaCategoria){
        return repositorio.save(nuevaCategoria);
    }

    

    
}
