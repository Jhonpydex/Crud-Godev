package com.GodevPortalDeTalentos.repositories;

import com.GodevPortalDeTalentos.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>{
    Optional<User> findByEmail(String email);
    //faz a verficação no banco se este e-mail já existe
}
