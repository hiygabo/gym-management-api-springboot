package com.umsa.gym.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umsa.gym.Models.PlanSuscripcion;
@Repository
public interface PlanSuscripcionRepository extends JpaRepository<PlanSuscripcion, Long> {
    boolean existsByidPlan(Long idPlan);
}
