package com.umsa.gym.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umsa.gym.Models.RegistroCliente;
@Repository
public interface  RegistroClienteRepository extends JpaRepository<RegistroCliente, Long>{
    boolean existsByidRegistro(Long idRegistro);
}
