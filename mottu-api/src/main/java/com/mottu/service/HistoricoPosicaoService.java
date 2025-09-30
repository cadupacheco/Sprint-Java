package com.mottu.service;

import com.mottu.entity.HistoricoPosicao;
import com.mottu.repository.HistoricoPosicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
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
        return historicoRepository.findByIdMoto(motoId.intValue());
    }

    @Transactional(readOnly = true)
    public List<HistoricoPosicao> buscarPorMotoOrderByData(Long motoId) {
        return historicoRepository.findByIdMotoOrderByDataAtualizacaoDesc(motoId.intValue());
    }

    @Transactional(readOnly = true)
    public List<HistoricoPosicao> buscarPorPeriodo(LocalDate inicio, LocalDate fim) {
        return historicoRepository.findByDataAtualizacaoBetween(inicio, fim);
    }

    @Transactional(readOnly = true)
    public List<HistoricoPosicao> buscarPorMotoEPeriodo(Long motoId, LocalDate inicio, LocalDate fim) {
        return historicoRepository.findByIdMotoAndDataAtualizacaoBetween(motoId.intValue(), inicio, fim);
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
        List<HistoricoPosicao> historicos = historicoRepository.findByIdMotoOrderByDataAtualizacaoDesc(motoId.intValue());
        return historicos.isEmpty() ? Optional.empty() : Optional.of(historicos.get(0));
    }

    @Transactional(readOnly = true)
    public List<HistoricoPosicao> buscarHistoricoRecente(Long motoId, int dias) {
        LocalDate dataLimite = LocalDate.now().minusDays(dias);
        return historicoRepository.findByIdMotoAndDataAtualizacaoAfter(motoId.intValue(), dataLimite);
    }

    @Transactional(readOnly = true)
    public long contarRegistrosPorMoto(Long motoId) {
        return historicoRepository.countByIdMoto(motoId.intValue());
    }

    public HistoricoPosicao salvar(HistoricoPosicao historico) {
        validarHistorico(historico);
        return historicoRepository.save(historico);
    }

    public HistoricoPosicao registrarPosicao(Long motoId, BigDecimal posX, BigDecimal posY,
                                             BigDecimal acuracia, String origem, String status) {
        HistoricoPosicao historico = HistoricoPosicao.builder()
                .idMoto(motoId.intValue())
                .dataAtualizacao(LocalDate.now())
                .posicaoX(posX.doubleValue())
                .posicaoY(posY.doubleValue())
                .acuraciaLocalizacao(acuracia.doubleValue())
                .origemDetectada(origem)
                .statusNoMomento(status)
                .build();

        return salvar(historico);
    }

    public void limparHistoricoAntigo(int diasParaManterHistorico) {
        LocalDate dataLimite = LocalDate.now().minusDays(diasParaManterHistorico);
        historicoRepository.deleteByDataAtualizacaoBefore(dataLimite);
    }

    public void deletar(Long motoId, LocalDate dataAtualizacao) {
        historicoRepository.deleteByIdMotoAndDataAtualizacao(motoId.intValue(), dataAtualizacao);
    }

    private void validarHistorico(HistoricoPosicao historico) {
        if (historico.getIdMoto() == null) {
            throw new RuntimeException("ID da moto é obrigatório");
        }

        if (historico.getPosicaoX() == null || historico.getPosicaoY() == null) {
            throw new RuntimeException("Posições X e Y são obrigatórias");
        }

        if (historico.getAcuraciaLocalizacao() == null || historico.getAcuraciaLocalizacao() < 0) {
            throw new RuntimeException("Acurácia deve ser um valor positivo");
        }

        if (historico.getOrigemDetectada() == null || historico.getOrigemDetectada().trim().isEmpty()) {
            throw new RuntimeException("Origem detectada é obrigatória");
        }

        if (historico.getStatusNoMomento() == null || historico.getStatusNoMomento().trim().isEmpty()) {
            throw new RuntimeException("Status no momento é obrigatório");
        }

        if (historico.getDataAtualizacao() == null) {
            historico.setDataAtualizacao(LocalDate.now());
        }
    }
}
