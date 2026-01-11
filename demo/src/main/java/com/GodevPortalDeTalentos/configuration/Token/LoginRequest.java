package com.GodevPortalDeTalentos.configuration.Token;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String email; // email usado para login
    private String senha; // senha em texto plano (será validada/criptografada)
}

