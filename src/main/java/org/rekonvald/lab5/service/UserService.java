package org.rekonvald.lab5.service;

import lombok.RequiredArgsConstructor;
import org.rekonvald.lab5.entity.User;
import org.rekonvald.lab5.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

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
        return userRepository.findAll().stream().filter(user -> user.getUsername().equals(username)).findFirst().orElseThrow(() -> new NoSuchElementException("User with username " + username + " not found"));
    }

    @Transactional
    public void registerUser(User user) {
        if (userRepository.findAll().stream().anyMatch(existingUser -> existingUser.getUsername().equals(user.getUsername()))) {
            throw new IllegalArgumentException("Username already exists");
        }

        userRepository.save(user);
    }

    @Transactional
    public User login(User user) {
        User existingUser = getUserByUsername(user.getUsername());
        if (!existingUser.getPassword().equals(user.getPassword())) {
            throw new NoSuchElementException("Invalid username or password");
        }

        return existingUser;
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public User updateUser(Long id, User newUser) {
        User existingUser = getUserById(id);

        if (userRepository.findAll().stream().anyMatch(user -> user.getUsername().equals(newUser.getUsername()) && !user.getId().equals(existingUser.getId()))) {
            throw new IllegalArgumentException("Username already exists");
        }

        existingUser.setUsername(newUser.getUsername());
        existingUser.setPassword(newUser.getPassword());

        return userRepository.save(existingUser);
    }
}
