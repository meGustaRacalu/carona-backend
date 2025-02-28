package com.generation.carona.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.generation.carona.model.Viagem;
import com.generation.carona.repository.VeiculoRepository;
import com.generation.carona.repository.ViagemRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/viagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ViagemController {

    @Autowired
    private ViagemRepository viagemRepository;

    @Autowired 
    private VeiculoRepository veiculoRepository;
    
    @GetMapping
    public ResponseEntity<List<Viagem>> getAll() {
        try {
            List<Viagem> viagens = viagemRepository.findAll();
            if (viagens.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(viagens);
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        	return null;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Viagem> getById(@PathVariable Long id) {
        try {
            return viagemRepository.findById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/destino/{destino}")
    public ResponseEntity<List<Viagem>> getByDestino(@PathVariable String destino) {
        try {
            List<Viagem> viagens = viagemRepository.findAllByDestinoContainingIgnoreCase(destino);
            if (viagens.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.ok(viagens);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Viagem> post(@Valid @RequestBody Viagem viagem) {
        try {
        	Float preco = (viagem.getDistancia() / 
        			veiculoRepository.findById(viagem.getVeiculo().getId()).get().getVelocidadeMedia()) * 50;
        	
        	BigDecimal bd = new BigDecimal(Float.toString(preco));
        	bd = bd.setScale(2, RoundingMode.HALF_UP);
        	
        	viagem.setPreco(bd.floatValue());
            
        	return ResponseEntity.status(HttpStatus.CREATED)
                    .body(viagemRepository.save(viagem));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping
    public ResponseEntity<Viagem> put(@Valid @RequestBody Viagem viagem) {
        try {
        	Float preco = (viagem.getDistancia() / 
        			veiculoRepository.findById(viagem.getVeiculo().getId()).get().getVelocidadeMedia()) * 50;
        	
        	BigDecimal bd = new BigDecimal(Float.toString(preco));
        	bd = bd.setScale(2, RoundingMode.HALF_UP);
        	
        	viagem.setPreco(bd.floatValue());
        	
            return viagemRepository.findById(viagem.getId())
                    .map(resposta -> ResponseEntity.status(HttpStatus.OK)
                            .body(viagemRepository.save(viagem)))
                    .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            Optional<Viagem> viagem = viagemRepository.findById(id);
            if (viagem.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            viagemRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
