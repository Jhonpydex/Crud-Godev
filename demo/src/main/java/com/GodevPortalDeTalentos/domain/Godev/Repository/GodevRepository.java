package com.GodevPortalDeTalentos.domain.Godev.Repository;

import com.GodevPortalDeTalentos.domain.Godev.GoDev;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GodevRepository extends JpaRepository<GoDev,Long>{
    Optional<Object> findByEmail(String email);
    List<GoDev> findByTurmaIdAndAtivoTrue(Long turmaId);

}
