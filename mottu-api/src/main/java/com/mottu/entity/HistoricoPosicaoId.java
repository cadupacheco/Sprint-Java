package com.mottu.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class HistoricoPosicaoId implements Serializable {

    private LocalDate dataAtualizacao; // DATE no banco
    private Integer idMoto; // INTEGER no banco

    public HistoricoPosicaoId() {}

    public HistoricoPosicaoId(LocalDate dataAtualizacao, Integer idMoto) {
        this.dataAtualizacao = dataAtualizacao;
        this.idMoto = idMoto;
    }

    public LocalDate getDataAtualizacao() { return dataAtualizacao; }
    public void setDataAtualizacao(LocalDate dataAtualizacao) { this.dataAtualizacao = dataAtualizacao; }
    public Integer getIdMoto() { return idMoto; }
    public void setIdMoto(Integer idMoto) { this.idMoto = idMoto; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoricoPosicaoId that = (HistoricoPosicaoId) o;
        return Objects.equals(dataAtualizacao, that.dataAtualizacao) &&
                Objects.equals(idMoto, that.idMoto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataAtualizacao, idMoto);
    }
}
