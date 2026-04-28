package com.umsa.gym.Services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.umsa.gym.Models.Categoria;
import com.umsa.gym.Models.Equipo;
import com.umsa.gym.Repositories.CategoriaRepository;
import com.umsa.gym.Repositories.EquipoRepository;

public class EquipoService {
    @Autowired
    private EquipoRepository equipoRepositorio;
    @Autowired
    private CategoriaRepository categoriaRepositorio;

    public List<Equipo> listarEquipos(){
        return equipoRepositorio.findAll();
    }

    public Equipo crearEquipo(Equipo nuevoEquipo){
        if(nuevoEquipo.getCategoria() == null || nuevoEquipo.getCategoria().getIdCategoria() == null){
            throw new RuntimeException("No tiene categoria asignada");
        }
        Long idCat = nuevoEquipo.getCategoria().getIdCategoria();
        Categoria categoriaEncontrada = categoriaRepositorio.findById(idCat).orElseThrow(()-> new RuntimeException("Categoria no existente"));
        nuevoEquipo.setCategoria(categoriaEncontrada);
        nuevoEquipo.setEstado("Activo");
        return equipoRepositorio.save(nuevoEquipo);

    }

    public Equipo obtenerPorId(Long idMaquina){
        return equipoRepositorio.findById(idMaquina).orElseThrow(() -> new RuntimeException("Maquina no encontrada"));
    }


}
