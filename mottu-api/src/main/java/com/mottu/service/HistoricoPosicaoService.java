package com.mottu.service;

import com.mottu.entity.HistoricoPosicao;
import com.mottu.repository.HistoricoPosicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HistoricoPosicaoService {

    @Autowired
    private HistoricoPosicaoRepository historicoRepository;

    @Transactional(readOnly = true)
    public List<HistoricoPosicao> listarTodos() {
        return historicoRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Page<HistoricoPosicao> listarTodos(Pageable pageable) {
        return historicoRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public List<HistoricoPosicao> buscarPorMoto(Long motoId) {
        return historicoRepository.findByIdMoto(motoId);
    }
    
    @Transactional(readOnly = true)
    public List<HistoricoPosicao> buscarPorMotoOrderByData(Long motoId) {
        return historicoRepository.findByIdMotoOrderByDataAtualizacaoDesc(motoId);
    }
    
    @Transactional(readOnly = true)
    public List<HistoricoPosicao> buscarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return historicoRepository.findByDataAtualizacaoBetween(inicio, fim);
    }
    
    @Transactional(readOnly = true)
    public List<HistoricoPosicao> buscarPorMotoEPeriodo(Long motoId, LocalDateTime inicio, LocalDateTime fim) {
        return historicoRepository.findByIdMotoAndDataAtualizacaoBetween(motoId, inicio, fim);
    }
    
    @Transactional(readOnly = true)
    public List<HistoricoPosicao> buscarPorOrigemDetectada(String origem) {
        return historicoRepository.findByOrigemDetectada(origem);
    }
    
    @Transactional(readOnly = true)
    public List<HistoricoPosicao> buscarPorStatusNoMomento(String status) {
        return historicoRepository.findByStatusNoMomento(status);
    }
    
    @Transactional(readOnly = true)
    public Optional<HistoricoPosicao> buscarUltimaPosicaoMoto(Long motoId) {
        List<HistoricoPosicao> historicos = historicoRepository.findByIdMotoOrderByDataAtualizacaoDesc(motoId);
        return historicos.isEmpty() ? Optional.empty() : Optional.of(historicos.get(0));
    }
    
    @Transactional(readOnly = true)
    public List<HistoricoPosicao> buscarHistoricoRecente(Long motoId, int horas) {
        LocalDateTime dataLimite = LocalDateTime.now().minusHours(horas);
        return historicoRepository.findByIdMotoAndDataAtualizacaoAfter(motoId, dataLimite);
    }
    
    @Transactional(readOnly = true)
    public long contarRegistrosPorMoto(Long motoId) {
        return historicoRepository.countByIdMoto(motoId);
    }

    public HistoricoPosicao salvar(HistoricoPosicao historico) {
        validarHistorico(historico);
        return historicoRepository.save(historico);
    }
    
    public HistoricoPosicao registrarPosicao(Long motoId, BigDecimal posX, BigDecimal posY, 
                                           BigDecimal acuracia, String origem, String status) {
        HistoricoPosicao historico = HistoricoPosicao.builder()
                .idMoto(motoId)
                .dataAtualizacao(LocalDateTime.now())
                .posicaoX(posX)
                .posicaoY(posY)
                .acuraciaLocalizacao(acuracia)
                .origemDetectada(origem)
                .statusNoMomento(status)
                .build();
        
        return salvar(historico);
    }
    
    public void limparHistoricoAntigo(int diasParaManterHistorico) {
        LocalDateTime dataLimite = LocalDateTime.now().minusDays(diasParaManterHistorico);
        historicoRepository.deleteByDataAtualizacaoBefore(dataLimite);
    }
    
    public List<HistoricoPosicao> gerarRelatorioMovimentacao(Long motoId, LocalDateTime inicio, LocalDateTime fim) {
        return buscarPorMotoEPeriodo(motoId, inicio, fim);
    }

    public void deletar(Long motoId, LocalDateTime dataAtualizacao) {
        historicoRepository.deleteByIdMotoAndDataAtualizacao(motoId, dataAtualizacao);
    }
    
    private void validarHistorico(HistoricoPosicao historico) {
        // Validar moto
        if (historico.getIdMoto() == null) {
            throw new RuntimeException("ID da moto é obrigatório");
        }
        
        // Validar posições
        if (historico.getPosicaoX() == null || historico.getPosicaoY() == null) {
            throw new RuntimeException("Posições X e Y são obrigatórias");
        }
        
        // Validar acurácia
        if (historico.getAcuraciaLocalizacao() == null || historico.getAcuraciaLocalizacao().compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Acurácia deve ser um valor positivo");
        }
        
        // Validar origem e status
        if (historico.getOrigemDetectada() == null || historico.getOrigemDetectada().trim().isEmpty()) {
            throw new RuntimeException("Origem detectada é obrigatória");
        }
        
        if (historico.getStatusNoMomento() == null || historico.getStatusNoMomento().trim().isEmpty()) {
            throw new RuntimeException("Status no momento é obrigatório");
        }
        
        // Definir data de atualização se não foi definida
        if (historico.getDataAtualizacao() == null) {
            historico.setDataAtualizacao(LocalDateTime.now());
        }
    }
}