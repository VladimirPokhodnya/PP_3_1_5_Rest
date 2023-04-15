package ru.kata.controller;

import java.util.List;

import ru.kata.UserService;
import ru.kata.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AppController {
    private final UserService userService;

    public AppController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/user")
    public String viewUser(Model model) {

        return "user";
    }

    @RequestMapping("/admin")
    public String viewAdmin(Model model) {
        List<User> listUsers = userService.listAll();
        model.addAttribute("listUsers", listUsers);

        return "admin";
    }

    @RequestMapping("/add")
    public String showNewUserForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);

        return "new_user";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user) {
        userService.save(user);

        return "redirect:/admin";
    }

    @RequestMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable(name = "id") Long id, Model model) {
        User user = userService.get(id);
        model.addAttribute("user", user);

        return "edit_user";
    }

    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") Long id) {
        userService.delete(id);

        return "redirect:/admin";
    }

    @RequestMapping("/user-update")
    public String updateUser(User user){
        userService.save(user);
        return "redirect:/admin";
    }
}
