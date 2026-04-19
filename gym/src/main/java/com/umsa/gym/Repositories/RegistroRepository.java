package com.umsa.gym.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umsa.gym.Models.Registro;
@Repository
public interface RegistroRepository extends JpaRepository<Registro, Long> {
    
}
