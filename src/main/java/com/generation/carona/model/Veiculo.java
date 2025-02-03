package com.generation.carona.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_veiculos")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O Atributo Modelo é obrigatório")
    @Size(min = 3, max = 100, message = "O modelo deve ter entre 3 e 100 caracteres")
    private String modelo;

    @NotNull(message = "O Atributo Marca é obrigatório")
    @Size(min = 3, max = 100, message = "A marca deve ter entre 3 e 100 caracteres")
    private String marca;

    @NotNull(message = "A Placa é obrigatória")
    @Size(min = 7, max = 7, message = "A placa deve ter 7 caracteres")
    private String placa;

    @JsonIgnoreProperties("veiculo")
    @OneToMany(mappedBy = "veiculo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Viagem> viagens;

    @NotNull(message = "O preço é obrigatório")
    private Float preco;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public List<Viagem> getViagens() {
        return viagens;
    }

    public void setViagens(List<Viagem> viagens) {
        this.viagens = viagens;
    }

	public Float getPreco() {
		return preco;
	}

	public void setPreco(Float preco) {
		this.preco = preco;
	}
    
    
}

