package com.mottu.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "PATIO")
public class Patio {

    @Id
    @Column(name = "ID_PATIO")
    private Integer idPatio;

    @Column(name = "NOME_PATIO", length = 50)
    private String nomePatio;

    @Column(name = "LOCALIZACAO_PATIO", length = 50)
    private String localizacaoPatio;

    @Column(name = "AREA_TOTAL")
    private Double areaTotal;

    @Column(name = "CAPACIDADE_MAXIMA")
    private Integer capacidadeMaxima;

    public Integer getIdPatio() { return idPatio; }
    public void setIdPatio(Integer idPatio) { this.idPatio = idPatio; }
    public String getNomePatio() { return nomePatio; }
    public void setNomePatio(String nomePatio) { this.nomePatio = nomePatio; }
    public String getLocalizacaoPatio() { return localizacaoPatio; }
    public void setLocalizacaoPatio(String localizacaoPatio) { this.localizacaoPatio = localizacaoPatio; }
    public Double getAreaTotal() { return areaTotal; }
    public void setAreaTotal(Double areaTotal) { this.areaTotal = areaTotal; }
    public Integer getCapacidadeMaxima() { return capacidadeMaxima; }
    public void setCapacidadeMaxima(Integer capacidadeMaxima) { this.capacidadeMaxima = capacidadeMaxima; }
}
