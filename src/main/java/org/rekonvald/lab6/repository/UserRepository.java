package org.rekonvald.lab6.repository;

import org.rekonvald.lab6.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}