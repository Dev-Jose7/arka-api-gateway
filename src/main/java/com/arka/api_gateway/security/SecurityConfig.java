package com.arka.api_gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.config.web.server.ServerHttpSecurity.CsrfSpec;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .csrf(CsrfSpec::disable)
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/api/v1/users/**").hasAnyRole("USER", "ADMIN")
                        .pathMatchers("/api/v1/orders/**").authenticated()
                        .pathMatchers("/api/v1/products/**").authenticated()
                        .pathMatchers("/api/v1/notifications/**").permitAll()
                        .pathMatchers("/api/v1/auth/**").permitAll()
                        .anyExchange().denyAll()
                );

        return http.build();
    }
}
