package com.example.demo.config;

import com.example.demo.enums.VeiculoCategoria;
import com.example.demo.model.Preco;
import com.example.demo.repository.PrecoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.math.BigDecimal;

@Configuration
public class PrecoInitializer {

    @Bean
    CommandLineRunner initPrecos(PrecoRepository repository) {
        return args -> {
            // Verifica se já existem preços cadastrados
            if (repository.count() == 0) {
                Preco precoMoto = new Preco();
                precoMoto.setCategoria(VeiculoCategoria.MOTO);
                precoMoto.setPrecoPorHora(new BigDecimal("8.00"));

                Preco precoCarro = new Preco();
                precoCarro.setCategoria(VeiculoCategoria.CARRO);
                precoCarro.setPrecoPorHora(new BigDecimal("16.00"));

                Preco precoVan = new Preco();
                precoVan.setCategoria(VeiculoCategoria.VAN);
                precoVan.setPrecoPorHora(new BigDecimal("25.00"));

                repository.save(precoMoto);
                repository.save(precoCarro);
                repository.save(precoVan);

                System.out.println("Preços inicializados com sucesso!");
            }
        };
    }
}
