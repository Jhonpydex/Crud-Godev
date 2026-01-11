package com.GodevPortalDeTalentos.domain;

import jakarta.persistence.*;
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

    private String nome;
    private String email;
    private String foto;

    @Column(columnDefinition = "Text")
    private String dossie;

    @ManyToOne
    @JoinColumn(name = "turma_id")
    private Turma turma;
}
