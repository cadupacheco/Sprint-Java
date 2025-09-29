package com.mottu.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "SENSOR_IOT")
public class SensorIot {

    @Id
    @Column(name = "ID_SENSOR_IOT")
    private Integer idSensorIot;

    @Column(name = "TIPO_SENSOR", length = 20)
    private String tipoSensor;

    @Column(name = "DATA_TRANSMISSAO")
    private LocalDate dataTransmissao;

    @Column(name = "BATERIA_PERCENTUAL")
    private Double bateriaPercentual;

    @Column(name = "ID_MOTO")
    private Integer idMoto;

    public Integer getIdSensorIot() { return idSensorIot; }
    public void setIdSensorIot(Integer idSensorIot) { this.idSensorIot = idSensorIot; }
    public String getTipoSensor() { return tipoSensor; }
    public void setTipoSensor(String tipoSensor) { this.tipoSensor = tipoSensor; }
    public LocalDate getDataTransmissao() { return dataTransmissao; }
    public void setDataTransmissao(LocalDate dataTransmissao) { this.dataTransmissao = dataTransmissao; }
    public Double getBateriaPercentual() { return bateriaPercentual; }
    public void setBateriaPercentual(Double bateriaPercentual) { this.bateriaPercentual = bateriaPercentual; }
    public Integer getIdMoto() { return idMoto; }
    public void setIdMoto(Integer idMoto) { this.idMoto = idMoto; }
}
