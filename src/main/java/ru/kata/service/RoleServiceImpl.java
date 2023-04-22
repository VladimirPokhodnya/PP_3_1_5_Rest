package ru.kata.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.model.Role;
import ru.kata.model.User;
import ru.kata.repository.RoleJpaRepository;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleJpaRepository repo;

    public RoleServiceImpl(RoleJpaRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Role> listAll() {
        return repo.listRole();
    }

    @Override
    public Role get(Long id) {
        return repo.findById(id).get();
    }

    @Transactional
    @Override
    public void save(Role role) {
        repo.save(role);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

}
