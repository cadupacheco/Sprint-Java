package com.mottu.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "MODELO")
@Data
public class Modelo {

    @Id
    @Column(name = "ID_MODELO")
    private Integer idModelo; // INTEGER no banco

    @Column(name = "FABRICANTE", length = 50)
    private String fabricante;

    @Column(name = "NOME_MODELO", length = 50)
    private String nomeModelo;

    @Column(name = "CILINDRADA")
    private Integer cilindrada;

    @Column(name = "TIPO", length = 30)
    private String tipo;
}
