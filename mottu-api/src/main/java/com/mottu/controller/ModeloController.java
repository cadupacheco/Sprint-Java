package com.mottu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ModeloController {

    @GetMapping("/modelos")
    public String listar(Model model) {
        model.addAttribute("itens", java.util.Collections.emptyList());
        return "modelos/lista";
    }

    @GetMapping("/modelos/novo")
    public String novo(Model model) {
        model.addAttribute("modelo", new Object());
        return "modelos/form";
    }
}
