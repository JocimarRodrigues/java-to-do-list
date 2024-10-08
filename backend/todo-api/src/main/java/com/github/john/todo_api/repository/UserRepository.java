package com.github.john.todo_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.john.todo_api.entity.Users;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmailOrName(String email, String name);

}
