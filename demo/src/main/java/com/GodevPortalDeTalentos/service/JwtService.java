package com.GodevPortalDeTalentos.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    // chave secreta usada para assinar tokens (mínimo 32 chars)
    private static final String SECRET_KEY = "minhaChaveSecretaSuperSegura1234567890";

    public String extrairEmail(String token) {
        // extrai subject (email) do token JWT
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    private Key getSigningKey() {
        // gera chave HMAC a partir da SECRET_KEY
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String gerarToken(
            @NotBlank(message = "Email é obrigatório!")
            @Pattern(
                    regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
                    message = "Email inválido, deve conter @ e domínio válido"
            ) String email) {
        // cria token JWT válido por 1 dia
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // expira em 24h
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}


