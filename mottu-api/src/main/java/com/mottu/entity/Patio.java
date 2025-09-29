package com.mottu.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Patio")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "motos")
@ToString(exclude = "motos")
public class Patio {
    
    @Id
    @Column(name = "id_patio")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPatio;
    
    @NotBlank(message = "Nome do pátio é obrigatório")
    @Size(max = 50, message = "Nome do pátio deve ter no máximo 50 caracteres")
    @Column(name = "nome_patio", length = 50)
    private String nomePatio;
    
    @NotBlank(message = "Localização é obrigatória")
    @Size(max = 50, message = "Localização deve ter no máximo 50 caracteres")
    @Column(name = "localizacao_patio", length = 50)
    private String localizacaoPatio;
    
    @NotNull(message = "Área total é obrigatória")
    @DecimalMin(value = "0.0", inclusive = false, message = "Área total deve ser positiva")
    @Column(name = "area_total", precision = 10, scale = 2)
    private BigDecimal areaTotal;
    
    @NotNull(message = "Capacidade máxima é obrigatória")
    @Positive(message = "Capacidade máxima deve ser positiva")
    @Column(name = "capacidade_maxima")
    private Integer capacidadeMaxima;
    
    @OneToMany(mappedBy = "patio", fetch = FetchType.LAZY)
    private List<Moto> motos;
}