package com.GodevPortalDeTalentos.domain;

import jakarta.persistence.*;
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

    private String nome;
    private String descricao;
    private Integer ano;
    private boolean ativo = true;

    @ManyToMany
    @JoinTable(
            name = "turma_godev",
            joinColumns = @JoinColumn(name = "turma_id"),
            inverseJoinColumns = @JoinColumn(name = "godev_id")
    )
    private List<User> godevs;

}
