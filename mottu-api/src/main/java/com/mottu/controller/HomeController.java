package com.mottu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HomeController {

    // Redireciona a raiz para a home (ou para /login se preferir)
    @GetMapping("/")
    public RedirectView root() {
        return new RedirectView("/home");
    }

    // Página inicial com links rápidos
    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("titulo", "Mottu - Início");
        return "home/index";
    }

    // Fallback explícito para evitar Whitelabel Error em /error
    @GetMapping("/error")
    public String error(Model model) {
        model.addAttribute("titulo", "Página não encontrada");
        model.addAttribute("mensagem", "O recurso solicitado não foi localizado ou está indisponível.");
        return "error/custom-error";
    }
}
