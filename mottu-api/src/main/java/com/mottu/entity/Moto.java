package com.mottu.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "MOTO")
@Data
public class Moto {

    @Id
    @Column(name = "ID_MOTO")
    private Integer idMoto;

    @Column(name = "PLACA", length = 10)
    private String placa;

    @Column(name = "CHASSI", length = 50)
    private String chassi;

    @Column(name = "ANO_FABRICACAO")
    private Integer anoFabricacao;

    @Column(name = "STATUS", length = 20)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MODELO_ID_MODELO")
    private Modelo modelo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PATIO")
    private Patio patio;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SENSOR_IOT")
    private SensorIot sensorIot;

    @Column(name = "DATA_ATUALIZACAO")
    private LocalDate dataAtualizacao;
}
