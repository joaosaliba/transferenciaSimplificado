package com.picpaysimplificado.repositories;

import com.picpaysimplificado.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findUserByDocument(String document);
    Optional<User> findUserById(Long id);

    Page<User> findAll(Pageable pageable);
}
