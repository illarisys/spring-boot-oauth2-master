package com.rd.repository;

import com.rd.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<Usuario, String> {

    @Query("SELECT u FROM Usuario u WHERE LOWER(u.username) = LOWER(:username)")
    Usuario findByUsernameCaseInsensitive(@Param("username") String username);

    @Query
    Usuario findByEmail(String email);

    @Query
    Usuario findByEmailAndActivationKey(String email, String activationKey);

    @Query
    Usuario findByEmailAndResetPasswordKey(String email, String resetPasswordKey);

}