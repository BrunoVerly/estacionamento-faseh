package com.example.demo.config;

import com.example.demo.model.Tabela;
import com.example.demo.repository.TabelaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class DataInitializer implements ApplicationRunner {

    @Autowired
    TabelaRepository tabelaRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if (tabelaRepository.count() == 0) {

            // 2. Cria os objetos
            Tabela moto = new Tabela();
            moto.setCategoria("MOTO");
            moto.setPrecoPorHora(new BigDecimal("8.00"));

            Tabela carro = new Tabela();
            carro.setCategoria("CARRO");
            carro.setPrecoPorHora(new BigDecimal("16.00"));

            Tabela van = new Tabela();
            van.setCategoria("VAN");
            van.setPrecoPorHora(new BigDecimal("25.00"));

            tabelaRepository.saveAll(List.of(moto, carro, van));

        }
    }
}