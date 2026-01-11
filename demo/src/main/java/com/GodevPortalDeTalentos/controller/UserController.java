package com.GodevPortalDeTalentos.controller;

import com.GodevPortalDeTalentos.domain.User;
import com.GodevPortalDeTalentos.repositories.UserRepository;
import com.GodevPortalDeTalentos.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("users")
public class UserController {
    private final UserRepository repo;//define como versão final o tipo UserRepository para ele ter uma referência fixa pra acessar o banco
    private final UserService service;

    @GetMapping
    public List<User> listarTodos(){
        return service.listarTodos();
    }//Cria um endpoint GET /users que retorna todos os usuários salvos no banco.

    @GetMapping("/ativos")
    public List<User> listarAtivos(){
        return service.listarAtivos();
    }

    @PostMapping
    public String salvar(@Valid @RequestBody User user){
        return service.cadastrar(user);
    }//Cria um endpoint Post que cadastra novos usuários

    @PutMapping("/{id}")
    public String atualizar(@PathVariable Long id ,@Valid @RequestBody User user){
        return service.atualizar(id,user);
    }

    @DeleteMapping("/{id}")
    public String inativar(@PathVariable Long id) {
        return service.inativar(id);
    }
}
