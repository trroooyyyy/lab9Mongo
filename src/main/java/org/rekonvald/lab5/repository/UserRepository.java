package org.rekonvald.lab5.repository;

import org.rekonvald.lab5.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}