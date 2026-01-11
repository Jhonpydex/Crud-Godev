package com.GodevPortalDeTalentos.service;

import com.GodevPortalDeTalentos.domain.Turma;
import com.GodevPortalDeTalentos.repositories.TurmaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TurmaService {
    private final TurmaRepository repo;

    public List<Turma> listarTurmas(){
        return repo.findAll();
    }

    public List<Turma> listarAtivas(){
        return repo.findByAtivoTrue();
    }

    public Optional<Turma> buscarPorID(Long id){
        return repo.findById(id);
    }

    public Turma salvar(Turma turma){
        return repo.save(turma);
    }

    public String inativar(Long id) {
        return repo.findById(id)
                .map(turma -> {
                    turma.setAtivo(false);
                    repo.save(turma);
                    return "Turma inativada com sucesso!";
                }).orElse("Turma não encontrada!");
    }

}
