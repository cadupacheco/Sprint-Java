package com.mottu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "HISTORICO_POSICAO")
@IdClass(HistoricoPosicaoId.class)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoricoPosicao {

    @Id
    @Column(name = "DATA_ATUALIZACAO")
    private LocalDate dataAtualizacao;

    @Id
    @Column(name = "ID_MOTO")
    private Integer idMoto;

    @Column(name = "POSICAO_X") // REMOVIDO: precision = 10, scale = 2
    private Double posicaoX;

    @Column(name = "POSICAO_Y") // REMOVIDO: precision = 10, scale = 2
    private Double posicaoY;

    @Column(name = "ACURACIA_LOCALIZACAO") // REMOVIDO: precision = 5, scale = 2
    private Double acuraciaLocalizacao;

    @Column(name = "ORIGEM_DETECTADA", length = 20)
    private String origemDetectada;

    @Column(name = "STATUS_NO_MOMENTO", length = 20)
    private String statusNoMomento;
}
