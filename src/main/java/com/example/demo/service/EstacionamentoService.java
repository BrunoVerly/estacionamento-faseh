package com.example.demo.service;

import com.example.demo.dto.EstacionamentoCreateDTO;
import com.example.demo.dto.EstacionamentoSaidaDTO;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.model.Estacionamento;
import com.example.demo.model.Tabela;
import com.example.demo.model.Vaga;
import com.example.demo.model.Veiculo;
import com.example.demo.repository.EstacionamentoRepository;
import com.example.demo.repository.TabelaRepository;
import com.example.demo.repository.VagaRepository;
import com.example.demo.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class EstacionamentoService {
    @Autowired
    EstacionamentoRepository repository;
    @Autowired
    VeiculoRepository repositoryVeiculo;
    @Autowired
    VagaRepository repositoryVaga;
    @Autowired
    TabelaRepository repositoryPreco;


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
        estacionamento.setUsuario(estacionamentoDTO.getUsuario());
        estacionamento.setDataHoraEntrada(LocalDateTime.now());

        // 6. Marcar vaga como ocupada
        vaga.setOcupada(true);
        repositoryVaga.save(vaga);

        return repository.save(estacionamento);
    }


    public EstacionamentoSaidaDTO update(EstacionamentoCreateDTO vagaEstacionada) {
        // validation and retrieval omitted for brevity (keep your existing logic)

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

        estacionamento.setDataHoraSaida(LocalDateTime.now());

        Vaga vaga = estacionamento.getVaga();
        vaga.setOcupada(false);
        repositoryVaga.save(vaga);

        // --- Início da Lógica de Cálculo Atualizada ---

        Duration duracao = Duration.between(
                estacionamento.getDataHoraEntrada(),
                estacionamento.getDataHoraSaida()
        );

        // Pega o total de milissegundos da duração
        BigDecimal totalMillis = BigDecimal.valueOf(duracao.toMillis());

        // Busca a categoria (lógica original)
        String categoriaVeiculo = estacionamento.getVeiculo() != null &&
                estacionamento.getVeiculo().getCategoria() != null
                ? estacionamento.getVeiculo().getCategoria()
                : "";

        // Busca o preço por hora
        Tabela tabela = repositoryPreco.findAll().stream()
                .filter(p -> p.getCategoria().equalsIgnoreCase(categoriaVeiculo))
                .findFirst()
                .orElseThrow(() -> new BadRequestException("Preço não encontrado para a categoria"));

        // Garanta que tabela.getPrecoPorHora() retorne um BigDecimal
        BigDecimal precoPorHora = tabela.getPrecoPorHora();

        // Define a constante de milissegundos por hora (60 min * 60 seg * 1000 ms)
        // Usar o construtor String é a forma mais segura de garantir precisão
        BigDecimal MILLIS_POR_HORA = new BigDecimal("3600000");

        // Lógica de cálculo direta:
        // valor = (precoPorHora * totalMillis) / millisPorHora
        // Multiplicamos primeiro para manter a precisão máxima
        BigDecimal valorAPagar = precoPorHora
                .multiply(totalMillis)
                .divide(MILLIS_POR_HORA, 2, RoundingMode.HALF_UP); // Divide e já arredonda para 2 casas

        // --- Fim da Lógica de Cálculo Atualizada ---

        estacionamento.setValorTotal(valorAPagar);
        repository.save(estacionamento);

        // Criação do DTO de saída
        EstacionamentoSaidaDTO saidaDTO = new EstacionamentoSaidaDTO();
        saidaDTO.setData_hora_carimbo(LocalDateTime.now());
        saidaDTO.setPlaca(estacionamento.getVeiculo().getPlaca());
        saidaDTO.setLocalVaga(vaga.getLocal());
        saidaDTO.setData_hora_entrada(estacionamento.getDataHoraEntrada());
        saidaDTO.setData_hora_saida(estacionamento.getDataHoraSaida());
        saidaDTO.setValor(valorAPagar);

        return saidaDTO;
    }


}

