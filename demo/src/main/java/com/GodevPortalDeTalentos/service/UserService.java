package com.GodevPortalDeTalentos.service;

import com.GodevPortalDeTalentos.domain.User;
import com.GodevPortalDeTalentos.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    @Autowired
    private BCryptPasswordEncoder encoder;

    public String cadastrar(User user) {
        if (repo.findByEmail(user.getEmail()).isPresent()) {
            return "Usuário já cadastrado!";
        } else {
            // Criptografa a senha antes de salvar
            user.setSenha(encoder.encode(user.getSenha()));
            repo.save(user);
            return "Usuário cadastrado com sucesso!";
        }
    }

    public List<User> listarTodos(){
      return repo.findAll();
    }

    public List<User> listarAtivos(){
        return repo.findByAtivoTrue();
    }


    public String atualizar(Long id, User novoUser) {
        User existente = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário com id " + id + " não encontrado"));

        Optional<User> outro = repo.findByEmail(novoUser.getEmail());
        if (outro.isPresent() && !outro.get().getId().equals(id)) {
            throw new IllegalArgumentException("Email já está em uso por outro usuário");
        }

        existente.setNome(novoUser.getNome());
        existente.setEmail(novoUser.getEmail());

        if (novoUser.getRole() != null) {
            existente.setRole(novoUser.getRole());
        }

        if (novoUser.getSenha() != null && !novoUser.getSenha().isBlank()) {
            existente.setSenha(encoder.encode(novoUser.getSenha()));
        }

        repo.save(existente);
        return "Usuário Atualizado com Sucesso!";
    }



    public String inativar(Long id) {
        return repo.findById(id)
                .map(user -> {
                    user.setAtivo(false);
                    repo.save(user);
                    return "Usuário inativado com sucesso!";
                }).orElse("Usuário não encontrado!");
    }

}

