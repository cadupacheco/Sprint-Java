package com.mottu.repository;

import com.mottu.entity.SensorIot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface SensorIotRepository extends JpaRepository<SensorIot, Long> {
    
    // Busca por tipo de sensor
    List<SensorIot> findByTipoSensor(String tipoSensor);
    
    // Busca por data de transmissão
    List<SensorIot> findByDataTransmissao(LocalDate dataTransmissao);
    List<SensorIot> findByDataTransmissaoAfter(LocalDate data);
    List<SensorIot> findByDataTransmissaoBefore(LocalDate data);
    List<SensorIot> findByDataTransmissaoBetween(LocalDate inicio, LocalDate fim);
    
    // Busca por percentual de bateria
    List<SensorIot> findByBateriaPercentual(BigDecimal percentual);
    List<SensorIot> findByBateriaPercentualLessThan(BigDecimal percentual);
    List<SensorIot> findByBateriaPercentualGreaterThan(BigDecimal percentual);
    List<SensorIot> findByBateriaPercentualBetween(BigDecimal min, BigDecimal max);
    
    // Query customizada para sensores sem moto associada
    @Query("SELECT s FROM SensorIot s WHERE s.moto IS NULL")
    List<SensorIot> findSensoresSemMoto();
    
    // Query para sensores com bateria crítica (menos de 20%)
    @Query("SELECT s FROM SensorIot s WHERE s.bateriaPercentual < 20")
    List<SensorIot> findSensoresComBateriaCritica();
    
    // Query para sensores ativos (transmissão recente)
    @Query("SELECT s FROM SensorIot s WHERE s.dataTransmissao >= :dataLimite")
    List<SensorIot> findSensoresAtivos(LocalDate dataLimite);
    
    // Contar sensores por tipo
    long countByTipoSensor(String tipoSensor);
    
    // Contar sensores com bateria baixa
    long countByBateriaPercentualLessThan(BigDecimal limite);
}