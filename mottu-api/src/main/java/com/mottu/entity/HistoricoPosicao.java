package com.mottu.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Historico_posicao")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(HistoricoPosicaoId.class)
public class HistoricoPosicao {
    
    @Id
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;
    
    @Id
    @Column(name = "id_moto")
    private Long idMoto;
    
    @NotNull(message = "Posição X é obrigatória")
    @Column(name = "posicao_x", precision = 10, scale = 2)
    private BigDecimal posicaoX;
    
    @NotNull(message = "Posição Y é obrigatória")
    @Column(name = "posicao_y", precision = 10, scale = 2)
    private BigDecimal posicaoY;
    
    @NotNull(message = "Acurácia é obrigatória")
    @DecimalMin(value = "0.0", message = "Acurácia deve ser positiva")
    @Column(name = "acuracia_localizacao", precision = 5, scale = 2)
    private BigDecimal acuraciaLocalizacao;
    
    @NotBlank(message = "Origem detectada é obrigatória")
    @Size(max = 20, message = "Origem detectada deve ter no máximo 20 caracteres")
    @Column(name = "origem_detectada", length = 20)
    private String origemDetectada;
    
    @NotBlank(message = "Status no momento é obrigatório")
    @Size(max = 20, message = "Status no momento deve ter no máximo 20 caracteres")
    @Column(name = "status_no_momento", length = 20)
    private String statusNoMomento;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_moto", insertable = false, updatable = false)
    private Moto moto;
}

// Classe de ID composta
@Data
@NoArgsConstructor
@AllArgsConstructor
class HistoricoPosicaoId implements Serializable {
    private LocalDateTime dataAtualizacao;
    private Long idMoto;
}