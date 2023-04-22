package ru.kata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.model.Role;

public interface RoleJpaRepository extends JpaRepository<Role, Long> {
}
