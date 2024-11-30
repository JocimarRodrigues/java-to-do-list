package com.github.john.todo_api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.john.todo_api.entity.Tasks;
import com.github.john.todo_api.enums.StatusTask;

import java.time.LocalDateTime;

public record TaskByFiltersDTO(Long id, Long userId, String name, String description, StatusTask status, @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") LocalDateTime createdAt) {

    public TaskByFiltersDTO(Tasks tasks) {
        this(tasks.getId(), tasks.getUser().getId(), tasks.getName(), tasks.getDescription(), tasks.getStatus(), tasks.getCreatedAt());
    }

}
