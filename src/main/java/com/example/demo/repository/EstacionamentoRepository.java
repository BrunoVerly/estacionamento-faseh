package com.example.demo.repository;


import com.example.demo.model.Estacionamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EstacionamentoRepository extends JpaRepository<Estacionamento,Long> {
    @Query("SELECT e FROM Estacionamento e WHERE e.veiculo.placa = ?1 AND e.vaga.local = ?2 AND e.dataHoraSaida IS NULL")
    Estacionamento findByPlacaAndVaga(String placa, String vaga);

}
