package com.generation.carona.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_viagens")
public class Viagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O Atributo Origem é obrigatório")
    private String origem;

    @NotNull(message = "O Atributo Destino é obrigatório")
    private String destino;

    @NotNull(message = "A Data e Hora da partida são obrigatórias")
    private LocalDateTime dataHoraPartida;

    @JsonIgnoreProperties("viagens")
    @ManyToOne
    private Veiculo veiculo;

    @JsonIgnoreProperties("viagens")
    @ManyToOne
    private Usuario usuario;

    @NotNull(message = "A distancia é obrigatória")
    private Float distancia;

    @NotNull(message = "O preço é obrigatório")
    private Float preco;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalDateTime getDataHoraPartida() {
        return dataHoraPartida;
    }

    public void setDataHoraPartida(LocalDateTime dataHoraPartida) {
        this.dataHoraPartida = dataHoraPartida;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

	public Float getDistancia() {
		return distancia;
	}

	public void setDistancia(Float distancia) {
		this.distancia = distancia;
	}

	public Float getPreco() {
		return preco;
	}

	public void setPreco(Float preco) {
		this.preco = preco;
	}
	
}


