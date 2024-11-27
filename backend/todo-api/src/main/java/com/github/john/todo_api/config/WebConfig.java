package com.github.john.todo_api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*") // Permite todas as origens
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS"); // Métodos HTTP permitidos
//                .allowedHeaders("*") // Permite todos os cabeçalhos
//                .allowCredentials(true); // Permite credenciais (se necessário)
    }

}
