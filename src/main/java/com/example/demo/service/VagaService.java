package com.example.demo.service;


import com.example.demo.exceptions.BadRequestException;
import com.example.demo.model.Vaga;
import com.example.demo.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VagaService {
    @Autowired
    VagaRepository repository;

    public Vaga create(Vaga vaga) {
        try {
            vaga.setOcupada(false);
            return repository.save(vaga);
        } catch (Exception e) {
            throw new BadRequestException("Erro ao cadastrar a vaga: " + e.getMessage());
        }
    }

    public void delete(Long id) {
        try {
            Optional<Vaga> vaga = repository.findById(id);
            if (vaga.isEmpty()) {
                throw new BadRequestException("Vaga não encontrada no banco de dados");
            }
            repository.deleteById(id);
        } catch (Exception e) {
            throw new BadRequestException("Erro ao deletar a vaga: " + e.getMessage());
        }
    }

    public List<Vaga> findAll() {
        return repository.findAll();
    }

    public List<Vaga> findOccupied() {
        return repository.findOccupied();
    }

    public List<Vaga> findFree() {
        return repository.findFree();
    }
}

