package com.example.demo.service;

import com.example.demo.dto.EstacionamentoCreateDTO;
import com.example.demo.dto.EstacionamentoSaidaDTO;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.model.Estacionamento;
import com.example.demo.model.Preco;
import com.example.demo.model.Vaga;
import com.example.demo.model.Veiculo;
import com.example.demo.repository.EstacionamentoRepository;
import com.example.demo.repository.PrecoRepository;
import com.example.demo.repository.VagaRepository;
import com.example.demo.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;

import static java.math.RoundingMode.HALF_UP;

@Service
public class EstacionamentoService {
    @Autowired
    EstacionamentoRepository repository;
    @Autowired
    VeiculoRepository repositoryVeiculo;
    @Autowired
    VagaRepository repositoryVaga;
    @Autowired
    PrecoRepository repositoryPreco;


    public Estacionamento create(EstacionamentoCreateDTO estacionamentoDTO) {
        // 1. Validar campos
        if (estacionamentoDTO.getPlaca() == null || estacionamentoDTO.getPlaca().isEmpty() ||
                estacionamentoDTO.getLocalVaga() == null || estacionamentoDTO.getLocalVaga().isEmpty()) {
            throw new BadRequestException("Os campos placa e vaga são obrigatórios");
        }

        // 2. Buscar veiculo
        Veiculo veiculo = repositoryVeiculo.findVeiculoByPlaca(estacionamentoDTO.getPlaca());
        if (veiculo == null) {
            throw new BadRequestException("Veiculo não encontrado com a placa: " + estacionamentoDTO.getPlaca());
        }

        // 3. Buscar vaga (usar repositoryVaga, não repositoryVeiculo)
        Vaga vaga = repositoryVaga.findByLocal(estacionamentoDTO.getLocalVaga());
        if (vaga == null) {
            throw new BadRequestException("Vaga não encontrada: " + estacionamentoDTO.getLocalVaga());
        }

        // 4. Verificar se vaga está ocupada
        if (vaga.isOcupada()) {
            throw new BadRequestException("A vaga selecionada já está ocupada");
        }

        // 5. Registrar estacionamento
        Estacionamento estacionamento = new Estacionamento();
        estacionamento.setVaga(vaga);
        estacionamento.setVeiculo(veiculo);
        estacionamento.setDataHoraEntrada(LocalDateTime.now());

        // 6. Marcar vaga como ocupada
        vaga.setOcupada(true);
        repositoryVaga.save(vaga);

        return repository.save(estacionamento);
    }


    public EstacionamentoSaidaDTO update(EstacionamentoCreateDTO vagaEstacionada) {
        // 1. Validar campos
        if (vagaEstacionada.getPlaca() == null || vagaEstacionada.getPlaca().isEmpty() ||
                vagaEstacionada.getLocalVaga() == null || vagaEstacionada.getLocalVaga().isEmpty()) {
            throw new BadRequestException("Os campos placa e vaga são obrigatórios");
        }

        // 2. Buscar estacionamento ativo pela placa E vaga
        Estacionamento estacionamento = repository.findByPlacaAndVaga(
                vagaEstacionada.getPlaca(),
                vagaEstacionada.getLocalVaga()
        );

        if (estacionamento == null) {
            throw new BadRequestException(
                    "Nenhum estacionamento ativo encontrado para a placa " +
                            vagaEstacionada.getPlaca() + " na vaga " + vagaEstacionada.getLocalVaga()
            );
        }

        // 3. Registrar saída
        estacionamento.setDataHoraSaida(LocalDateTime.now());

        // 4. Liberar vaga
        Vaga vaga = estacionamento.getVaga();
        vaga.setOcupada(false);
        repositoryVaga.save(vaga);

        // 5. Salvar estacionamento atualizado
        repository.save(estacionamento);

        // 6. Calcular tempo e valor
        Duration duracao = Duration.between(estacionamento.getDataHoraEntrada(), estacionamento.getDataHoraSaida());
        long minutosUsados = duracao.toMinutes();

        Preco preco = repositoryPreco.findAll().stream()
                .filter(p -> p.getCategoria() == estacionamento.getVeiculo().getCategoria())
                .findFirst()
                .orElseThrow(() -> new BadRequestException("Preço não encontrado para a categoria"));

        BigDecimal horasUsadas = BigDecimal.valueOf(minutosUsados)
                .divide(BigDecimal.valueOf(60), 2, RoundingMode.HALF_UP);
        BigDecimal valorAPagar = preco.getPrecoPorHora()
                .multiply(horasUsadas)
                .setScale(2, RoundingMode.HALF_UP);

        // 7. Montar DTO de saída
        EstacionamentoSaidaDTO saidaDTO = new EstacionamentoSaidaDTO();
        saidaDTO.setData_hora_carimbo(LocalDateTime.now());
        saidaDTO.setPlaca(estacionamento.getVeiculo().getPlaca());
        saidaDTO.setVaga(vaga.getLocal());
        saidaDTO.setData_hora_entrada(estacionamento.getDataHoraEntrada());
        saidaDTO.setData_hora_saida(estacionamento.getDataHoraSaida());
        saidaDTO.setValor(valorAPagar);

        return saidaDTO;
    }



}

