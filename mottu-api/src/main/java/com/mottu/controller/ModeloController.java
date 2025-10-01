package com.mottu.controller;

import java.util.Collections;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mottu.entity.Modelo;

@Controller
public class ModeloController {

    @GetMapping("/modelos")
    public String listar(Model model) {
        model.addAttribute("modelos", Collections.emptyList());
        return "modelo/modelo-list";
    }

    @GetMapping("/modelos/novo")
    public String novo(Model model) {
        model.addAttribute("modelo", new Modelo());
        return "modelo/modelo-form";
    }

    @GetMapping("/modelos/{id}")
    public String detalhe(@PathVariable Long id, Model model) {
        model.addAttribute("modelo", new Modelo());
        return "modelo/modelo-view";
    }
}
