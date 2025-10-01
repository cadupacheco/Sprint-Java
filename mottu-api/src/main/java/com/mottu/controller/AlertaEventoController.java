package com.mottu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AlertaController {

    @GetMapping("/alertas")
    public String listar(Model model) {
        model.addAttribute("itens", java.util.Collections.emptyList());
        return "alerta/alerta-list";
    }
}
