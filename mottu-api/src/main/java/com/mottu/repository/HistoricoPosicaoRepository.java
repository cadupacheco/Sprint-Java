package com.mottu.repository;

import com.mottu.entity.HistoricoPosicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HistoricoPosicaoRepository extends JpaRepository<HistoricoPosicao, Long> {
    
    // Busca por moto
    List<HistoricoPosicao> findByIdMoto(Long idMoto);
    List<HistoricoPosicao> findByIdMotoOrderByDataAtualizacaoDesc(Long idMoto);
    List<HistoricoPosicao> findByIdMotoOrderByDataAtualizacaoAsc(Long idMoto);
    
    // Busca por data de atualização
    List<HistoricoPosicao> findByDataAtualizacao(LocalDateTime dataAtualizacao);
    List<HistoricoPosicao> findByDataAtualizacaoAfter(LocalDateTime data);
    List<HistoricoPosicao> findByDataAtualizacaoBefore(LocalDateTime data);
    List<HistoricoPosicao> findByDataAtualizacaoBetween(LocalDateTime inicio, LocalDateTime fim);
    
    // Busca por moto e período
    List<HistoricoPosicao> findByIdMotoAndDataAtualizacaoBetween(Long idMoto, LocalDateTime inicio, LocalDateTime fim);
    List<HistoricoPosicao> findByIdMotoAndDataAtualizacaoAfter(Long idMoto, LocalDateTime data);
    
    // Busca por origem detectada
    List<HistoricoPosicao> findByOrigemDetectada(String origemDetectada);
    List<HistoricoPosicao> findByIdMotoAndOrigemDetectada(Long idMoto, String origemDetectada);
    
    // Busca por status no momento
    List<HistoricoPosicao> findByStatusNoMomento(String statusNoMomento);
    List<HistoricoPosicao> findByIdMotoAndStatusNoMomento(Long idMoto, String statusNoMomento);
    
    // Query para última posição de uma moto
    @Query("SELECT h FROM HistoricoPosicao h WHERE h.idMoto = :idMoto ORDER BY h.dataAtualizacao DESC LIMIT 1")
    HistoricoPosicao findUltimaPosicaoByMoto(@Param("idMoto") Long idMoto);
    
    // Query para posições em uma área específica (retângulo)
    @Query("SELECT h FROM HistoricoPosicao h WHERE h.posicaoX BETWEEN :minX AND :maxX AND h.posicaoY BETWEEN :minY AND :maxY")
    List<HistoricoPosicao> findPosicoesNaArea(@Param("minX") Double minX, @Param("maxX") Double maxX, 
                                             @Param("minY") Double minY, @Param("maxY") Double maxY);
    
    // Query para trilha de uma moto em um período
    @Query("SELECT h FROM HistoricoPosicao h WHERE h.idMoto = :idMoto AND h.dataAtualizacao BETWEEN :inicio AND :fim ORDER BY h.dataAtualizacao ASC")
    List<HistoricoPosicao> findTrilhaMoto(@Param("idMoto") Long idMoto, 
                                         @Param("inicio") LocalDateTime inicio, 
                                         @Param("fim") LocalDateTime fim);
    
    // Query para motos em movimento (múltiplas posições diferentes)
    @Query("SELECT DISTINCT h.idMoto FROM HistoricoPosicao h WHERE h.dataAtualizacao >= :dataLimite GROUP BY h.idMoto HAVING COUNT(DISTINCT CONCAT(h.posicaoX, ',', h.posicaoY)) > 1")
    List<Long> findMotosEmMovimento(@Param("dataLimite") LocalDateTime dataLimite);
    
    // Query para relatório de movimentação
    @Query("SELECT h.idMoto, COUNT(h), MIN(h.dataAtualizacao), MAX(h.dataAtualizacao) FROM HistoricoPosicao h WHERE h.dataAtualizacao BETWEEN :inicio AND :fim GROUP BY h.idMoto")
    List<Object[]> findRelatorioMovimentacao(@Param("inicio") LocalDateTime inicio, @Param("fim") LocalDateTime fim);
    
    // Contadores
    long countByIdMoto(Long idMoto);
    long countByOrigemDetectada(String origem);
    long countByStatusNoMomento(String status);
    long countByDataAtualizacaoAfter(LocalDateTime data);
    
    // Contar registros por período
    @Query("SELECT COUNT(h) FROM HistoricoPosicao h WHERE h.dataAtualizacao BETWEEN :inicio AND :fim")
    long countRegistrosPorPeriodo(@Param("inicio") LocalDateTime inicio, @Param("fim") LocalDateTime fim);
    
    // Delete por período (limpeza de histórico antigo)
    @Modifying
    @Query("DELETE FROM HistoricoPosicao h WHERE h.dataAtualizacao < :dataLimite")
    void deleteByDataAtualizacaoBefore(@Param("dataLimite") LocalDateTime dataLimite);
    
    // Delete por moto e data específica
    @Modifying
    @Query("DELETE FROM HistoricoPosicao h WHERE h.idMoto = :idMoto AND h.dataAtualizacao = :dataAtualizacao")
    void deleteByIdMotoAndDataAtualizacao(@Param("idMoto") Long idMoto, @Param("dataAtualizacao") LocalDateTime dataAtualizacao);
    
    // Delete todo histórico de uma moto
    @Modifying
    @Query("DELETE FROM HistoricoPosicao h WHERE h.idMoto = :idMoto")
    void deleteByIdMoto(@Param("idMoto") Long idMoto);
}