package ru.kata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.model.Role;
import ru.kata.model.User;
import ru.kata.service.RoleService;
import ru.kata.service.UserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;


    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String viewAdmin(Model model) {
        List<User> listUsers = userService.listAll();
        User userAuth = userService.getAuthUser();
        List<Role> allRoles = userService.getAllRoles();

        model.addAttribute("listUsers", listUsers);
        model.addAttribute("user", userAuth);
        model.addAttribute("allRoles", allRoles);
        return "admin";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }


    @PutMapping("/edit/{id}")
    public String editUser(@ModelAttribute("usEdit") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }


    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") Long id) {
        User user = userService.get(id);
        user.setRoles(new HashSet<>());
        userService.delete(id);

        return "redirect:/admin";
    }

}
