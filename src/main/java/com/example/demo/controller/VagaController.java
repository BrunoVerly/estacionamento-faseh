package com.example.demo.controller;

import com.example.demo.dto.VagaLivreDTO;
import com.example.demo.dto.VagaOcupadaDTO;
import com.example.demo.model.Vaga;
import com.example.demo.service.VagaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vaga")
@Tag(name = "Vaga", description = "Gerenciamento das vagas do estacionamento")
public class VagaController {
    @Autowired
    VagaService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Cadastrar vaga", description = "Endpoint para registar nova vaga de estacionamento")
    public Vaga create(@RequestBody Vaga vaga) {
        return service.create(vaga);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Atualizar vaga", description = "Endpoint para atualizar vaga de estacionamento")
    public Vaga update(@RequestBody Vaga vaga) {
        return service.update(vaga);
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Buscar todas as vagas", description = "Endpoint para buscar todas as vagas de estacionamentos cadastradas")
    public List<Vaga> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/ocupada",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Buscar todas as vagas ocupadas", description = "Endpoint para buscar todas as vagas de estacionamentos em uso")
    public List<VagaOcupadaDTO> findOccupied() {
        return service.findOccupied();
    }

    @GetMapping(value = "/livre",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Buscar todas as vagas livres", description = "Endpoint para buscar todas as vagas de estacionamentos livres")
    public List<VagaLivreDTO> findFree() {
        return service.findFree();
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Apagar vaga", description = "Endpoint para apagar vaga de estacionamento")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
