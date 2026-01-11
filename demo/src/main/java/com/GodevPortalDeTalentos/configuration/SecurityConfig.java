package com.GodevPortalDeTalentos.configuration;

import com.GodevPortalDeTalentos.configuration.Token.JwtAuthFilter;
import com.GodevPortalDeTalentos.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthFilter jwtAuthFilter) throws Exception {
        http.csrf(csrf -> csrf.disable()) // desabilita CSRF (não necessário com JWT)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll() // login público
                        .requestMatchers(HttpMethod.POST, "/users").permitAll()      // cadastro público
                        .requestMatchers(HttpMethod.GET, "/users").permitAll()       // busca pública
                        .requestMatchers(HttpMethod.PUT, "/users/**").authenticated() // PUT exige token
                        .anyRequest().authenticated() // demais endpoints exigem autenticação
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // sem sessão, apenas JWT
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); // aplica filtro JWT

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        // carrega usuário pelo email e monta UserDetails para o Spring Security
        return username -> userRepository.findByEmail(username)
                .map(user -> User.withUsername(user.getEmail())
                        .password(user.getSenha())
                        .roles("USER") // pode trocar para "ADMIN" se necessário
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // encoder para senhas
    }
}


