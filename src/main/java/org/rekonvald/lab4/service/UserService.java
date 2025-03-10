package org.rekonvald.lab4.service;

import org.rekonvald.lab4.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    private final List<User> users;
    private Long currentId;

    public UserService() {
        users = new ArrayList<>();
        currentId = 1L;
        users.add(new User(currentId++, "viktoria", "12345"));
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User getUserById(Long id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    public User getUserByUsername(String username) {
        return users.stream().filter(user -> user.getUsername().equals(username)).findFirst().orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    public void registerUser(User user) {
        if (users.stream().anyMatch(existingUser -> existingUser.getUsername().equals(user.getUsername()))) {
            throw new IllegalArgumentException("Username already exists");
        }

        user.setId(currentId++);
        users.add(user);
    }

    public User login(User user) {
        User existingUser = getUserByUsername(user.getUsername());
        if (existingUser.getPassword().equals(user.getPassword())) {
            return existingUser;
        }
        throw new NoSuchElementException("Invalid username or password");
    }

    public void deleteUser(Long id) {
        User user = getUserById(id);
        users.remove(user);
    }

    public User updateUser(User newUser) {
        User existingUser = getUserById(newUser.getId());

        if (users.stream()
                .anyMatch(user -> user.getUsername().equals(newUser.getUsername()) && !user.getId().equals(existingUser.getId()))) {
            throw new IllegalArgumentException("Username already exists");
        }

        existingUser.setUsername(newUser.getUsername());
        existingUser.setPassword(newUser.getPassword());

        return existingUser;
    }

}
