package com.example.demo.model;


import com.example.demo.enums.VeiculoCategoria;
import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Entity
@Table(name = "veiculo")
@Data
public class Veiculo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String marca;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private VeiculoCategoria categoria;
    @Column(nullable = false)
    private String modelo;
    @Column(nullable = false, unique = true)
    private String placa;
    @Column(nullable = false)
    private String propietario;


}
