package com.example.demo.model;

import com.example.demo.enums.VeiculoCategoria;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "preco")
@Data
public class Preco {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private VeiculoCategoria categoria;

    @Column(name = "preco_por_hora", nullable = false)
    private BigDecimal precoPorHora;
}
