package com.umsa.gym.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umsa.gym.Models.Cliente;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    boolean existsByCi(String ci);
    Optional<Cliente> findByCi(String ci);
}
