package ru.kata.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.model.Role;
import ru.kata.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getAllUsers();

    List<User> listAll();

    void save(User user);

    void saveUser(User user);

    void delete(Long id);

    public User get(Long id);

    User getById(Long id);

    void updateUser(User user);

    User getAuthUser();

    List<Role> getAllRoles();
}