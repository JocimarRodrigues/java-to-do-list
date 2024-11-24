package com.github.john.todo_api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.john.todo_api.entity.Tasks;
import com.github.john.todo_api.enums.StatusTask;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserTasksDto(Long id, String name, String description, StatusTask status, @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") LocalDateTime createdAt) {

    public UserTasksDto(Tasks tasks) {
        this(tasks.getId(), tasks.getName(), tasks.getDescription(), tasks.getStatus(), tasks.getCreatedAt());
    }
}
