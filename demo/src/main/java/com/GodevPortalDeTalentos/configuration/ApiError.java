package com.GodevPortalDeTalentos.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ApiError {
    private LocalDateTime timestamp; // momento em que o erro ocorreu
    private int status;              // código HTTP (400, 404, 500...)
    private String error;            // tipo de erro (Validation, Not Found, etc.)
    private List<String> message;    // mensagens detalhadas (ex.: campos inválidos)
    private String path;             // endpoint que gerou o erro
}

