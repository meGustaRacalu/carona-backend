package com.generation.carona.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.carona.model.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    
    List<Veiculo> findAllByModeloContainingIgnoreCase(@Param("modelo") String modelo);
    
}