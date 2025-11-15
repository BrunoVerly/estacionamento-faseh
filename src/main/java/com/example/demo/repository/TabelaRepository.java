package com.example.demo.repository;

import com.example.demo.model.Tabela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TabelaRepository extends JpaRepository<Tabela, Long> {
}
