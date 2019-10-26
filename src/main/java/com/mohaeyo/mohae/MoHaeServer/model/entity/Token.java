package com.mohaeyo.mohae.MoHaeServer.model.entity;

import com.mohaeyo.mohae.MoHaeServer.exception.InvalidJwtAuthenticationException;
import io.jsonwebtoken.*;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Token {
    private String token;

    public String createToken(String id) {
        String key = Base64.getEncoder().encodeToString("key".getBytes());

        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");

        Map<String, Object> payloads = new HashMap<>();
        long expiredTime = 1000 * 60 * 60 * 24 * 7L;

        Date now = new Date();
        now.setTime(now.getTime() + expiredTime);
        payloads.put("exp", now);
        payloads.put("data", id);

        return Jwts.builder()
                .setHeader(headers)
                .setClaims(payloads)
                .signWith(SignatureAlgorithm.HS256, key.getBytes())
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey("key")
                    .parseClaimsJws(token);

            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new InvalidJwtAuthenticationException("Expired or invalid JWT token");
        }
    }

    public Token(User user) {
        token = this.createToken(user.getId());
    }

    public Map<Object, Object> getTokenResponse() {
        Map<Object, Object> model = new HashMap<>();
        model.put("token", this.token);

        return model;
    }
}
