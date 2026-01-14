package com.GodevPortalDeTalentos.domain.User.Service;

import com.GodevPortalDeTalentos.domain.Enum.enums;
import com.GodevPortalDeTalentos.domain.User.User;
import com.GodevPortalDeTalentos.domain.User.Repository.UserRepository;
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
    private BCryptPasswordEncoder encoder; // usado para criptografar senhas

    public String cadastrar(User user) {
        // evita duplicidade de email
        if (repo.findByEmail(user.getEmail()).isPresent()) {
            return "Usuário já cadastrado!";
        } else {
            // senha sempre criptografada antes de salvar
            user.setSenha(encoder.encode(user.getSenha()));
            repo.save(user);
            return "Usuário cadastrado com sucesso!";
        }
    }

    public List<User> listarTodos(){
        return repo.findAll(); // retorna todos, inclusive inativos
    }

    public List<User> listarAtivos(){
        return repo.findByAtivoTrue(); // apenas usuários ativos
    }

    public User buscarEmailAtivo(String email){
        return repo.findByEmailAndAtivoTrue(email)
                .orElseThrow(() -> new EntityNotFoundException("Usuário ativo não encontrado!"));
    }//Busca apenas emails ativos

    public List<User> buscarEnum(enums.Role role){
        return repo.findByRole(role);
    }//busca o tipo de perfil dentro do enum (Gestor ou Lider)

    public String atualizar(Long id, User novoUser) {
        User existente = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário com id " + id + " não encontrado"));

        // valida se email já pertence a outro usuário
        Optional<User> outro = repo.findByEmail(novoUser.getEmail());
        if (outro.isPresent() && !outro.get().getId().equals(id)) {
            throw new IllegalArgumentException("Email já está em uso por outro usuário");
        }

        existente.setNome(novoUser.getNome());
        existente.setEmail(novoUser.getEmail());

        if (novoUser.getRole() != null) {
            existente.setRole(novoUser.getRole()); // atualiza role se informado
        }

        if (novoUser.getSenha() != null && !novoUser.getSenha().isBlank()) {
            existente.setSenha(encoder.encode(novoUser.getSenha())); // recriptografa nova senha
        }

        repo.save(existente);
        return "Usuário Atualizado com Sucesso!";
    }

    public String inativar(Long id) {
        // delete lógico: apenas marca como inativo
        return repo.findById(id)
                .map(user -> {
                    user.setAtivo(false);
                    repo.save(user);
                    return "Usuário inativado com sucesso!";
                }).orElse("Usuário não encontrado!");
    }
}


