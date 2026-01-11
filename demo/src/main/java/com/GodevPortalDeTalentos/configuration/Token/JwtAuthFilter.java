package com.GodevPortalDeTalentos.configuration.Token;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.GodevPortalDeTalentos.service.JwtService;

import java.io.IOException;
import java.util.Enumeration;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String uri = request.getRequestURI();
        String method = request.getMethod();
        System.out.println("[JWT] Requisição interceptada: " + method + " " + uri);

        // 🔐 Ignorar login
        if (uri.equals("/auth") || uri.equals("/auth/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        Enumeration<String> headers = request.getHeaderNames();
        System.out.println("[JWT] Headers recebidos:");
        while (headers.hasMoreElements()) {
            String name = headers.nextElement();
            System.out.println("  " + name + ": " + request.getHeader(name));
        }

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null) {
            System.out.println("[JWT] Falha: Header Authorization está ausente");
        } else if (!authHeader.startsWith("Bearer ")) {
            System.out.println("[JWT] Falha: Header Authorization não começa com 'Bearer '");
        } else {
            String token = authHeader.substring(7);
            System.out.println("[JWT] Token recebido: " + token);

            try {
                String email = jwtService.extrairEmail(token);
                System.out.println("[JWT] Email extraído do token: " + email);

                if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                    System.out.println("[JWT] UserDetails carregado: " + userDetails.getUsername());

                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    System.out.println("[JWT] SecurityContext autenticado com sucesso");
                } else {
                    System.out.println("[JWT] Email nulo ou já autenticado");
                }
            } catch (Exception e) {
                System.out.println("[JWT] Erro ao extrair email ou autenticar: " + e.getMessage());
            }
        }
        filterChain.doFilter(request, response);
    }
}

