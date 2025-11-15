package com.example.demo.controller;

import com.example.demo.dto.VeiculosEstacionadosDTO;
import com.example.demo.model.Veiculo;
import com.example.demo.service.VeiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/veiculo")
@Tag(name = "Veiculo", description = "Gerenciamento dos veículos")
public class VeiculoController {
    @Autowired
    VeiculoService service;
    
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Buscar todos os veiculos", description = "Endpoint para buscar todos os veículos cadastrados")
    public List<Veiculo> findAll() {
        return service.findAll();
    }
    @GetMapping(value ="/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Buscar veiculo por id", description = "Endpoint para buscar um veículo pelo id")
    public Optional<Veiculo> findById(@PathVariable long id) {
        return service.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Cadastar veiculo", description = "Endpoint para registrar novo veículo")
    public Veiculo create(@RequestBody Veiculo veiculo) {
        return service.create(veiculo);
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Apagar veiculo", description = "Endpoint apagar um veículo pelo id")
    public void delete(@PathVariable Long id) {
       service.delete(id);
    }
    
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Atualizar veiculo", description = "Endpoint para atualizar veiculo")
    public Veiculo update(@RequestBody Veiculo veiculo) {
        return service.update(veiculo);
    }

    @GetMapping(value ="/estacionados",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Buscar veiculos no estaciomento", description = "Endpoint buscar todos os veículos atualmente estacionados")
    public VeiculosEstacionadosDTO veiculosEstacionados() {
        return service.veiculosEstacionados();
    }

}
