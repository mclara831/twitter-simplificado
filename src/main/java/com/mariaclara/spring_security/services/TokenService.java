package com.mariaclara.spring_security.services;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.mariaclara.spring_security.entities.User;
import com.mariaclara.spring_security.entities.UserRole;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;
    
    public String generateToken(User user) {

        Algorithm algorithm = Algorithm.HMAC256(secret);
        var now = Instant.now();
        var expirationTime = now.plusSeconds(7200);

        var scope = user.getRoles()
                    .stream()
                    .map(UserRole::getName)
                    .collect(Collectors.joining(" "));

        var token = JWT.create()
                    .withIssuer("spring-security")
                    .withSubject(user.getUserId().toString())
                    .withClaim("scope", scope)
                    .withIssuedAt(now)
                    .withExpiresAt(expirationTime)
                    .sign(algorithm);
        return token;
    }

    

}
