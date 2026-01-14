package com.GodevPortalDeTalentos.domain.Godev.Service;

import com.GodevPortalDeTalentos.domain.Godev.GoDev;
import com.GodevPortalDeTalentos.domain.Turma.Turma;
import com.GodevPortalDeTalentos.domain.Godev.Repository.GodevRepository;
import com.GodevPortalDeTalentos.domain.Turma.Repository.TurmaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GodevService {

    private final GodevRepository repo;       // acesso ao banco de GoDevs
    private final TurmaRepository turmaRepo;  // usado para validar vínculo com Turma

    public List<GoDev> listarTodos() {
        return repo.findAll(); // retorna todos os GoDevs
    }

    public GoDev buscarPorId(Long id) {
        // lança exceção se não encontrar
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("GoDev com id " + id + " não encontrado"));
    }

    public GoDev buscarPorEmail(String email) {
        // lança exceção se não encontrar
        return (GoDev) repo.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("GoDev com email: " + email + " não encontrado"));
    }//busca email

    public List<GoDev> buscarTurmaPorId(Long turmaId){
        return repo.findByTurmaIdAndAtivoTrue(turmaId);
    }//busca a turma pelo id

    public GoDev salvar(GoDev godev) {
        // garante que a turma informada existe antes de salvar
        if (godev.getTurma() != null) {
            Turma turma = turmaRepo.findById(godev.getTurma().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Turma não encontrada"));
            godev.setTurma(turma);
        }
        return repo.save(godev);
    }

    public GoDev atualizar(Long id, GoDev novoGodev) {
        GoDev existente = buscarPorId(id);

        // atualiza dados básicos
        existente.setNome(novoGodev.getNome());
        existente.setEmail(novoGodev.getEmail());
        existente.setFoto(novoGodev.getFoto());
        existente.setDossie(novoGodev.getDossie());

        // atualiza vínculo com turma se informado
        if (novoGodev.getTurma() != null) {
            Turma turma = turmaRepo.findById(novoGodev.getTurma().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Turma não encontrada"));
            existente.setTurma(turma);
        }

        return repo.save(existente);
    }

    public String deletar(Long id) {
        // delete físico (remove do banco)
        GoDev existente = buscarPorId(id);
        repo.delete(existente);
        return "GoDev removido com sucesso!";
    }
}


