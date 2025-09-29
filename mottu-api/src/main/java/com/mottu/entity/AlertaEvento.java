package com.mottu.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Alerta_evento")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlertaEvento {
    
    @Id
    @Column(name = "id_alerta")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAlerta;
    
    @NotBlank(message = "Tipo de alerta é obrigatório")
    @Size(max = 50, message = "Tipo de alerta deve ter no máximo 50 caracteres")
    @Column(name = "tipo_alerta", length = 50)
    private String tipoAlerta;
    
    @NotNull(message = "Data de geração é obrigatória")
    @Column(name = "data_geracao")
    private LocalDateTime dataGeracao;
    
    @NotNull(message = "Moto é obrigatória")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_moto")
    private Moto moto;
    
    @PrePersist
    private void setDataGeracao() {
        if (this.dataGeracao == null) {
            this.dataGeracao = LocalDateTime.now();
        }
    }
}