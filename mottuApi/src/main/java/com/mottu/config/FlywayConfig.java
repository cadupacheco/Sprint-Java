package com.mottu.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class FlywayConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    @Primary
    public Flyway flyway() {
        return Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:db/migration")
                .baselineOnMigrate(true)
                .baselineVersion("0")
                .validateOnMigrate(true)
                .cleanOnValidationError(false)
                .outOfOrder(false)
                .placeholderReplacement(true)
                .sqlMigrationPrefix("V")
                .sqlMigrationSeparator("__")
                .sqlMigrationSuffixes(".sql")
                .encoding("UTF-8")
                .table("flyway_schema_history")
                .load();
    }

    @Bean
    public FlywayProperties flywayProperties() {
        FlywayProperties properties = new FlywayProperties();
        properties.setLocations("classpath:db/migration");
        properties.setBaselineOnMigrate(true);
        properties.setValidateOnMigrate(true);
        properties.setCleanOnValidationError(false);
        properties.setOutOfOrder(false);
        properties.setTable("flyway_schema_history");
        properties.setEncoding("UTF-8");
        properties.setSqlMigrationPrefix("V");
        properties.setSqlMigrationSeparator("__");
        properties.setSqlMigrationSuffixes(new String[]{".sql"});
        return properties;
    }
}