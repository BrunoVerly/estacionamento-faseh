package com.example.demo.service;


import com.example.demo.exceptions.BadRequestException;
import com.example.demo.model.Veiculo;
import com.example.demo.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoService {
    @Autowired
    VeiculoRepository repository;


    public Veiculo create (Veiculo veiculo) {
       try{
           return repository.save(veiculo);
       }catch(Exception e){
              throw new BadRequestException("Erro ao salvar veiculo: " + e.getMessage());
       }
    }
}
