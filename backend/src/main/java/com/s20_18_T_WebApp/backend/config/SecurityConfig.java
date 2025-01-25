package com.s20_18_T_WebApp.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {
    /**
     * Configure CORS mapping for the application.
     *
     * @param registry the CorsRegistry to configure
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Add a mapping for all paths
        registry.addMapping("/**")
                // Allow requests from localhost:8080
                .allowedOrigins("http://localhost:8080")
                // Allow the specified HTTP methods
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                // Allow all headers
                .allowedHeaders("*")
                // Do not allow credentials
                .allowCredentials(false);
    }
}
