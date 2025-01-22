package org.rekonvald.lab2.service;

import org.rekonvald.lab2.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

  private final List<User> users;

  public UserService() {
    users = new ArrayList<>();
    users.add(new User("viktoria", "12345"));
  }

  public User login(User user) {
    User userByUsername = getUserByUsername(user.getUsername());

    if(userByUsername.getPassword().equals(user.getPassword())) {
      return user;
    }

    return null;
  }

  public User registerUser(User user) {
    users.add(user);

    return user;
  }

  public User getUserByUsername(String username) {
    return users.stream()
            .filter(status -> status.getUsername().equals(username))
            .findFirst()
            .orElseThrow(NoSuchElementException::new);
  }

  public List<User> getAllUsers() {
    return users;
  }
}
