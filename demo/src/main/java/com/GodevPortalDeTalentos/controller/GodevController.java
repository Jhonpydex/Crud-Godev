package com.GodevPortalDeTalentos.controller;

import com.GodevPortalDeTalentos.domain.Godev.GoDev;
import com.GodevPortalDeTalentos.domain.Godev.Service.GodevService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/godevs")
public class GodevController {

    private final GodevService service; // lógica de negócio

    @GetMapping
    public List<GoDev> listarTodos() {
        return service.listarTodos(); // GET /godevs
    }

    @GetMapping("/{id}")
    public ResponseEntity<GoDev> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.buscarPorId(id)); // busca por ID
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); // retorna 404 se não encontrar
        }
    }

    @PostMapping
    public ResponseEntity<GoDev> criar(@RequestBody GoDev godev) {
        return ResponseEntity.ok(service.salvar(godev)); // POST /godevs
    }

    @PutMapping("/{id}")
    public ResponseEntity<GoDev> atualizar(@PathVariable Long id, @RequestBody GoDev godev) {
        try {
            return ResponseEntity.ok(service.atualizar(id, godev)); // PUT /godevs/{id}
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.deletar(id)); // DELETE físico /godevs/{id}
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}


