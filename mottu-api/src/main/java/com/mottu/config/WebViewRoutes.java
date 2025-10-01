package com.mottu.config;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebViewRoutes implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Mapeia rotas “páginas” para templates thymeleaf correspondentes
        registry.addViewController("/dashboard").setViewName("dashboard/index");
        registry.addViewController("/modelos").setViewName("modelos/lista");
        registry.addViewController("/motos").setViewName("motos/lista");
        registry.addViewController("/patios").setViewName("patios/lista");
        registry.addViewController("/usuarios").setViewName("usuarios/lista");
        registry.addViewController("/sensores").setViewName("sensores/lista");
        registry.addViewController("/historico").setViewName("historico/index");
        registry.addViewController("/alertas").setViewName("alertas/index");
        registry.addViewController("/login").setViewName("auth/login");
        registry.addViewController("/home").setViewName("home/index");
    }

    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }
}
