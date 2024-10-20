package com.github.john.todo_api.mapper;

import com.github.john.todo_api.dto.TaskDTO;
import com.github.john.todo_api.entity.Tasks;
import com.github.john.todo_api.entity.Users;

public class TaskMapper {
    public static Tasks toEntity(TaskDTO dto, Users user) {
        Tasks entity = new Tasks();
        entity.setName(dto.getName());
        entity.setStatus(dto.getStatus());
        entity.setUser(user);
        return entity;
    }

    public static TaskDTO toDTO(Tasks entity) {
        TaskDTO dto = new TaskDTO();
//        dto.setId(entity.getId());
        dto.setUsuarioId(entity.getUser().getId());
        dto.setName(entity.getName());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}