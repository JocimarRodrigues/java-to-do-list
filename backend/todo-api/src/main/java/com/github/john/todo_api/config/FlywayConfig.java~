package com.github.john.todo_api.config;

import org.flywaydb.core.Flyway;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfig {

    @Bean
    CommandLineRunner cleanMigrateDatabase(Flyway flyway) {
        return args -> {
            flyway.clean();     // Limpa o banco de dados
            flyway.migrate();   // Reaplica todas as migrations
        };
    }
}