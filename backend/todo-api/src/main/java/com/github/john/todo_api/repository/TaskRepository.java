package com.github.john.todo_api.repository;

import com.github.john.todo_api.model.Tasks;
import com.github.john.todo_api.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Tasks, Integer> {

}
