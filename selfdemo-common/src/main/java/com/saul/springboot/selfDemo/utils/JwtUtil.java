package com.saul.springboot.selfDemo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JwtUtil {

    private final Key key;

    public JwtUtil(String secret) {

        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String createToken(Long userId, String userName, Long restaurantId) {

        JwtBuilder jwtBuilder = Jwts.builder()
                .claim("userId", userId)
                .claim("userName", userName);

        // restaurantId가 오는 경우만 만들어지도록
        if (restaurantId != null) {
            jwtBuilder.claim("restaurantId", restaurantId);
        }

        String token = jwtBuilder
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return token;
    }

    public Claims getClaims(String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody()
        ;

        return claims;
    }
}
