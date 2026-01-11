package com.GodevPortalDeTalentos.controller;

import com.GodevPortalDeTalentos.configuration.Token.LoginRequest;
import com.GodevPortalDeTalentos.configuration.Token.TokenResponse;
import com.GodevPortalDeTalentos.domain.User;
import com.GodevPortalDeTalentos.repositories.UserRepository;
import com.GodevPortalDeTalentos.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository repo; // acesso ao banco de usuários

    @Autowired
    private BCryptPasswordEncoder encoder; // usado para validar senha

    @Autowired
    private JwtService jwtService; // gera e valida tokens JWT

    // Endpoint de login
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> autenticar(@RequestBody LoginRequest login) {
        System.out.println("Tentando login com: " + login.getEmail());

        // busca usuário pelo email
        User user = repo.findByEmail(login.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Email não encontrado"));

        // valida senha informada contra a senha criptografada
        if (!encoder.matches(login.getSenha(), user.getSenha())) {
            throw new BadCredentialsException("Senha inválida");
        }

        // gera token JWT com email como subject
        String token = jwtService.gerarToken(user.getEmail());
        return ResponseEntity.ok(new TokenResponse(token));
    }
}

