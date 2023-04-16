package ru.kata.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.kata.model.Role;

import java.util.List;

public interface RoleRepository  extends CrudRepository<Role, Integer> {
    @Query("SELECT r FROM Role r WHERE r.name = :name")
    Role findByName(@Param("name") String name);
    @Query("From Role")
    public List<Role> listRole();
}
