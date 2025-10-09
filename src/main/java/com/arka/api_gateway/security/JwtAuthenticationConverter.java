package com.arka.api_gateway.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public class JwtAuthenticationConverter implements ServerAuthenticationConverter {

    private final JwtService jwtService;

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) return Mono.empty();

        try {
            String token = authHeader.substring(7);
            DecodedJWT jwt = jwtService.validateToken(token);
            String subject = jwt.getSubject();

            List<SimpleGrantedAuthority> authorities = jwtService.extractAuthorities(jwt).stream()
                    .map(SimpleGrantedAuthority::new)
                    .toList();

            Authentication auth = new JwtAuthenticationToken(subject, token, authorities);
            return Mono.just(auth);

        } catch (Exception ex) {
            return Mono.empty();
        }
    }
}
