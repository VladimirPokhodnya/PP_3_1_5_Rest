package ru.kata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kata.model.User;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {
    @Query("select u from User u left join fetch u.roles where u.username=:username")
    User findByName(String username);
}
