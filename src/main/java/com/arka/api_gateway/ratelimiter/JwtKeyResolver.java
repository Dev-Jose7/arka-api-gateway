package com.arka.api_gateway.ratelimiter;

import com.arka.api_gateway.security.JwtService;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component("userKeyResolver")
@RequiredArgsConstructor
@Slf4j
public class JwtKeyResolver implements KeyResolver {

    private final JwtService jwtService;

    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {

        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            try {
                DecodedJWT decodedJWT = jwtService.validateToken(token);
                String username = decodedJWT.getSubject();

                if (username != null && !username.isEmpty()) return Mono.just(username);

            } catch (Exception ex) {
                log.warn("Rate limiter: invalid JWT token.");
            }
        }

        return Mono.just("anonymous");
    }
}
