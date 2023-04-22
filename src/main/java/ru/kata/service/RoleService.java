package ru.kata.service;

import ru.kata.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> listAll();

    public Role get(Long id);

    void save(Role role);

    public void delete(Long id);
}
