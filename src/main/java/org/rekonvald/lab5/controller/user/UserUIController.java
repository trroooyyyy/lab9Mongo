package org.rekonvald.lab5.controller.user;

import lombok.RequiredArgsConstructor;
import org.rekonvald.lab5.entity.User;
import org.rekonvald.lab5.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/ui/users")
@RequiredArgsConstructor
public class UserUIController {

    private final UserService userService;

    @GetMapping("/")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("user", new User());
        return "users_list";
    }

    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "edit_user";
    }

    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Long id, @Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<User> users = userService.getAllUsers();
            model.addAttribute("users", users);
            return "edit_user";
        }

        try {
            user.setId(id);
            userService.updateUser(id, user);
            return "redirect:/ui/users/";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", "Користувач з таким іменем уже існує");
            return "edit_user";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/ui/users/";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register_user";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register_user";
        }

        try {
            userService.registerUser(user);
            return "redirect:/ui/users/";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", "Користувач з таким іменем уже існує");
            return "register_user";
        }
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login_user";
    }

    @PostMapping("/login")
    public String loginUser(@Valid @ModelAttribute User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "login_user";
        }

        try {
            userService.login(user);
            return "redirect:/ui/users/";
        } catch (NoSuchElementException e) {
            model.addAttribute("error", "Невірний логін або пароль");
            return "login_user";
        }
    }
}
