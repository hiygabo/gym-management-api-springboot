package com.umsa.gym.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umsa.gym.Models.Entrenador;

@Repository
public interface  EntrenadorRepository extends JpaRepository<Entrenador, Long>{
    
}
