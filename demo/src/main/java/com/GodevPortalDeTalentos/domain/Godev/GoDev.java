package com.GodevPortalDeTalentos.domain.Godev;

import com.GodevPortalDeTalentos.domain.Turma.Turma;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Informações total do Dev para o perfil de Gestor
@Entity
@Table(name = "Godevs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GoDev {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório!")
    private String nome;

    @NotBlank(message = "Email é obrigatório!")
    @Pattern(
            regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "Email inválido, deve conter @ e domínio válido")
    @Column(unique = true)
    private String email;

    private String foto;

    @NotBlank(message = "Dossie é obrigatório!")
    @Column(columnDefinition = "Text")
    private String dossie;

    @ManyToOne
    @JoinColumn(name = "turma_id")
    private Turma turma;

    private boolean ativo = true; // delete lógico
}
