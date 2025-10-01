package com.mottu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HistoricoController {

    @GetMapping("/historico")
    public String listar(Model model) {
        model.addAttribute("registros", java.util.Collections.emptyList());
        return "historico/historico-list";
    }
}
