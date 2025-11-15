package com.example.demo.dto;

import com.example.demo.model.Estacionamento;
import com.example.demo.model.Vaga;
import com.example.demo.model.Veiculo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class EstacionamentoSaidaDTO {

    @Schema(
            description = "Data e hora da emissão do ticket",
            example = "2023-10-01 10:00:00"
    )
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime data_hora_carimbo;

    @Schema(
            description = "Placa do veículo",
            example = "ABC-1234"
    )
    private String placa;

    @Schema(
            description = "Localização da vaga",
            example = "E1"
    )
    private String localVaga;

    @Schema(
            description = "Data e hora da entrada",
            example = "2023-10-01 09:00:00"
    )
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime data_hora_entrada;

    @Schema(
            description = "Data e hora da saída",
            example = "2023-10-01 10:00:00"
    )
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime data_hora_saida;

    @Schema(
            description = "Valor cobrado pelo tempo e categoria do veículo",
            example = "15.50"
    )
    private BigDecimal valor;

}


