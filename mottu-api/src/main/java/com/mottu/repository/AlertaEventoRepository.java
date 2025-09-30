package com.mottu.repository;

import com.mottu.entity.AlertaEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AlertaEventoRepository extends JpaRepository<AlertaEvento, Long> {

    // Busca por tipo de alerta
    List<AlertaEvento> findByTipoAlerta(String tipoAlerta);

    // Busca por moto (INTEGER)
    List<AlertaEvento> findByIdMoto(Integer idMoto);

    // Busca por data de geração (LocalDate - DATE no banco)
    List<AlertaEvento> findByDataGeracao(LocalDate dataGeracao);
    List<AlertaEvento> findByDataGeracaoAfter(LocalDate data);
    List<AlertaEvento> findByDataGeracaoBefore(LocalDate data);
    List<AlertaEvento> findByDataGeracaoBetween(LocalDate inicio, LocalDate fim);

    // Busca por tipo e moto
    List<AlertaEvento> findByTipoAlertaAndIdMoto(String tipoAlerta, Integer idMoto);

    // Ordenação por data mais recente
    List<AlertaEvento> findAllByOrderByDataGeracaoDesc();
    List<AlertaEvento> findByIdMotoOrderByDataGeracaoDesc(Integer idMoto);
    List<AlertaEvento> findByTipoAlertaOrderByDataGeracaoDesc(String tipoAlerta);

    // Contadores
    long countByTipoAlerta(String tipoAlerta);
    long countByIdMoto(Integer idMoto);
    long countByDataGeracaoAfter(LocalDate data);

    // Delete por data (LocalDate)
    @Modifying
    @Query("DELETE FROM AlertaEvento a WHERE a.dataGeracao < :dataLimite")
    void deleteByDataGeracaoBefore(@Param("dataLimite") LocalDate dataLimite);

    // Delete por moto
    @Modifying
    @Query("DELETE FROM AlertaEvento a WHERE a.idMoto = :idMoto")
    void deleteByIdMoto(@Param("idMoto") Integer idMoto);
}
