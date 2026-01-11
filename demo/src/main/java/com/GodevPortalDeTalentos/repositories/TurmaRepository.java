package com.GodevPortalDeTalentos.repositories;

import com.GodevPortalDeTalentos.domain.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TurmaRepository extends JpaRepository <Turma,Long> {
    List<Turma> findByAtivoTrue();
}
