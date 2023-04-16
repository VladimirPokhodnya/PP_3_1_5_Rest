package ru.kata.service;

import org.springframework.stereotype.Service;
import ru.kata.model.Role;
import ru.kata.repository.RoleRepository;

import java.util.List;
@Service
public class RoleService {
    private final RoleRepository repo;

    public RoleService(RoleRepository repo) {
        this.repo = repo;
    }
    public List<Role> listAll() {
        return repo.listRole();
    }

    public void save(Role role) {
        repo.save(role);
    }

    public Role get(Integer id) {
        return repo.findById(id).get();
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }

}
