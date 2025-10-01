package com.mottu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ModeloController {

    @GetMapping("/modelos")
    public String listar(Model model) {
        model.addAttribute("itens", java.util.Collections.emptyList());
        return "modelo/modelo-list";
    }

    @GetMapping("/modelos/novo")
    public String novo(Model model) {
        model.addAttribute("modelo", new Object());
        return "modelo/modelo-form";
    }

    @GetMapping("/modelos/detalhe")
    public String detalhe(Model model) {
        model.addAttribute("modelo", new Object());
        return "modelo/modelo-view";
    }
}
