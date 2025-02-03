package com.generation.carona.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.carona.model.Viagem;

public interface ViagemRepository extends JpaRepository<Viagem, Long> {

    List<Viagem> findAllByDestinoContainingIgnoreCase(@Param("destino") String destino);
}

