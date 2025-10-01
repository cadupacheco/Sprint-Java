package com.mottu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuarioController {

    @GetMapping("/usuarios")
    public String listar(Model model) {
        model.addAttribute("itens", java.util.Collections.emptyList());
        return "usuario/usuario-list";
    }

    @GetMapping("/usuarios/novo")
    public String novo(Model model) {
        model.addAttribute("usuario", new Object());
        return "usuario/usuario-form";
    }

    @GetMapping("/usuarios/detalhe")
    public String detalhe(Model model) {
        model.addAttribute("usuario", new Object());
        return "usuario/usuario-view";
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }
}
