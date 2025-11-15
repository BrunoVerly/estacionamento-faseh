package com.example.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "tabela")
@Data
public class Tabela {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
            description = "Id do registro da tabela de categoria-preço",
            example = "1"
    )
    private Long id;

    @Column(unique = true, nullable = false)
    @Schema(
            description = "Categoria do veículo",
            example = "CARRO"
    )
    private String categoria;

    @Column(name = "preco_por_hora", nullable = false, precision = 10, scale = 2)
    @Schema(
            description = "Valor cobrado por hora pela categoria do veículo",
            example = "15.50"
    )
    private BigDecimal precoPorHora;
}
