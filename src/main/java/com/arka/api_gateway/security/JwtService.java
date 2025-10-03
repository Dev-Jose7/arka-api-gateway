package com.arka.api_gateway.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${security.jwt.key}")
    private String jwtKey;

    @Value("${security.jwt.user}")
    private String jwtUser;

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(jwtKey);
    }

    public DecodedJWT validateToken(String token) {
        try{
            JWTVerifier verifier = JWT.require(getAlgorithm())
                    .withIssuer(jwtUser)
                    .acceptLeeway(5)
                    .build();

            return verifier.verify(token);
        } catch (JWTVerificationException ex) {
            throw new JWTVerificationException("Invalid token: " + ex.getMessage(), ex);
        }
    }

    public List<String> extractAuthorities(DecodedJWT decodedJWT) {
        Claim authorities = decodedJWT.getClaim("authorities");
        if (authorities.isNull()) return List.of();
        return authorities.asList(String.class);
    }
}
