package org.rekonvald.lab9Mongo.repository;

import org.rekonvald.lab9Mongo.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    boolean existsByUsername(String username);
    boolean existsByPhone(String phone);
    Optional<User> findByUsername(String username);
}