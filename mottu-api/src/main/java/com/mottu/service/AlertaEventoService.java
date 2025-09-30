package com.mottu.service;

import com.mottu.entity.AlertaEvento;
import com.mottu.repository.AlertaEventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
        return alertaEventoRepository.findByIdMoto(motoId.intValue());
    }

    @Transactional(readOnly = true)
    public List<AlertaEvento> buscarPorPeriodo(LocalDate inicio, LocalDate fim) {
        return alertaEventoRepository.findByDataGeracaoBetween(inicio, fim);
    }

    @Transactional(readOnly = true)
    public List<AlertaEvento> buscarAlertasRecentes(int dias) {
        LocalDate dataLimite = LocalDate.now().minusDays(dias); // LocalDate, não LocalDateTime
        return alertaEventoRepository.findByDataGeracaoAfter(dataLimite);
    }

    @Transactional(readOnly = true)
    public List<AlertaEvento> buscarAlertasDeHoje() {
        LocalDate hoje = LocalDate.now(); // LocalDate, não LocalDateTime
        return buscarPorPeriodo(hoje, hoje);
    }

    @Transactional(readOnly = true)
    public long contarAlertasPorTipo(String tipoAlerta) {
        return alertaEventoRepository.countByTipoAlerta(tipoAlerta);
    }

    @Transactional(readOnly = true)
    public long contarAlertasPorMoto(Long motoId) {
        return alertaEventoRepository.countByIdMoto(motoId.intValue());
    }

    public AlertaEvento salvar(AlertaEvento alerta) {
        validarAlerta(alerta);
        return alertaEventoRepository.save(alerta);
    }

    public AlertaEvento criarAlerta(String tipoAlerta, Long motoId) {
        AlertaEvento alerta = AlertaEvento.builder()
                .tipoAlerta(tipoAlerta)
                .dataGeracao(LocalDate.now()) // LocalDate, não LocalDateTime
                .idMoto(motoId.intValue())
                .build();

        return salvar(alerta);
    }

    public void processarAlertas() {
        List<AlertaEvento> alertasRecentes = buscarAlertasRecentes(1);

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
        LocalDate dataLimite = LocalDate.now().minusDays(diasParaManterAlertas); // LocalDate, não LocalDateTime
        alertaEventoRepository.deleteByDataGeracaoBefore(dataLimite);
    }

    private void validarAlerta(AlertaEvento alerta) {
        if (alerta.getTipoAlerta() == null || alerta.getTipoAlerta().trim().isEmpty()) {
            throw new RuntimeException("Tipo de alerta é obrigatório");
        }

        if (alerta.getIdMoto() == null) {
            throw new RuntimeException("ID da moto é obrigatório para o alerta");
        }

        if (alerta.getDataGeracao() == null) {
            alerta.setDataGeracao(LocalDate.now()); // LocalDate, não LocalDateTime
        }
    }
}
