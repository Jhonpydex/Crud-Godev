package com.GodevPortalDeTalentos.service;

import com.GodevPortalDeTalentos.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository; // acesso ao banco de usuários

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // carrega usuário pelo email e monta UserDetails para o Spring Security
        return userRepository.findByEmail(email)
                .map(user -> User.withUsername(user.getEmail())
                        .password(user.getSenha())
                        .roles("USER") // papel padrão
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));
    }
}


