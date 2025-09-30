package com.mottu.service;

import com.mottu.entity.Modelo;
import com.mottu.repository.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ModeloService {

    @Autowired
    private ModeloRepository modeloRepository;

    @Transactional(readOnly = true)
    public List<Modelo> listarTodos() {
        return modeloRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<Modelo> listarTodos(Pageable pageable) {
        return modeloRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<Modelo> buscarPorId(Long id) {
        return modeloRepository.findById(id.intValue()); // Converte Long->Integer
    }

    @Transactional(readOnly = true)
    public List<Modelo> buscarPorFabricante(String fabricante) {
        return modeloRepository.findByFabricante(fabricante);
    }

    @Transactional(readOnly = true)
    public Optional<Modelo> buscarPorNomeModelo(String nomeModelo) {
        return modeloRepository.findByNomeModelo(nomeModelo);
    }

    @Transactional(readOnly = true)
    public List<Modelo> buscarPorTipo(String tipo) {
        return modeloRepository.findByTipo(tipo);
    }

    @Transactional(readOnly = true)
    public List<Modelo> buscarPorCilindrada(Integer cilindrada) {
        return modeloRepository.findByCilindrada(cilindrada);
    }

    @Transactional(readOnly = true)
    public List<Modelo> buscarPorCilindradaFaixa(Integer min, Integer max) {
        return modeloRepository.findByCilindradaBetween(min, max);
    }

    @Transactional(readOnly = true)
    public List<Modelo> buscarModelosMaisUtilizados() {
        return modeloRepository.findModelosMaisUtilizados();
    }

    @Transactional(readOnly = true)
    public long contarMotosPorModelo(Long modeloId) {
        return modeloRepository.countMotosByModeloId(modeloId.intValue()); // Converte Long->Integer
    }

    public Modelo salvar(Modelo modelo) {
        validarModelo(modelo);
        return modeloRepository.save(modelo);
    }

    public void deletar(Long id) {
        if (!modeloRepository.existsById(id.intValue())) {
            throw new RuntimeException("Modelo não encontrado com ID: " + id);
        }

        long motosVinculadas = contarMotosPorModelo(id);
        if (motosVinculadas > 0) {
            throw new RuntimeException("Não é possível deletar o modelo. Existem " + motosVinculadas + " motos vinculadas.");
        }

        modeloRepository.deleteById(id.intValue());
    }

    private void validarModelo(Modelo modelo) {
        if (modelo.getIdModelo() == null || !modelo.getNomeModelo().equals(buscarPorId(modelo.getIdModelo().longValue()).map(Modelo::getNomeModelo).orElse(null))) {
            if (buscarPorNomeModelo(modelo.getNomeModelo()).isPresent()) {
                throw new RuntimeException("Já existe um modelo com este nome: " + modelo.getNomeModelo());
            }
        }

        if (modelo.getCilindrada() <= 0) {
            throw new RuntimeException("Cilindrada deve ser um valor positivo");
        }
    }
}
