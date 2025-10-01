package com.mottu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PatioController {

    @GetMapping("/patios")
    public String listar(Model model) {
        model.addAttribute("itens", java.util.Collections.emptyList());
        return "patios/lista";
    }

    @GetMapping("/patios/novo")
    public String novo(Model model) {
        model.addAttribute("patio", new Object());
        return "patios/form";
    }
}
