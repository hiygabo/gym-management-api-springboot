package com.umsa.gym.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umsa.gym.Models.DetalleSuscripcion;
import com.umsa.gym.Models.Entrenador;
import com.umsa.gym.Models.Suscripcion;

@Repository
public interface DetalleSuscripcionRepository extends JpaRepository<DetalleSuscripcion, Long> {
    boolean existsBySuscripcionAndEntrenador(Suscripcion suscripcion, Entrenador entrenador);
}
