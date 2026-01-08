package com.GodevPortalDeTalentos.domain;import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Criação de Usuário
@Entity//tipo inicializa pra criar tabela ou coluna algo assim
@Table(name = "users")//cria uma tabela no banco com o nome de User
@Getter//implementa getter pra adquirir as informações que o usuário der
@Setter//usar setter para mostrar ou fazer uso da informação obtida que o usuário deu
@NoArgsConstructor//Não necessáriamente precisa de constructor algo assim
@AllArgsConstructor//pra conseguir modelar o constructor inicial caso precise
public class User {
    @Id//inicializa a chave como ID
    @GeneratedValue(strategy = GenerationType.IDENTITY)//cria um id automaticamente
    // conforme vai sendo feito

    private Long id;

    @NotBlank(message = "Nome é obrigatório!")
    private String nome;

    @NotBlank(message = "Email é obrigatório!")@Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
            message = "Senha deve ter no mínimo 8 caracteres, incluindo letra, número e caractere especial"
    )
    private String senha;
    @Pattern(
            regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "Email inválido, deve conter @ e domínio válido"
    )
    private String email;


}


