package com.GodevPortalDeTalentos.controller;


import com.GodevPortalDeTalentos.domain.Turma;
import com.GodevPortalDeTalentos.service.TurmaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    private final TurmaService turmaService;

    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    @GetMapping
    public List<Turma> listarTodas() {
        return turmaService.listarTurmas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> buscarPorId(@PathVariable Long id) {
        return turmaService.buscarPorID(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Turma criar(@RequestBody Turma turma) {
        return turmaService.salvar(turma);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turma> atualizar(@PathVariable Long id, @RequestBody Turma turma) {
        return turmaService.buscarPorID(id)
                .map(t -> {
                    t.setNome(turma.getNome());
                    t.setDescricao(turma.getDescricao());
                    return ResponseEntity.ok(turmaService.salvar(t));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        turmaService.inativar(id);
        return ResponseEntity.noContent().build();
    }
    /*Os pontos que faltam (atualizar todos os campos, diferenciar turma inexistente no DELETE,
    validar ativo no POST)*/
}
