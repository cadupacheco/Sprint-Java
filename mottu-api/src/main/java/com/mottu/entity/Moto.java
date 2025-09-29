package com.mottu.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Moto")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"historicoPosicoes", "alertaEventos"})
@ToString(exclude = {"historicoPosicoes", "alertaEventos"})
public class Moto {
    
    @Id
    @Column(name = "id_moto")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMoto;
    
    @NotBlank(message = "Placa é obrigatória")
    @Size(max = 10, message = "Placa deve ter no máximo 10 caracteres")
    @Pattern(regexp = "^[A-Z]{3}[0-9][0-9A-Z][0-9]{2}$", message = "Placa deve seguir o padrão brasileiro")
    @Column(name = "placa", length = 10, unique = true)
    private String placa;
    
    @NotBlank(message = "Chassi é obrigatório")
    @Size(max = 50, message = "Chassi deve ter no máximo 50 caracteres")
    @Column(name = "chassi", length = 50, unique = true)
    private String chassi;
    
    @NotNull(message = "Ano de fabricação é obrigatório")
    @Min(value = 1990, message = "Ano de fabricação deve ser maior que 1990")
    @Max(value = 2030, message = "Ano de fabricação não pode ser futuro")
    @Column(name = "ano_fabricacao")
    private Integer anoFabricacao;
    
    @NotBlank(message = "Status é obrigatório")
    @Size(max = 20, message = "Status deve ter no máximo 20 caracteres")
    @Column(name = "status", length = 20)
    private String status;
    
    @NotNull(message = "Modelo é obrigatório")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Modelo_id_modelo")
    private Modelo modelo;
    
    @NotNull(message = "Pátio é obrigatório")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_patio")
    private Patio patio;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sensor_iot")
    private SensorIot sensorIot;
    
    @Column(name = "data_atualizacao")
    private LocalDate dataAtualizacao;
    
    @OneToMany(mappedBy = "moto", fetch = FetchType.LAZY)
    private List<HistoricoPosicao> historicoPosicoes;
    
    @OneToMany(mappedBy = "moto", fetch = FetchType.LAZY)
    private List<AlertaEvento> alertaEventos;
    
    @PrePersist
    @PreUpdate
    private void setDataAtualizacao() {
        this.dataAtualizacao = LocalDate.now();
    }
}