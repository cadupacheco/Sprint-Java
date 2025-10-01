package com.mottu.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Component
public class EndpointLogger implements CommandLineRunner {

    @Autowired
    private RequestMappingHandlerMapping mapping;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=== Registered Request Mappings ===");
        mapping.getHandlerMethods().forEach((k, v) -> System.out.println(k + " => " + v));
        System.out.println("=== End Request Mappings ===");
    }
}
