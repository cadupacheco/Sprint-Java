package com.mottu.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Sensor_iot")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SensorIot {
    
    @Id
    @Column(name = "id_sensor_iot")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSensorIot;
    
    @NotBlank(message = "Tipo do sensor é obrigatório")
    @Size(max = 20, message = "Tipo do sensor deve ter no máximo 20 caracteres")
    @Column(name = "tipo_sensor", length = 20)
    private String tipoSensor;
    
    @NotNull(message = "Data de transmissão é obrigatória")
    @Column(name = "data_transmissao")
    private LocalDate dataTransmissao;
    
    @NotNull(message = "Percentual da bateria é obrigatório")
    @DecimalMin(value = "0.0", message = "Percentual da bateria deve ser positivo")
    @DecimalMax(value = "100.0", message = "Percentual da bateria não pode exceder 100%")
    @Column(name = "bateria_percentual", precision = 5, scale = 2)
    private BigDecimal bateriaPercentual;
    
    @OneToOne(mappedBy = "sensorIot", fetch = FetchType.LAZY)
    private Moto moto;
}