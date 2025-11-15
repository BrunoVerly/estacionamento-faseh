package com.example.demo.controller;

import com.example.demo.dto.EstacionamentoCreateDTO;
import com.example.demo.dto.EstacionamentoSaidaDTO;
import com.example.demo.model.Estacionamento;
import com.example.demo.service.EstacionamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estacionamento")
public class EstacionamentoController {
    @Autowired
    EstacionamentoService service;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Estacionamento create(@RequestBody EstacionamentoCreateDTO registro) {

        return service.create(registro);
    }
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public EstacionamentoSaidaDTO update(@RequestBody EstacionamentoCreateDTO registro) {

        return service.update(registro);
    }

}

