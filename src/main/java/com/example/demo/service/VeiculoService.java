package com.example.demo.service;


import com.example.demo.dto.VeiculosEstacionadosDTO;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.model.Estacionamento;
import com.example.demo.model.Veiculo;
import com.example.demo.repository.EstacionamentoRepository;
import com.example.demo.repository.TabelaRepository;
import com.example.demo.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class VeiculoService {
    @Autowired
    VeiculoRepository repository;
    @Autowired
    EstacionamentoRepository estacionamentoRepository;
    @Autowired
    TabelaRepository tabelaRepository;

    public Veiculo create(Veiculo veiculo) {
        try {
            // Check if categoria exists in Tabela
            boolean categoriaExiste= tabelaRepository.findAll().stream()
                    .anyMatch(t -> t.getCategoria().equalsIgnoreCase(veiculo.getCategoria()));
            if (!categoriaExiste) {
                throw new BadRequestException("Categoria não encontrada na tabela de preços: " + veiculo.getCategoria());
            }
            return repository.save(veiculo);
        } catch (Exception e) {
            throw new BadRequestException("Erro ao salvar veículo: " + e.getMessage());
        }
    }

    public List<Veiculo> findAll() {
        return repository.findAll();
    }

    public Optional<Veiculo> findById(long id) {
        return repository.findById(id);
    }




    public Veiculo update(Veiculo veiculo) {
        try {
            Optional<Veiculo> verificarVeiculo = repository.findById(veiculo.getId());
            if (verificarVeiculo.isEmpty()) {
                throw new BadRequestException("Veículo não localizado no banco de dados");
            }
            // Check if categoria exists in Tabela
            boolean categoriaExiste = tabelaRepository.findAll().stream()
                    .anyMatch(t -> t.getCategoria().equalsIgnoreCase(veiculo.getCategoria()));
            if (!categoriaExiste) {
                throw new BadRequestException("Categoria não encontrada na tabela de preços: " + veiculo.getCategoria());
            }
            return repository.save(veiculo);
        } catch (Exception e) {
            throw new BadRequestException("Erro ao salvar veículo: " + e.getMessage());
        }
    }

    public void delete(Long id) {
        try {
            Optional<Veiculo> veiculo = repository.findById(id);
            if (veiculo.isEmpty()) {
                throw new BadRequestException("Veiculo não localizado no banco de dados");
            }
            repository.deleteById(id);
        } catch (Exception e) {
            throw new BadRequestException("Erro ao apagar o veiculo: " + e.getMessage());
        }
    }


    public VeiculosEstacionadosDTO veiculosEstacionados() {
        try {
            VeiculosEstacionadosDTO dto = new VeiculosEstacionadosDTO();
            Map<String, Integer> contagem = new HashMap<>();

            List<Estacionamento> estacionamentosAtivos = estacionamentoRepository.findAll().stream()
                    .filter(e -> e.getDataHoraSaida() == null)
                    .toList();

            for (Estacionamento estacionamento : estacionamentosAtivos) {
                Veiculo veiculo = estacionamento.getVeiculo();
                if (veiculo != null) {
                    String categoria = veiculo.getCategoria() != null ? veiculo.getCategoria().toUpperCase() : "UNKNOWN";
                    contagem.put(categoria, contagem.getOrDefault(categoria, 0) + 1);
                }
            }

            dto.setVeiculosPorCategoria(contagem);
            dto.setTotalVeiculos(contagem.values().stream().mapToInt(Integer::intValue).sum());

            return dto;
        } catch (Exception e) {
            throw new BadRequestException("Erro ao buscar veículos estacionados: " + e.getMessage());
        }
    }

}
