package com.example.demo.service;


import com.example.demo.exceptions.BadRequestException;
import com.example.demo.model.Tabela;
import com.example.demo.model.Vaga;
import com.example.demo.repository.TabelaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TabelaService {
    @Autowired
    TabelaRepository repository;

    public Tabela create(Tabela tabela) {
            return repository.save(tabela);
    }

    public void delete(Long id) {
        try {
            Optional<Tabela> tabela = repository.findById(id);
            if (tabela.isEmpty()) {
                throw new BadRequestException("Tabela categoria-preco não encontrada no banco de dados");
            }
            repository.deleteById(id);
        } catch (Exception e) {
            throw new BadRequestException("Erro ao apagar a categoria-preco: " + e.getMessage());
        }
    }
    public Tabela update(Tabela tabela) {
        try {
            Optional<Tabela> verificarTabela = repository.findById(tabela.getId());
            if (verificarTabela.isEmpty()) {
                throw new BadRequestException("Vaga não encontrada no banco de dados");
            }
            return repository.save(tabela);
        } catch (Exception e) {
            throw new BadRequestException("Erro ao atualizar a vaga: " + e.getMessage());
        }
    }

    public List<Tabela> findAll() {
        return repository.findAll();
    }
}

