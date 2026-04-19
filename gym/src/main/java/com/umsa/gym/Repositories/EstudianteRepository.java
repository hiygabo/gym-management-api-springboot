package com.umsa.gym.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umsa.gym.Models.Estudiante;
@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long>{
    
}
