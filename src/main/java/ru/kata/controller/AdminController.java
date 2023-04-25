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
        model.addAttribute("listUsers", listUsers);

        return "admin";
    }

    @GetMapping("/add")
    public String showNewUserForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);

        return "new_user";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("user") User user, @RequestParam ArrayList<String> listRoleId) {
        Set<Role> userRole = user.getRoles();
        for (String roleId : listRoleId) {
            Role role = roleService.get(Long.parseLong(roleId));
            userRole.add(role);
        }
        user.setRoles(userRole);
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable(name = "id") Long id, Model model) {
        User user = userService.get(id);
        if (user == null) {
            return "redirect:/admin";
        }
        model.addAttribute("user", user);

        return "edit_user";
    }

    @PatchMapping("/edit/{id}")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam ArrayList<String> listRoleId) {
        Set<Role> userRole = user.getRoles();
        for (String roleId : listRoleId) {
            Role role = roleService.get(Long.parseLong(roleId));
            userRole.add(role);
        }
        user.setRoles(userRole);
        userService.save(user);
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
