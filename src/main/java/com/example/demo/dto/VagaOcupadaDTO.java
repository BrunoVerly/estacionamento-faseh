package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class VagaOcupadaDTO {
    @Schema(
            description = "Localização da vaga",
            example = "A1"
    )
    private String localVaga;
    @Schema(
            description = "Placa do veículo",
            example = "ABC-1234"
    )
    private String placa;
    @Schema(
            description = "Modelo do veículo",
            example = "Fusca"
    )
    private String modelo;
}


