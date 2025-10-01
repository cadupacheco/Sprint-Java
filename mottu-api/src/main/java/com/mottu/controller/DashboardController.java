package com.mottu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    // Descomentar quando tiver os services prontos
    // @Autowired
    // private MotoService motoService;
    // @Autowired
    // private PatioService patioService;
    // @Autowired
    // private ModeloService modeloService;
    // @Autowired
    // private AlertaService alertaService;

    @GetMapping
    public String dashboard(Model model) {
        try {
            // Buscar dados reais dos services
            // Long totalMotos = motoService.contarTodas();
            // Long motosDisponiveis = motoService.contarPorStatus("DISPONIVEL");
            // Long motosAlugadas = motoService.contarPorStatus("ALUGADA");
            // Long motosManutencao = motoService.contarPorStatus("MANUTENCAO");
            // Long totalPatios = patioService.contarTodos();
            // Long totalModelos = modeloService.contarTodos();
            // List<Alerta> alertasHoje = alertaService.buscarAlertasHoje();
            // List<Alerta> alertasRecentes = alertaService.buscarRecentes(5);
            // List<Patio> patiosDisponiveis = patioService.buscarDisponiveis();

            // Por enquanto, valores default até conectar services
            model.addAttribute("totalMotos", 0);
            model.addAttribute("motosDisponiveis", 0);
            model.addAttribute("motosAlugadas", 0);
            model.addAttribute("motosManutencao", 0);
            model.addAttribute("totalPatios", 0);
            model.addAttribute("totalModelos", 0);
            model.addAttribute("alertasHoje", Collections.emptyList());
            model.addAttribute("alertasRecentes", Collections.emptyList());
            model.addAttribute("patiosDisponiveis", Collections.emptyList());

            return "dashboard/index";
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao carregar dashboard: " + e.getMessage());
            return "error/custom-error";
        }
    }
}
