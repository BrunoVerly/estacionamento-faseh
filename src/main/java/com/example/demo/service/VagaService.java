package com.example.demo.service;


import com.example.demo.dto.VagaLivreDTO;
import com.example.demo.dto.VagaOcupadaDTO;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.model.Estacionamento;
import com.example.demo.model.Vaga;
import com.example.demo.model.Veiculo;
import com.example.demo.repository.EstacionamentoRepository;
import com.example.demo.repository.VagaRepository;
import com.example.demo.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VagaService {
    @Autowired
    VagaRepository repository;
    @Autowired
    VeiculoRepository veiculoRepository;
    @Autowired
    VeiculoService veiculoService;
    @Autowired
    EstacionamentoRepository estacionamentoRepository;
    @Autowired
    EstacionamentoService estacionamentoService;

    public Vaga create(Vaga vaga) {
        try {
            vaga.setOcupada(false);
            return repository.save(vaga);
        } catch (Exception e) {
            throw new BadRequestException("Erro ao cadastrar a vaga: " + e.getMessage());
        }
    }

    public Vaga update(Vaga vaga) {
        try {
            Optional<Vaga> verificarVaga = repository.findById(vaga.getId());
            if (verificarVaga.isEmpty()) {
                throw new BadRequestException("Vaga não encontrada no banco de dados");
            }
            return repository.save(vaga);
        } catch (Exception e) {
            throw new BadRequestException("Erro ao atualizar a vaga: " + e.getMessage());
        }
    }

    public List<Vaga> findAll() {

        return repository.findAll();
    }

    public List<VagaOcupadaDTO> findOccupied() {
        try {
            List<Vaga> vagas = repository.findOccupied();
            List<VagaOcupadaDTO> vagasDTO = new ArrayList<>();
            for (Vaga vaga : vagas) {
                Estacionamento estacionamento = estacionamentoRepository.findByVaga(vaga.getLocal());
                if (estacionamento != null) {
                    Veiculo veiculo = estacionamento.getVeiculo();
                    VagaOcupadaDTO vagaOcupadaDTO = new VagaOcupadaDTO();
                    vagaOcupadaDTO.setLocalVaga(vaga.getLocal());
                    vagaOcupadaDTO.setPlaca(veiculo.getPlaca());
                    vagaOcupadaDTO.setModelo(veiculo.getModelo());
                    vagasDTO.add(vagaOcupadaDTO);
                }
            }
            return vagasDTO;
        } catch (Exception e) {
            throw new BadRequestException("Erro ao buscar vagas ocupadas: " + e.getMessage());
        }
    }

    public List<VagaLivreDTO> findFree() {
        try {
            List<Vaga> vagas = repository.findFree();
            List<VagaLivreDTO> vagasDTO = new ArrayList<>();
            for (Vaga vaga : vagas) {
                VagaLivreDTO vagaLivreDTO = new VagaLivreDTO();
                vagaLivreDTO.setLocalVaga(vaga.getLocal());
                vagasDTO.add(vagaLivreDTO);
            }
            return vagasDTO;
        } catch (Exception e) {
            throw new BadRequestException("Erro ao buscar vagas livres: " + e.getMessage());
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
            throw new BadRequestException("Erro ao apagar a vaga: " + e.getMessage());
        }
    }


}

