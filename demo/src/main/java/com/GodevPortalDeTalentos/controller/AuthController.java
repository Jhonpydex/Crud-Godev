package com.GodevPortalDeTalentos.controller;

import com.GodevPortalDeTalentos.configuration.Token.LoginRequest;
import com.GodevPortalDeTalentos.configuration.Token.TokenResponse;
import com.GodevPortalDeTalentos.domain.User;
import com.GodevPortalDeTalentos.repositories.UserRepository;
import com.GodevPortalDeTalentos.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository repo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

//cria um novo hash ao atualizar a senha
    @PutMapping("/users/{id}")
    public ResponseEntity<?> atualizarUsuario(@PathVariable Long id, @RequestBody User dto) {
        Optional<User> optional = repo.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User user = optional.get();

        // Atualiza campos
        user.setEmail(dto.getEmail());
        user.setNome(dto.getNome());

        // Força hash na senha nova
        if (dto.getSenha() != null && !dto.getSenha().isBlank()) {
            String senhaCriptografada = passwordEncoder.encode(dto.getSenha());
            user.setSenha(senhaCriptografada);
            System.out.println("[DEBUG] Senha recebida: " + dto.getSenha());
            System.out.println("[DEBUG] Senha criptografada: " + senhaCriptografada);
        }

        repo.save(user);
        return ResponseEntity.ok(user);
    }




    //Autentica se o Usuário existe
    @PostMapping
    public ResponseEntity<TokenResponse> autenticar(@RequestBody LoginRequest login) {
        System.out.println("Tentando login com: " + login.getEmail());

        User user = repo.findByEmail(login.getEmail())
                .orElseThrow(() -> {
                    System.out.println("Email não encontrado");
                    return new UsernameNotFoundException("Email não encontrado");
                });

        System.out.println("Usuário encontrado: " + user.getEmail());
        System.out.println("Senha no banco: " + user.getSenha());
        System.out.println("Senha recebida: " + login.getSenha());

        if (!encoder.matches(login.getSenha(), user.getSenha())) {
            System.out.println("Senha inválida");
            throw new BadCredentialsException("Senha inválida");
        }

        String token = jwtService.gerarToken(user);
        System.out.println("Token gerado: " + token);

        return ResponseEntity.ok(new TokenResponse(token));
    }

}