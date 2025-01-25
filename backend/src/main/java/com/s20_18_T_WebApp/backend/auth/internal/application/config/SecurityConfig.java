package com.s20_18_T_WebApp.backend.auth.internal.application.config;

import com.s20_18_T_WebApp.backend.auth.internal.application.service.CorsConfigurationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {
    private final CorsConfigurationService corsConfigurationService;

    /**
     * Configures the security filter chain for the API.
     *
     * <p>This configuration disables CSRF protection as it is not needed for stateless APIs,
     * configures CORS settings using the {@link CorsConfigurationService}, defines authorization
     * rules for HTTP requests, sets session management to stateless since the API is token-based,
     * and allows unauthenticated access to certain paths.
     *
     * @param http the HttpSecurity to modify
     * @return the configured SecurityFilterChain
     * @throws Exception if an error occurs
     */
    @Bean
    public SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {

        http
                // Disable CSRF protection as it is not needed for stateless APIs
                .csrf(AbstractHttpConfigurer::disable)
                // Configure CORS settings using the CorsConfigurationService
                .cors(corsConfigurationService::configure)
                // Define authorization rules for HTTP requests
                .authorizeHttpRequests(authz -> authz
                        // Allow unauthenticated access to all GET requests
                        .requestMatchers(HttpMethod.GET, "/**").permitAll()
                        // Allow unauthenticated access to all POST requests
                        .requestMatchers(HttpMethod.POST, "/**").permitAll()
                        // Allow unauthenticated access to all PUT requests
                        .requestMatchers(HttpMethod.PUT, "/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/**").permitAll()
                        // Allow unauthenticated access to /docs/** paths
                        .requestMatchers("/docs/**").permitAll()
                        // Require authentication for any other requests
                        .anyRequest().authenticated()
                )
                // Set session management to stateless since the API is token-based
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        // Build and return the configured SecurityFilterChain
        return http.build();
    }
}
