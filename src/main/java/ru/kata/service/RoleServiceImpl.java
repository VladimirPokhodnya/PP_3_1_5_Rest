package ru.kata.service;

import org.springframework.stereotype.Service;
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

//    public void save(Role role) {
//        repo.save(role);
//    }
//
//    public Role get(Integer id) {
//        return repo.findById(id.longValue()).get();
//    }
//
//    public void delete(Integer id) {
//        repo.deleteById(id.longValue());
//    }

}
