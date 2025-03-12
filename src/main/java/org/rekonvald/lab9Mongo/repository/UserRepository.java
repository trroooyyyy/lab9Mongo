package org.rekonvald.lab9Mongo.repository;

import org.rekonvald.lab9Mongo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    boolean existsByPhone(String phone);
    Optional<User> findByUsername(String username);

}