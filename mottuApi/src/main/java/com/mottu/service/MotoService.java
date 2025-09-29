package com.mottu.service;

import com.mottu.entity.Moto;
import com.mottu.repository.MotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MotoService {

    @Autowired
    private MotoRepository motoRepository;

    public List<Moto> listarTodas() {
        return motoRepository.findAll();
    }

    public Optional<Moto> buscarPorId(Long id) {
        return motoRepository.findById(id);
    }

    public Moto salvar(Moto moto) {
        return motoRepository.save(moto);
    }

    public void deletar(Long id) {
        motoRepository.deleteById(id);
    }
}