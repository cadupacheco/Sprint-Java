package com.mottu.repository;

import com.mottu.entity.AlertaEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AlertaEventoRepository extends JpaRepository<AlertaEvento, Long> {
    
    // Busca por tipo de alerta
    List<AlertaEvento> findByTipoAlerta(String tipoAlerta);
    
    // Busca por moto
    List<AlertaEvento> findByMoto_IdMoto(Long motoId);
    
    // Busca por data de geração
    List<AlertaEvento> findByDataGeracao(LocalDateTime dataGeracao);
    List<AlertaEvento> findByDataGeracaoAfter(LocalDateTime data);
    List<AlertaEvento> findByDataGeracaoBefore(LocalDateTime data);
    List<AlertaEvento> findByDataGeracaoBetween(LocalDateTime inicio, LocalDateTime fim);
    
    // Busca por tipo e moto
    List<AlertaEvento> findByTipoAlertaAndMoto_IdMoto(String tipoAlerta, Long motoId);
    
    // Ordenação por data mais recente
    List<AlertaEvento> findAllByOrderByDataGeracaoDesc();
    List<AlertaEvento> findByMoto_IdMotoOrderByDataGeracaoDesc(Long motoId);
    List<AlertaEvento> findByTipoAlertaOrderByDataGeracaoDesc(String tipoAlerta);
    
    // Query para alertas do dia
    @Query("SELECT a FROM AlertaEvento a WHERE DATE(a.dataGeracao) = DATE(:data)")
    List<AlertaEvento> findAlertasDoDia(@Param("data") LocalDateTime data);
    
    // Query para alertas da última hora
    @Query("SELECT a FROM AlertaEvento a WHERE a.dataGeracao >= :dataLimite ORDER BY a.dataGeracao DESC")
    List<AlertaEvento> findAlertasUltimaHora(@Param("dataLimite") LocalDateTime dataLimite);
    
    // Query para relatório de alertas por período
    @Query("SELECT a.tipoAlerta, COUNT(a) FROM AlertaEvento a WHERE a.dataGeracao BETWEEN :inicio AND :fim GROUP BY a.tipoAlerta")
    List<Object[]> findRelatorioAlertasPorTipo(@Param("inicio") LocalDateTime inicio, @Param("fim") LocalDateTime fim);
    
    // Query para top motos com mais alertas
    @Query("SELECT a.moto.idMoto, a.moto.placa, COUNT(a) as total FROM AlertaEvento a GROUP BY a.moto.idMoto, a.moto.placa ORDER BY total DESC")
    List<Object[]> findTopMotosComMaisAlertas();
    
    // Contadores
    long countByTipoAlerta(String tipoAlerta);
    long countByMoto_IdMoto(Long motoId);
    long countByDataGeracaoAfter(LocalDateTime data);
    
    // Contar alertas por período
    @Query("SELECT COUNT(a) FROM AlertaEvento a WHERE a.dataGeracao BETWEEN :inicio AND :fim")
    long countAlertasPorPeriodo(@Param("inicio") LocalDateTime inicio, @Param("fim") LocalDateTime fim);
    
    // Delete por data (limpeza de alertas antigos)
    @Modifying
    @Query("DELETE FROM AlertaEvento a WHERE a.dataGeracao < :dataLimite")
    void deleteByDataGeracaoBefore(@Param("dataLimite") LocalDateTime dataLimite);
    
    // Delete por moto
    @Modifying
    @Query("DELETE FROM AlertaEvento a WHERE a.moto.idMoto = :motoId")
    void deleteByMotoId(@Param("motoId") Long motoId);
}