package com.generation.carona.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.carona.model.Veiculo;


@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    
    List<Veiculo> findAllByModeloContainingIgnoreCase(@Param("modelo") String modelo);
    
}