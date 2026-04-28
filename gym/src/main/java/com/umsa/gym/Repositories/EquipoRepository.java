package com.umsa.gym.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umsa.gym.Models.Equipo;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Long> {
    boolean existsByIdMaquina(Long idMaquina);
}
