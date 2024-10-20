package com.github.john.todo_api.mapper;

import com.github.john.todo_api.dto.TaskDTO;
import com.github.john.todo_api.dto.UserDTO;
import com.github.john.todo_api.dto.UserDetailDTO;
import com.github.john.todo_api.entity.Tasks;
import com.github.john.todo_api.entity.Users;

import java.util.stream.Collectors;

public class UserMapper {
    public static Users toEntity(UserDTO dto) {
        Users entity = new Users();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        return entity;
    }

    public static UserDTO toDTO(Users entity) {
        UserDTO dto = new UserDTO();
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        return dto;
    }

    public static UserDetailDTO toUserDetailDTO(Users entity) {
        UserDetailDTO dto = new UserDetailDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setTasks(entity.getTasks().stream().map(TaskMapper::toDTO).collect(Collectors.toSet()));
        return dto;
    }
}
