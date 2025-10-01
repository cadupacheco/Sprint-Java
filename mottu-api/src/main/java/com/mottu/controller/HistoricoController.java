package com.mottu.controller;

import java.util.Collections;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HistoricoController {

    @GetMapping("/historico")
    public String listar(Model model) {
        model.addAttribute("registros", Collections.emptyList());
        return "historico/historico-list";
    }
}
