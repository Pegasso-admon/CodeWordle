package com.crudzaso.codewordle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Main application class for CodeWordle - Educational Programming Word Game.
 * 
 * <p>This Spring Boot application provides a Wordle-style game focused on 
 * programming terminology with features for game management, theme selection, 
 * and real-time attempt processing.</p>
 * 
 * @author Crudzaso Team
 * @version 1.0.0
 * @since 2024
 */
@SpringBootApplication
public class CodeWordleApplication extends SpringBootServletInitializer {

    /**
     * Main entry point for the Spring Boot application.
     * 
     * @param args command line arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(CodeWordleApplication.class, args);
    }

    /**
     * Configures the application for WAR deployment.
     * 
     * @param application the Spring application builder
     * @return the configured application builder
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CodeWordleApplication.class);
    }
}