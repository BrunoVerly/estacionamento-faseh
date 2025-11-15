package com.example.demo.controller;

import com.example.demo.dto.EstacionamentoCreateDTO;
import com.example.demo.dto.EstacionamentoSaidaDTO;
import com.example.demo.model.Estacionamento;
import com.example.demo.service.EstacionamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estacionamento")
@Tag(name = "Estacionamento", description = "Gerenciamento de entradas e saídas de veículos")
public class EstacionamentoController{
    @Autowired
    EstacionamentoService service;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Registrar entrada", description = "Registra a entrada de veículo no estacionamento")
    public Estacionamento create(@RequestBody EstacionamentoCreateDTO registro) {

        return service.create(registro);
    }
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Registrar saida", description = "Registra a saida de veículo do estacionamento e retorna o ticket")
    public EstacionamentoSaidaDTO update(@RequestBody EstacionamentoCreateDTO registro) {

        return service.update(registro);
    }

}

