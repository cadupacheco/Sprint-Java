package com.mottu.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "MOTO")
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

    @Column(name = "MODELO_ID_MODELO")
    private Integer modeloId;

    @Column(name = "ID_PATIO")
    private Integer idPatio;

    @Column(name = "ID_SENSOR_IOT")
    private Integer idSensorIot;

    @Column(name = "DATA_ATUALIZACAO")
    private LocalDate dataAtualizacao;

    public Integer getIdMoto() { return idMoto; }
    public void setIdMoto(Integer idMoto) { this.idMoto = idMoto; }
    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }
    public String getChassi() { return chassi; }
    public void setChassi(String chassi) { this.chassi = chassi; }
    public Integer getAnoFabricacao() { return anoFabricacao; }
    public void setAnoFabricacao(Integer anoFabricacao) { this.anoFabricacao = anoFabricacao; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Integer getModeloId() { return modeloId; }
    public void setModeloId(Integer modeloId) { this.modeloId = modeloId; }
    public Integer getIdPatio() { return idPatio; }
    public void setIdPatio(Integer idPatio) { this.idPatio = idPatio; }
    public Integer getIdSensorIot() { return idSensorIot; }
    public void setIdSensorIot(Integer idSensorIot) { this.idSensorIot = idSensorIot; }
    public LocalDate getDataAtualizacao() { return dataAtualizacao; }
    public void setDataAtualizacao(LocalDate dataAtualizacao) { this.dataAtualizacao = dataAtualizacao; }
}
