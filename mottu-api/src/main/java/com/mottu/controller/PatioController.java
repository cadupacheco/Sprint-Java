package com.mottu.controller;

import java.util.Collections;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PatioController {

    @GetMapping("/patios")
    public String listar(Model model) {
        model.addAttribute("patios", Collections.emptyList());
        return "patio/patio-list";
    }

    @GetMapping("/patios/novo")
    public String novo(Model model) {
        model.addAttribute("patio", new Object());
        return "patio/patio-form";
    }

    @GetMapping("/patios/{id}")
    public String detalhe(@PathVariable Long id, Model model) {
        model.addAttribute("patio", new Object());
        return "patio/patio-view";
    }
}
