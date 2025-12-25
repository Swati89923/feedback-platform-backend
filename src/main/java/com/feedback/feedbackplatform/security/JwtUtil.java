package com.feedback.feedbackplatform.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    private static final Key key =
            Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private static final long EXPIRATION = 1000 * 60 * 60; // 1 hour

    public static String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key)
                .compact();
    }

    // 🔥 THIS METHOD WAS MISSING
    public static Key getKey() {
        return key;
    }
}
