package com.mottu.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Modelo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "motos")
@ToString(exclude = "motos")
public class Modelo {
    
    @Id
    @Column(name = "id_modelo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idModelo;
    
    @NotBlank(message = "Fabricante é obrigatório")
    @Size(max = 50, message = "Fabricante deve ter no máximo 50 caracteres")
    @Column(name = "fabricante", length = 50)
    private String fabricante;
    
    @NotBlank(message = "Nome do modelo é obrigatório")
    @Size(max = 50, message = "Nome do modelo deve ter no máximo 50 caracteres")
    @Column(name = "nome_modelo", length = 50)
    private String nomeModelo;
    
    @NotNull(message = "Cilindrada é obrigatória")
    @Positive(message = "Cilindrada deve ser positiva")
    @Column(name = "cilindrada")
    private Integer cilindrada;
    
    @NotBlank(message = "Tipo é obrigatório")
    @Size(max = 30, message = "Tipo deve ter no máximo 30 caracteres")
    @Column(name = "tipo", length = 30)
    private String tipo;
    
    @OneToMany(mappedBy = "modelo", fetch = FetchType.LAZY)
    private List<Moto> motos;
}