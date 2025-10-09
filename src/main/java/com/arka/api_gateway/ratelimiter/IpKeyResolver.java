package com.arka.api_gateway.ratelimiter;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Primary
@Component("ipKeyResolver")
public class IpKeyResolver implements KeyResolver {

    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        String forwardedFor = exchange.getRequest()
                .getHeaders()
                .getFirst("X-Forwarded-For");

        if (forwardedFor != null && !forwardedFor.isEmpty()) {
            String ip = forwardedFor.split(",")[0].trim();
            return Mono.just(ip);
        }

        return Mono.justOrEmpty(exchange.getRequest()
                        .getRemoteAddress())
                .map(addr -> addr.getAddress().getHostAddress());
    }
}
