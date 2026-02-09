package com.GodevPortalDeTalentos.domain.DTO;

import lombok.Getter;

@Getter
public class UserResponseDTO {
    private Long id;
    private String nome;
    private String email;

    public UserResponseDTO(Long id, String nome, String email){
        this.id = id;
        this.nome=nome;
        this.email =email;
    }
}
