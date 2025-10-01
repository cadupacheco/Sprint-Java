package com.mottu.controller;

import java.util.Collections;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SensorController {

    @GetMapping("/sensores")
    public String listar(Model model) {
        model.addAttribute("sensores", Collections.emptyList());
        return "sensor/sensor-list";
    }

    @GetMapping("/sensores/novo")
    public String novo(Model model) {
        model.addAttribute("sensor", new Object());
        return "sensor/sensor-form";
    }
}
