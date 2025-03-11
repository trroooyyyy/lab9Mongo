package org.rekonvald.lab7.repository;

import org.rekonvald.lab7.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}