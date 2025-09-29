package com.mottu.service;

import com.mottu.entity.AlertaEvento;
import com.mottu.repository.AlertaEventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AlertaEventoService {

    @Autowired
    private AlertaEventoRepository alertaEventoRepository;

    @Transactional(readOnly = true)
    public List<AlertaEvento> listarTodos() {
        return alertaEventoRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Page<AlertaEvento> listarTodos(Pageable pageable) {
        return alertaEventoRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<AlertaEvento> buscarPorId(Long id) {
        return alertaEventoRepository.findById(id);
    }
    
    @Transactional(readOnly = true)
    public List<AlertaEvento> buscarPorTipoAlerta(String tipoAlerta) {
        return alertaEventoRepository.findByTipoAlerta(tipoAlerta);
    }
    
    @Transactional(readOnly = true)
    public List<AlertaEvento> buscarPorMoto(Long motoId) {
        return alertaEventoRepository.findByMoto_IdMoto(motoId);
    }
    
    @Transactional(readOnly = true)
    public List<AlertaEvento> buscarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return alertaEventoRepository.findByDataGeracaoBetween(inicio, fim);
    }
    
    @Transactional(readOnly = true)
    public List<AlertaEvento> buscarAlertasRecentes(int dias) {
        LocalDateTime dataLimite = LocalDateTime.now().minusDays(dias);
        return alertaEventoRepository.findByDataGeracaoAfter(dataLimite);
    }
    
    @Transactional(readOnly = true)
    public List<AlertaEvento> buscarAlertasDeHoje() {
        LocalDateTime inicioHoje = LocalDateTime.now().toLocalDate().atStartOfDay();
        LocalDateTime fimHoje = inicioHoje.plusDays(1).minusSeconds(1);
        return buscarPorPeriodo(inicioHoje, fimHoje);
    }
    
    @Transactional(readOnly = true)
    public long contarAlertasPorTipo(String tipoAlerta) {
        return alertaEventoRepository.countByTipoAlerta(tipoAlerta);
    }
    
    @Transactional(readOnly = true)
    public long contarAlertasPorMoto(Long motoId) {
        return alertaEventoRepository.countByMoto_IdMoto(motoId);
    }

    public AlertaEvento salvar(AlertaEvento alerta) {
        validarAlerta(alerta);
        return alertaEventoRepository.save(alerta);
    }
    
    public AlertaEvento criarAlerta(String tipoAlerta, Long motoId) {
        AlertaEvento alerta = AlertaEvento.builder()
                .tipoAlerta(tipoAlerta)
                .dataGeracao(LocalDateTime.now())
                .build();
        
        // Buscar a moto pelo ID e associar
        // Nota: Aqui você precisaria injetar MotoService ou MotoRepository
        // Por simplicidade, estou deixando que seja definido externamente
        
        return salvar(alerta);
    }
    
    public void processarAlertas() {
        // Método para processar alertas pendentes
        // Implementar lógica de negócio específica
        List<AlertaEvento> alertasRecentes = buscarAlertasRecentes(1);
        
        // Exemplo: agrupar por tipo e gerar relatórios
        alertasRecentes.stream()
                .collect(java.util.stream.Collectors.groupingBy(AlertaEvento::getTipoAlerta))
                .forEach((tipo, alertas) -> {
                    System.out.println("Tipo: " + tipo + " - Quantidade: " + alertas.size());
                });
    }

    public void deletar(Long id) {
        if (!alertaEventoRepository.existsById(id)) {
            throw new RuntimeException("Alerta não encontrado com ID: " + id);
        }
        alertaEventoRepository.deleteById(id);
    }
    
    public void deletarAlertasAntigos(int diasParaManterAlertas) {
        LocalDateTime dataLimite = LocalDateTime.now().minusDays(diasParaManterAlertas);
        alertaEventoRepository.deleteByDataGeracaoBefore(dataLimite);
    }
    
    private void validarAlerta(AlertaEvento alerta) {
        // Validar tipo do alerta
        if (alerta.getTipoAlerta() == null || alerta.getTipoAlerta().trim().isEmpty()) {
            throw new RuntimeException("Tipo de alerta é obrigatório");
        }
        
        // Validar moto
        if (alerta.getMoto() == null) {
            throw new RuntimeException("Moto é obrigatória para o alerta");
        }
        
        // Definir data de geração se não foi definida
        if (alerta.getDataGeracao() == null) {
            alerta.setDataGeracao(LocalDateTime.now());
        }
    }
}