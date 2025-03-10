package org.rekonvald.lab4.repository;

import org.rekonvald.lab4.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}