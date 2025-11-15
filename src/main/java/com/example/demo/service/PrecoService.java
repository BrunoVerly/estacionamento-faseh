package com.example.demo.service;


import com.example.demo.exceptions.BadRequestException;
import com.example.demo.model.Preco;
import com.example.demo.model.Vaga;
import com.example.demo.repository.PrecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrecoService {
    @Autowired
    PrecoRepository repository;

    public Preco create(Preco preco) {
            return repository.save(preco);
    }

    public void delete(Long id) {
        try {
            Optional<Preco> preco = repository.findById(id);
            if (preco.isEmpty()) {
                throw new BadRequestException("Vaga não encontrada no banco de dados");
            }
            repository.deleteById(id);
        } catch (Exception e) {
            throw new BadRequestException("Erro ao apagar o preco: " + e.getMessage());
        }
    }

    public List<Preco> findAll() {
        return repository.findAll();
    }
}

