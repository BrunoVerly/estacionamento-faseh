package com.example.demo.dto;

import com.example.demo.model.Estacionamento;
import com.example.demo.model.Vaga;
import com.example.demo.model.Veiculo;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class EstacionamentoSaidaDTO {
    private static final long serialVersionUID = 1L;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime data_hora_carimbo;

    private String placa;

    private String vaga;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime data_hora_entrada;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime data_hora_saida;

    private BigDecimal valor;

}


