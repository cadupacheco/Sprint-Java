package com.mottu.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ALERTA_EVENTO")
public class AlertaEvento {

    @Id
    @Column(name = "ID_ALERTA")
    private Integer idAlerta;

    @Column(name = "TIPO_ALERTA", length = 50)
    private String tipoAlerta;

    @Column(name = "DATA_GERACAO")
    private LocalDate dataGeracao;

    @Column(name = "ID_MOTO")
    private Integer idMoto;

    public Integer getIdAlerta() { return idAlerta; }
    public void setIdAlerta(Integer idAlerta) { this.idAlerta = idAlerta; }
    public String getTipoAlerta() { return tipoAlerta; }
    public void setTipoAlerta(String tipoAlerta) { this.tipoAlerta = tipoAlerta; }
    public LocalDate getDataGeracao() { return dataGeracao; }
    public void setDataGeracao(LocalDate dataGeracao) { this.dataGeracao = dataGeracao; }
    public Integer getIdMoto() { return idMoto; }
    public void setIdMoto(Integer idMoto) { this.idMoto = idMoto; }
}
