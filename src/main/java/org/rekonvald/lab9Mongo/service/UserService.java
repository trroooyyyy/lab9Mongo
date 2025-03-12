package org.rekonvald.lab9Mongo.service;

import lombok.RequiredArgsConstructor;
import org.rekonvald.lab9Mongo.entity.User;
import org.rekonvald.lab9Mongo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User with ID " + id + " not found"));
    }

    @Transactional(readOnly = true)
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new NoSuchElementException("User with username " + username + " not found"));
    }

    @Transactional
    public void registerUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        if (userRepository.existsByPhone(user.getPhone())) {
            throw new IllegalArgumentException("Phone already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public User updateUser(Long id, User newUser) {
        User existingUser = getUserById(id);

        if (userRepository.existsByUsername(newUser.getUsername()) && !existingUser.getUsername().equals(newUser.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        if (userRepository.existsByPhone(newUser.getPhone()) && !existingUser.getPhone().equals(newUser.getPhone())) {
            throw new IllegalArgumentException("Phone already exists");
        }

        existingUser.setUsername(newUser.getUsername());
        existingUser.setName(newUser.getName());
        existingUser.setSurname(newUser.getSurname());
        existingUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        existingUser.setPhone(newUser.getPhone());

        return userRepository.save(existingUser);
    }
}