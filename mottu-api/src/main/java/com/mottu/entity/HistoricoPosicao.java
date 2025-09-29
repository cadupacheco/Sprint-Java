package com.mottu.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "HISTORICO_POSICAO")
@IdClass(HistoricoPosicaoId.class)
public class HistoricoPosicao {

    @Id
    @Column(name = "DATA_ATUALIZACAO")
    private LocalDate dataAtualizacao;

    @Id
    @Column(name = "ID_MOTO")
    private Integer idMoto;

    @Column(name = "POSICAO_X")
    private Double posicaoX;

    @Column(name = "POSICAO_Y")
    private Double posicaoY;

    @Column(name = "ACURACIA_LOCALIZACAO")
    private Double acuraciaLocalizacao;

    @Column(name = "ORIGEM_DETECTADA", length = 20)
    private String origemDetectada;

    @Column(name = "STATUS_NO_MOMENTO", length = 20)
    private String statusNoMomento;

    public LocalDate getDataAtualizacao() { return dataAtualizacao; }
    public void setDataAtualizacao(LocalDate dataAtualizacao) { this.dataAtualizacao = dataAtualizacao; }
    public Integer getIdMoto() { return idMoto; }
    public void setIdMoto(Integer idMoto) { this.idMoto = idMoto; }
    public Double getPosicaoX() { return posicaoX; }
    public void setPosicaoX(Double posicaoX) { this.posicaoX = posicaoX; }
    public Double getPosicaoY() { return posicaoY; }
    public void setPosicaoY(Double posicaoY) { this.posicaoY = posicaoY; }
    public Double getAcuraciaLocalizacao() { return acuraciaLocalizacao; }
    public void setAcuraciaLocalizacao(Double acuraciaLocalizacao) { this.acuraciaLocalizacao = acuraciaLocalizacao; }
    public String getOrigemDetectada() { return origemDetectada; }
    public void setOrigemDetectada(String origemDetectada) { this.origemDetectada = origemDetectada; }
    public String getStatusNoMomento() { return statusNoMomento; }
    public void setStatusNoMomento(String statusNoMomento) { this.statusNoMomento = statusNoMomento; }
}
