package com.GodevPortalDeTalentos.service;

import com.GodevPortalDeTalentos.domain.GoDev;
import com.GodevPortalDeTalentos.domain.Turma;
import com.GodevPortalDeTalentos.repositories.GodevRepository;
import com.GodevPortalDeTalentos.repositories.TurmaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GodevService {

    private final GodevRepository repo;
    private final TurmaRepository turmaRepo;

    public List<GoDev> listarTodos() {
        return repo.findAll();
    }

    public GoDev buscarPorId(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("GoDev com id " + id + " não encontrado"));
    }

    public GoDev salvar(GoDev godev) {
        // garante que a turma existe antes de salvar
        if (godev.getTurma() != null) {
            Turma turma = turmaRepo.findById(godev.getTurma().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Turma não encontrada"));
            godev.setTurma(turma);
        }
        return repo.save(godev);
    }

    public GoDev atualizar(Long id, GoDev novoGodev) {
        GoDev existente = buscarPorId(id);

        existente.setNome(novoGodev.getNome());
        existente.setEmail(novoGodev.getEmail());
        existente.setFoto(novoGodev.getFoto());
        existente.setDossie(novoGodev.getDossie());

        if (novoGodev.getTurma() != null) {
            Turma turma = turmaRepo.findById(novoGodev.getTurma().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Turma não encontrada"));
            existente.setTurma(turma);
        }

        return repo.save(existente);
    }

    public String deletar(Long id) {
        GoDev existente = buscarPorId(id);
        repo.delete(existente);
        return "GoDev removido com sucesso!";
    }
}

