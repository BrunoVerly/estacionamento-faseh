package com.example.demo.controller;

import com.example.demo.model.Veiculo;
import com.example.demo.service.EstacionamentoService;
import com.example.demo.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {
    @Autowired
    VeiculoService service;
    
    //@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    //public List<Veiculo> findAll() {
    //    return service.findAll();
    //}

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Veiculo create(@RequestBody Veiculo veiculo) {
        return service.create(veiculo);
    }

    //@DeleteMapping(path = "/{id}")
    //public void delete(@PathVariable Long id) {
   //     service.delete(id);
    //}
    
    
    
    
    
}
