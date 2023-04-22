package ru.kata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.model.Role;
import ru.kata.model.User;
import ru.kata.repository.RoleJpaRepository;
import ru.kata.repository.UserJpaRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserJpaRepository userJpaRepository;
    private final RoleJpaRepository roleJpaRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserJpaRepository userJpaRepository, RoleJpaRepository roleJpaRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userJpaRepository = userJpaRepository;
        this.roleJpaRepository = roleJpaRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userJpaRepository.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), user.getAuthorities());
    }

    @Override
    public List<User> listAll() {
        return userJpaRepository.listUser();
    }

    @Transactional
    @Override
    public void save(User user) {
        userJpaRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userJpaRepository.findAll();
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userJpaRepository.save(user);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userJpaRepository.save(user);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        User user = userJpaRepository.findById(id).get();
        user.setRoles(null);
        userJpaRepository.save(user);
        userJpaRepository.deleteById(id);
    }

    @Override
    public User getById(Long id) {
        return userJpaRepository.getOne(id);
    }

    @Override
    public User getAuthUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userJpaRepository.findByName(auth.getName());
    }

    @Override
    public List<Role> getAllRoles() {
        return roleJpaRepository.findAll();
    }

    @Override
    public User get(Long id) {
        return userJpaRepository.findById(id).get();
    }

}
