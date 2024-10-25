package com.jt.jwt_token_generate.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.security.Key;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Base64;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long jwtExpirationInMillis;

    private Key key;

    // Initialize the secret key and convert it into a Key object
    @PostConstruct
    public void init() {
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes); // Create an HMAC-SHA key from the secret
    }

    // Method to generate a token with claims, issued time, and expiration
    public String generateToken(String username) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);

        return Jwts.builder()
                .setClaims(claims) // Set custom claims
                .setSubject(username) // Set the subject (username)
                .setIssuedAt(new Date(System.currentTimeMillis())) // Set issued time
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMillis)) // Set expiration
                .signWith(key) // Signing the token with the key
                .compact(); // Compact the token to its final form (String)
    }
}
