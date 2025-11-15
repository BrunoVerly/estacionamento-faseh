package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class VagaLivreDTO {
    @Schema(
            description = "Localização da vaga",
            example = "E2"
    )
    private String localVaga;
}


