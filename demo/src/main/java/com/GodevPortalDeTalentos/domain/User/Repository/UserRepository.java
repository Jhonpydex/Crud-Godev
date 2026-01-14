package com.GodevPortalDeTalentos.domain.User.Repository;

import com.GodevPortalDeTalentos.domain.Enum.enums;
import com.GodevPortalDeTalentos.domain.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>{
    Optional<User> findByEmail(String email);
    List<User> findByAtivoTrue();
    List<User> findByRole(enums.Role role);
    Optional<User> findByEmailAndAtivoTrue(String email);
    //faz a verficação no banco se este e-mail já existe
}
