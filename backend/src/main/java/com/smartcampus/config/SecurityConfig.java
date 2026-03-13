package com.smartcampus.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartcampus.model.dto.ApiResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> cors.configure(http))
            .csrf(csrf -> csrf.disable()) // Disabled for REST API
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/public/**", "/error", "/login/oauth2/**").permitAll()
                .anyRequest().authenticated()
            )
            .oauth2Login(org.springframework.security.config.Customizer.withDefaults())
            .exceptionHandling(ex -> ex
                .authenticationEntryPoint((request, response, authException) -> {
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    response.setContentType("application/json");
                    ApiResponse<Void> apiResponse = ApiResponse.error("Unauthorized: " + authException.getMessage());
                    response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
                })
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                    response.setContentType("application/json");
                    ApiResponse<Void> apiResponse = ApiResponse.error("Forbidden: Access Denied");
                    response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
                })
            );

        return http.build();
    }
}
