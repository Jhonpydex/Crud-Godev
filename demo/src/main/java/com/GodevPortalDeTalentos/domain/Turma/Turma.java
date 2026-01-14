package com.GodevPortalDeTalentos.domain.Turma;

import com.GodevPortalDeTalentos.domain.Godev.GoDev;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

//Informações da Turma Respectiva
@Entity
@Table(name = "turmas")
@Getter
@Setter
@AllArgsConstructor
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome da turma é obrigatório!")
    private String nome;

    @NotBlank(message = "Descrição é obrigatória!")
    private String descricao;

    @Min(value = 2020, message = "Ano mínimo permitido é 2020")
    @Max(value = 2026, message = "Ano máximo permitido é 2026")
    private Integer ano;

    private boolean ativo = true;

    @ManyToMany
    @JoinTable(
            name = "turma_godev",
            joinColumns = @JoinColumn(name = "turma_id"),
            inverseJoinColumns = @JoinColumn(name = "godev_id")
    )
    @OneToMany(mappedBy = "turma", cascade = CascadeType.ALL)
    private List<GoDev> godevs;

}
