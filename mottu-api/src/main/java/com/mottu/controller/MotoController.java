package com.mottu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MotoController {

    @GetMapping("/motos")
    public String listar(Model model) {
        model.addAttribute("itens", java.util.Collections.emptyList());
        return "moto/list";
    }

    @GetMapping("/motos/novo")
    public String novo(Model model) {
        model.addAttribute("moto", new Object());
        return "moto/form";
    }

    @GetMapping("/motos/detalhe")
    public String detalhe(Model model) {
        model.addAttribute("moto", new Object());
        return "moto/view";
    }
}
