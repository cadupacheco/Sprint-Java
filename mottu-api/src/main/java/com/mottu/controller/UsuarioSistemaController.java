package com.mottu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuarioController {

    @GetMapping("/usuarios")
    public String listar(Model model) {
        model.addAttribute("itens", java.util.Collections.emptyList());
        return "usuarios/lista";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "auth/login";
    }
}
