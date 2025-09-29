package com.mottu.repository;

import com.mottu.entity.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Long> {
    
    // Busca por fabricante
    List<Modelo> findByFabricante(String fabricante);
    
    // Busca por nome do modelo
    Optional<Modelo> findByNomeModelo(String nomeModelo);
    
    // Busca por fabricante e nome do modelo
    Optional<Modelo> findByFabricanteAndNomeModelo(String fabricante, String nomeModelo);
    
    // Busca por tipo
    List<Modelo> findByTipo(String tipo);
    
    // Busca por cilindrada
    List<Modelo> findByCilindrada(Integer cilindrada);
    List<Modelo> findByCilindradaBetween(Integer min, Integer max);
    
    // Query customizada para modelos mais utilizados
    @Query("SELECT m FROM Modelo m JOIN m.motos mo GROUP BY m ORDER BY COUNT(mo) DESC")
    List<Modelo> findModelosMaisUtilizados();
    
    // Contar motos por modelo
    @Query("SELECT COUNT(mo) FROM Moto mo WHERE mo.modelo.idModelo = :modeloId")
    long countMotosByModeloId(Long modeloId);
}