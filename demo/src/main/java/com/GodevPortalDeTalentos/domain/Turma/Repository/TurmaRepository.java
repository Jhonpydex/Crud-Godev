package com.GodevPortalDeTalentos.domain.Turma.Repository;

import com.GodevPortalDeTalentos.domain.Turma.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TurmaRepository extends JpaRepository <Turma,Long> {
    List<Turma> findByAtivoTrue();

    List<Turma> findByAno(Integer ano);

    Optional<Turma> findByNome(String nome);
}
