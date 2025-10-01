package com.mottu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/modelos")
public class ModeloController {

    // @Autowired
    // private ModeloService modeloService;

    @GetMapping
    public String listar(Model model) {
        try {
            // List<Modelo> modelos = modeloService.listarTodos();
            // model.addAttribute("modelos", modelos);

            // Placeholder até conectar service
            model.addAttribute("modelos", java.util.Collections.emptyList());
            return "modelo/modelo-list";
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao carregar modelos: " + e.getMessage());
            return "modelo/modelo-list";
        }
    }

    @GetMapping("/novo")
    public String novoForm(Model model) {
        // model.addAttribute("modelo", new Modelo());
        model.addAttribute("modelo", new Object()); // placeholder
        return "modelo/modelo-form";
    }

    @GetMapping("/{id}")
    public String detalhe(@PathVariable Long id, Model model) {
        try {
            // Optional<Modelo> modelo = modeloService.buscarPorId(id);
            // if (modelo.isPresent()) {
            //     model.addAttribute("modelo", modelo.get());
            //     return "modelo/modelo-view";
            // } else {
            //     model.addAttribute("erro", "Modelo não encontrado");
            //     return "error/custom-error";
            // }

            // Placeholder
            model.addAttribute("modelo", new Object());
            return "modelo/modelo-view";
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao buscar modelo: " + e.getMessage());
            return "error/custom-error";
        }
    }

    @GetMapping("/{id}/editar")
    public String editarForm(@PathVariable Long id, Model model) {
        try {
            // Optional<Modelo> modelo = modeloService.buscarPorId(id);
            // if (modelo.isPresent()) {
            //     model.addAttribute("modelo", modelo.get());
            //     return "modelo/modelo-form";
            // } else {
            //     model.addAttribute("erro", "Modelo não encontrado");
            //     return "error/custom-error";
            // }

            // Placeholder
            model.addAttribute("modelo", new Object());
            return "modelo/modelo-form";
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao buscar modelo: " + e.getMessage());
            return "error/custom-error";
        }
    }

    @PostMapping
    public String salvar(@ModelAttribute Object modelo, RedirectAttributes redirectAttributes) {
        try {
            // modeloService.salvar(modelo);
            redirectAttributes.addFlashAttribute("sucesso", "Modelo salvo com sucesso!");
            return "redirect:/modelos";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao salvar modelo: " + e.getMessage());
            return "redirect:/modelos/novo";
        }
    }

    @PostMapping("/{id}")
    public String atualizar(@PathVariable Long id, @ModelAttribute Object modelo, RedirectAttributes redirectAttributes) {
        try {
            // modeloService.atualizar(id, modelo);
            redirectAttributes.addFlashAttribute("sucesso", "Modelo atualizado com sucesso!");
            return "redirect:/modelos";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao atualizar modelo: " + e.getMessage());
            return "redirect:/modelos/" + id + "/editar";
        }
    }

    @PostMapping("/{id}/deletar")
    public String deletar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            // modeloService.deletar(id);
            redirectAttributes.addFlashAttribute("sucesso", "Modelo deletado com sucesso!");
            return "redirect:/modelos";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao deletar modelo: " + e.getMessage());
            return "redirect:/modelos";
        }
    }
}
