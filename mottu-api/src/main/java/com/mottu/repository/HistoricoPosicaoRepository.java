package com.mottu.repository;

import com.mottu.entity.HistoricoPosicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HistoricoPosicaoRepository extends JpaRepository<HistoricoPosicao, Long> {

    // Busca por moto (INTEGER)
    List<HistoricoPosicao> findByIdMoto(Integer idMoto);
    List<HistoricoPosicao> findByIdMotoOrderByDataAtualizacaoDesc(Integer idMoto);
    List<HistoricoPosicao> findByIdMotoOrderByDataAtualizacaoAsc(Integer idMoto);

    // Busca por data de atualização (LocalDate)
    List<HistoricoPosicao> findByDataAtualizacao(LocalDate dataAtualizacao);
    List<HistoricoPosicao> findByDataAtualizacaoAfter(LocalDate data);
    List<HistoricoPosicao> findByDataAtualizacaoBefore(LocalDate data);
    List<HistoricoPosicao> findByDataAtualizacaoBetween(LocalDate inicio, LocalDate fim);

    // Busca por moto e período
    List<HistoricoPosicao> findByIdMotoAndDataAtualizacaoBetween(Integer idMoto, LocalDate inicio, LocalDate fim);
    List<HistoricoPosicao> findByIdMotoAndDataAtualizacaoAfter(Integer idMoto, LocalDate data);

    // Busca por origem detectada
    List<HistoricoPosicao> findByOrigemDetectada(String origemDetectada);
    List<HistoricoPosicao> findByIdMotoAndOrigemDetectada(Integer idMoto, String origemDetectada);

    // Busca por status no momento
    List<HistoricoPosicao> findByStatusNoMomento(String statusNoMomento);
    List<HistoricoPosicao> findByIdMotoAndStatusNoMomento(Integer idMoto, String statusNoMomento);

    // Contadores
    long countByIdMoto(Integer idMoto);
    long countByOrigemDetectada(String origem);
    long countByStatusNoMomento(String status);
    long countByDataAtualizacaoAfter(LocalDate data);

    // Delete por período
    @Modifying
    @Query("DELETE FROM HistoricoPosicao h WHERE h.dataAtualizacao < :dataLimite")
    void deleteByDataAtualizacaoBefore(@Param("dataLimite") LocalDate dataLimite);

    // Delete por moto e data específica
    @Modifying
    @Query("DELETE FROM HistoricoPosicao h WHERE h.idMoto = :idMoto AND h.dataAtualizacao = :dataAtualizacao")
    void deleteByIdMotoAndDataAtualizacao(@Param("idMoto") Integer idMoto, @Param("dataAtualizacao") LocalDate dataAtualizacao);

    // Delete todo histórico de uma moto
    @Modifying
    @Query("DELETE FROM HistoricoPosicao h WHERE h.idMoto = :idMoto")
    void deleteByIdMoto(@Param("idMoto") Integer idMoto);
}
