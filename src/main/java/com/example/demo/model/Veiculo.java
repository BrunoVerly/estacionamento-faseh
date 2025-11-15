package com.example.demo.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Entity
@Table(name = "veiculo")
@Data
public class Veiculo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
            description = "Id do registro da tabela de categoria-preço",
            example = "1"
    )
    private Long id;

    @Column(nullable = false)
    @Schema(
            description = "Marca do veículo",
            example = "Volkswagen"
    )
    private String marca;

    @Column(nullable = false)
    @Schema(
            description = "Categoria do veículo",
            example = "CARRO"
    )
    private String categoria;

    @Column(nullable = false)
    @Schema(
            description = "Modelo do veículo",
            example = "Fusca"
    )
    private String modelo;

    @Column(nullable = false, unique = true)
    @Schema(
            description = "Placa do veículo",
            example = "ABC-1234"
    )
    private String placa;

    @Schema(
            description = "Proprietário do veículo",
            example = "João da Silva"
    )
    @Column(nullable = false)
    private String propietario;

}
