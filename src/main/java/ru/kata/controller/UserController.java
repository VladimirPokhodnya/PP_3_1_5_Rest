package ru.kata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.model.User;
import ru.kata.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/user")
    public String showInfoYourSelf(Model model) {
        User userAuth = userService.getAuthUser();
        model.addAttribute("user", userAuth);
        return "user";
    }

}
