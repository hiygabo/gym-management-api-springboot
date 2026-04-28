package com.umsa.gym.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umsa.gym.Models.DetalleSuscripcion;

@Repository
public interface DetalleSuscripcionRepository extends JpaRepository<DetalleSuscripcion, Long> {
}
