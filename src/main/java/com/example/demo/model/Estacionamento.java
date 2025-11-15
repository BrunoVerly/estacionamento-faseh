package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "estacionamento")
@Data
public class Estacionamento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
            description = "Id do registro de estacionamento",
            example = "1"
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_vaga", referencedColumnName = "id")
    private Vaga vaga;

    @ManyToOne(optional = true)
    @JoinColumn(name = "veiculo_id", referencedColumnName = "id")
    private Veiculo veiculo;

    @Column(name = "usuario", nullable = false)
    @Schema(
            description = "Usuário que está registrando o estacionamento",
            example = "carlos.silva"
    )
    private String usuario;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "data_hora_entrada")
    @Schema(
            description = "Data e hora da entrada",
            example = "2023-10-01 09:00:00"
    )
    private LocalDateTime dataHoraEntrada;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "data_hora_saida")
    @Schema(
            description = "Data e hora da saida",
            example = "2023-10-01 09:00:00"
    )
    private LocalDateTime dataHoraSaida;

    @Column(name = "valor_total")
    @Schema(
            description = "Valor cobrado pelo tempo e categoria do veículo",
            example = "15.50"
    )
    private BigDecimal valorTotal;
}

