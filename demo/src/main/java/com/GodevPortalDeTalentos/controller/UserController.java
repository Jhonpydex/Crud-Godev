package com.GodevPortalDeTalentos.controller;

import com.GodevPortalDeTalentos.domain.User.User;
import com.GodevPortalDeTalentos.domain.User.Repository.UserRepository;
import com.GodevPortalDeTalentos.domain.User.Service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("users")
public class UserController {
    private final UserService service; // lógica de negócio

    @GetMapping
    public List<User> listarTodos(){
        return service.listarTodos(); // GET /users
    }

    @GetMapping("/ativos")
    public List<User> listarAtivos(){
        return service.listarAtivos(); // GET /users/ativos
    }

    @PostMapping
    public String salvar(@Valid @RequestBody User user){
        return service.cadastrar(user); // POST /users
    }

    @PutMapping("/{id}")
    public String atualizar(@PathVariable Long id ,@Valid @RequestBody User user){
        return service.atualizar(id,user); // PUT /users/{id}
    }

    @DeleteMapping("/{id}")
    public String inativar(@PathVariable Long id) {
        return service.inativar(id); // DELETE lógico /users/{id}
    }
}

