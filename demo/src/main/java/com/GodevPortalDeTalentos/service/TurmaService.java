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

    private final TurmaRepository repo; // acesso ao banco de dados

    public List<Turma> listarTurmas() {
        return repo.findAll(); // retorna todas as turmas
    }

    public List<Turma> listarAtivas() {
        return repo.findByAtivoTrue(); // apenas turmas ativas
    }

    public Optional<Turma> buscarPorID(Long id) {
        return repo.findById(id); // busca turma pelo ID
    }

    public Turma salvar(Turma turma) {
        // salva nova turma ou atualiza existente
        return repo.save(turma);
    }

    public String inativar(Long id) {
        // delete lógico: marca turma como inativa
        return repo.findById(id)
                .map(turma -> {
                    turma.setAtivo(false);
                    repo.save(turma);
                    return "Turma inativada com sucesso!";
                }).orElse("Turma não encontrada!");
    }
}

