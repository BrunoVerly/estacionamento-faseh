package com.example.demo.repository;


import com.example.demo.model.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VagaRepository extends JpaRepository<Vaga,Long> {
    Vaga findByLocal(String local);

    @Query
    ("SELECT v FROM Vaga v WHERE v.ocupada = true")
    List<Vaga> findOccupied();

    @Query
    ("SELECT v FROM Vaga v WHERE v.ocupada = false")
    List<Vaga> findFree();
}
