package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class VeiculosEstacionadosDTO {
    @Schema(
            description = "Map de categoria para quantidade",
            example = "{\"MOTO\":3,\"CARRO\":2}"
    )
    private Map<String, Integer> veiculosPorCategoria = new HashMap<>();
    @Schema(
            description = "totalVeiculos",
            example = "5"
    )
    private int totalVeiculos;
}


