package com.GodevPortalDeTalentos.controller;

import com.GodevPortalDeTalentos.domain.User;
import com.GodevPortalDeTalentos.repositories.UserRepository;
import com.GodevPortalDeTalentos.service.UserService;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserRepository repo;//define como versão final o tipo UserRepository para ele ter uma referência fixa pra acessar o banco
    private final UserService service;


    public UserController(UserRepository repo, UserService service){
        this.repo = repo;
        this.service = service;
    }//(Injeção de Depêndencia) Construtor que recebe o repositório e guarda na variável repo, permitindo usar o banco dentro do controller.

    @GetMapping
    public List<User> listar(){
        return repo.findAll();
    }//Cria um endpoint GET /users que retorna todos os usuários salvos no banco.

    @PostMapping
    public String salvar(@Valid @RequestBody User user){
        return service.cadastrar(user);
    }//Cria um endpoint Post que cadastra novos usuários

    @PutMapping("/{id}")
    public String atualizar(@PathVariable Long id ,@Valid @RequestBody User user){
        return service.atualizar(id,user);
    }

    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Long id) {
        return service.deletar(id);
    }
}
