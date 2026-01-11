package com.GodevPortalDeTalentos.controller;

import com.GodevPortalDeTalentos.domain.GoDev;
import com.GodevPortalDeTalentos.service.GodevService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/godevs")
public class GodevController {

    private final GodevService service;


    @GetMapping
    public List<GoDev> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GoDev> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.buscarPorId(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<GoDev> criar(@RequestBody GoDev godev) {
        return ResponseEntity.ok(service.salvar(godev));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GoDev> atualizar(@PathVariable Long id, @RequestBody GoDev godev) {
        try {
            return ResponseEntity.ok(service.atualizar(id, godev));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.deletar(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

