package com.GodevPortalDeTalentos.configuration.Token;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TokenResponse {
    private String token; // JWT gerado e retornado ao cliente
}

