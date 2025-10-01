package com.mottu.controller;

import java.util.Collections;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AlertaController {

    @GetMapping("/alertas")
    public String listar(Model model) {
        model.addAttribute("alertas", Collections.emptyList());
        return "alerta/alerta-list";
    }
}
