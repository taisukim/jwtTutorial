package com.example.jwtTutorial.repository;

import com.example.jwtTutorial.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOwnWithAuthoritiesByUsername(String username);

}
