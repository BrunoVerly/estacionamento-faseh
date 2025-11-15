package com.example.demo.controller;

import com.example.demo.model.Vaga;
import com.example.demo.model.Veiculo;
import com.example.demo.service.VagaService;
import com.example.demo.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vaga")
public class VagaController {
    @Autowired
    VagaService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Vaga create(@RequestBody Vaga vaga) {
        return service.create(vaga);
    }

}
