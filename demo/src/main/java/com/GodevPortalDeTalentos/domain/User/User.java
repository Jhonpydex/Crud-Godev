package com.GodevPortalDeTalentos.domain.User;

import com.GodevPortalDeTalentos.Validation.OnCreate;
import com.GodevPortalDeTalentos.Validation.OnUpdate;
import com.GodevPortalDeTalentos.domain.User.Enum.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Criação de Usuário
@Entity//tipo inicializa pra criar tabela ou coluna algo assim
@Table(name = "users")//cria uma tabela no banco com o nome de User
@Getter//implementa getter pra adquirir as informações que o usuário der
@Setter//usar setter para mostrar ou fazer uso da informação obtida que o usuário deu
@AllArgsConstructor//pra conseguir modelar o constructor inicial caso precise
@NoArgsConstructor
public class User {
    @Id//inicializa a chave como ID
    @GeneratedValue(strategy = GenerationType.IDENTITY)//cria um id automaticamente
    // conforme vai sendo feito

    private Long id;

    @NotBlank(message = "Nome é obrigatório!", groups = {OnCreate.class, OnUpdate.class})
    private String nome;

    @NotBlank(message = "Senha é obrigatório!", groups = OnCreate.class)@Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
            message = "Senha deve ter no mínimo 8 caracteres, incluindo letra, número e caractere especial",
            groups = OnCreate.class
    )
    @JsonProperty( access = JsonProperty.Access.WRITE_ONLY)
    private String senha;
    @NotBlank(message = "Email é obrigatório!",groups = {OnCreate.class, OnUpdate.class})@Pattern(
            regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "Email inválido, deve conter @ e domínio válido",
            groups = {OnCreate.class,OnUpdate.class}
    )
    @Column(unique = true) // garante unicidade do banco
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean ativo = true;//padrão ativo
}


