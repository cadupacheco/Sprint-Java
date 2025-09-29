package com.mottu.repository;

import com.mottu.entity.Patio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatioRepository extends JpaRepository<Patio, Long> {
    
    // Busca por nome do pátio
    Optional<Patio> findByNomePatio(String nomePatio);
    
    // Busca por localização
    List<Patio> findByLocalizacaoPatio(String localizacao);
    
    // Busca por localização contendo
    List<Patio> findByLocalizacaoPatioContainingIgnoreCase(String localizacao);
    
    // Busca por capacidade máxima
    List<Patio> findByCapacidadeMaxima(Integer capacidade);
    List<Patio> findByCapacidadeMaximaGreaterThanEqual(Integer capacidadeMinima);
    
    // Busca por área total
    List<Patio> findByAreaTotalBetween(BigDecimal areaMin, BigDecimal areaMax);
    
    // Query para pátios com mais espaço disponível
    @Query("SELECT p FROM Patio p WHERE (SELECT COUNT(m) FROM Moto m WHERE m.patio.idPatio = p.idPatio) < p.capacidadeMaxima ORDER BY (p.capacidadeMaxima - (SELECT COUNT(m) FROM Moto m WHERE m.patio.idPatio = p.idPatio)) DESC")
    List<Patio> findPatiosComEspacoDisponivel();
    
    // Contar motos no pátio
    @Query("SELECT COUNT(m) FROM Moto m WHERE m.patio.idPatio = :patioId")
    long countMotosByPatioId(Long patioId);
    
    // Calcular taxa de ocupação
    @Query("SELECT (COUNT(m) * 100.0 / p.capacidadeMaxima) FROM Patio p LEFT JOIN Moto m ON m.patio.idPatio = p.idPatio WHERE p.idPatio = :patioId GROUP BY p.idPatio, p.capacidadeMaxima")
    Double calcularTaxaOcupacao(Long patioId);
}