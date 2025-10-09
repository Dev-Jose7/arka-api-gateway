package com.arka.api_gateway.ratelimiter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component("userKeyResolver")
@RequiredArgsConstructor
@Slf4j
public class JwtKeyResolver implements KeyResolver {

    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        return ReactiveSecurityContextHolder.getContext()
                .map(securityContext -> {
                    Authentication auth = securityContext.getAuthentication();
                    if (auth != null && auth.isAuthenticated() && auth.getName() != null) {
                        log.info("RateLimiter using user: {}", auth.getName());
                        return auth.getName();
                    }

                    return "anonymous";
                })
                .switchIfEmpty(Mono.just("anonymous"));
    }
}
