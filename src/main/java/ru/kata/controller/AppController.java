package ru.kata.controller;

import java.util.List;

import ru.kata.UserDetailsServiceImpl;
import ru.kata.UserService;
import ru.kata.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.repository.UserRepository;

@Controller
public class AppController {
    private final UserService userService;

    public AppController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String viewUser(Model model) {
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

//    @RequestMapping(value = "/update", method = RequestMethod.POST)
//    public String updateUser(@ModelAttribute("user") User user, @ModelAttribute("id") Long id) {
//        user = userService.get(id);
//        userService.save(user);
//
//        return "redirect:/";
//    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user) {
        userService.save(user);

        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditUserForm(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_user");

        User user = userService.get(id);
        mav.addObject("user", user);

        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") Long id) {
        userService.delete(id);

        return "redirect:/";
    }

//    @RequestMapping("/user-update/{id}")
//    public String updateUserForm(@PathVariable("id") Long id, Model model){
//        User user = userService.get(id);
//        model.addAttribute("user", user);
//        return "edit_user";
//    }

    @RequestMapping("/user-update")
    public String updateUser(User user){
        userService.save(user);
        return "redirect:/";
    }
}
