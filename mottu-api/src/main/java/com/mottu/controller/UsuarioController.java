package com.mottu.controller;

import java.util.Collections;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UsuarioController {

    @GetMapping("/usuarios")
    public String listar(Model model) {
        model.addAttribute("usuarios", Collections.emptyList());
        return "usuario/usuario-list";
    }

    @GetMapping("/usuarios/novo")
    public String novo(Model model) {
        model.addAttribute("usuario", new Object());
        return "usuario/usuario-form";
    }

    @GetMapping("/usuarios/{id}")
    public String detalhe(@PathVariable Long id, Model model) {
        model.addAttribute("usuario", new Object());
        return "usuario/usuario-view";
    }
}
