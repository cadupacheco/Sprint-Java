package com.mottu.config;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration
public class FlywayConfig {

    @Bean
    public Flyway flyway() {
        Flyway flyway = Flyway.configure()
                .schemas("YOUR_SCHEMA")                         // se múltiplos, use .schemas("schema1","schema2")
                .locations("classpath:db/migration")             // aceita varargs ou List.of(...)
                .baselineOnMigrate(true)
                .baselineVersion("0")
                .validateOnMigrate(false)
                .encoding(StandardCharsets.UTF_8)                // usa Charset, não String
                .load();
        flyway.migrate();
        return flyway;
    }
}
