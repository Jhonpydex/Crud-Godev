package com.GodevPortalDeTalentos.domain.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CreateUserDTO {
    @NotBlank
    private String nome;

    @NotBlank
    @Pattern(
            regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\\\.[A-Za-z]{2,}$",
            message = "Email inválido"
    )
    private String email;

    @NotBlank
    @Pattern( regexp = "^(?=.*[A-Za-z])(?=.*\\\\d)(?=.*[@$!%*#?&]).{8,}$",
        message = "Senha fraca" )
    private String senha;
}
