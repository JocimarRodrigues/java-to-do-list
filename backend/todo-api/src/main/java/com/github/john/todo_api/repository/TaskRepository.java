package com.github.john.todo_api.repository;

import com.github.john.todo_api.enums.StatusTask;
import org.springframework.data.jpa.repository.JpaRepository;

import com.github.john.todo_api.entity.Tasks;
import com.github.john.todo_api.entity.Users;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Tasks, Integer>, JpaSpecificationExecutor<Tasks> {
    List<Tasks> findByUser(Users user);
    List<Tasks> findByUserAndStatus(Users user, StatusTask status);
}
