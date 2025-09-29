package com.mottu.controller;

import com.mottu.entity.Moto;
import com.mottu.service.MotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/motos")
public class MotoController {

    @Autowired
    private MotoService motoService;

    @GetMapping
    public List<Moto> listar() {
        return motoService.listarTodas();
    }

    @GetMapping("/{id}")
    public Optional<Moto> buscarPorId(@PathVariable Long id) {
        return motoService.buscarPorId(id);
    }

    @PostMapping
    public Moto criar(@RequestBody Moto moto) {
        return motoService.salvar(moto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        motoService.deletar(id);
    }
}