package com.example.demo.controller;

import com.example.demo.model.Tabela;
import com.example.demo.model.Vaga;
import com.example.demo.service.TabelaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tabela")
@Tag(name = "Tabela", description = "Gerenciamento das tabelas de categoria-preço")
public class TabelaController {
    @Autowired
    TabelaService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Cadastrar tabela de categoria-preço", description = "Endpoint para registar uma nova tabela de categoria-preço")
    public Tabela create(@RequestBody Tabela tabela) {
        return service.create(tabela);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Atualizar tabela categoria-preço", description = "Endpoint para atualizar uma tabela de categoria-preço")
    public Tabela update(@RequestBody Tabela tabela) {
        return service.update(tabela);
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Buscar todas as tabelas de categoria-preço", description = "Endpoint buscar todas as tabelas de categoria-preço cadastradas")
    public List<Tabela> findAll() {
        return service.findAll();
    }


    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Apagar tabela", description = "Endpoint apagar uma tabela categoria-preço pelo id")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
