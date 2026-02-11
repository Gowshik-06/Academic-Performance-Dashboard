package com.apda.apda_backend.repository;

import com.apda.apda_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    // Custom query methods can be added later
    // Example:
    // Optional<User> findByUsername(String username);
}
