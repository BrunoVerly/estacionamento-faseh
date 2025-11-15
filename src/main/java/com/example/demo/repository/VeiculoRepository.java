package com.example.demo.repository;


import com.example.demo.model.Vaga;
import com.example.demo.model.Veiculo;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo,Long> {
    @Query("SELECT v FROM Veiculo v WHERE v.placa = ?1")
    Veiculo findVeiculoByPlaca(String placa);
}
