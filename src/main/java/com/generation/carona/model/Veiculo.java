package com.generation.carona.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_veiculos")
public class Veiculo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    
    @NotBlank(message = "O atributo modelo é Obrigatório!") 
    @Size(min = 2, max = 100, message = "O atributo modelo deve conter no mínimo 2 e no máximo 100 caracteres")
    private String modelo;
    
    @NotBlank(message = "O atributo marca é Obrigatório!")
    @Size(min = 2, max = 50, message = "O atributo marca deve conter no mínimo 2 e no máximo 50 caracteres")
    private String marca;
    
    @NotBlank(message = "O atributo placa é Obrigatório!")
    @Size(min = 7, max = 7, message = "O atributo placa deve conter exatamente 7 caracteres")
    private String placa;
    
    @UpdateTimestamp
    private LocalDateTime dataCadastro;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPlaca() {
        return this.placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public LocalDateTime getDataCadastro() {
        return this.dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
