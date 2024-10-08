package com.github.john.todo_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.john.todo_api.mapper.TaskMapper;
import com.github.john.todo_api.entity.Users;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    @JsonIgnore
    private List<TaskDTO> tasks;  // Adicionar a lista de tasks

    public UserDTO(Users obj) {
        id = obj.getId();
        name = obj.getName();
        email = obj.getEmail();
        tasks = obj.getTasks().stream().map(TaskMapper::toDTO).collect(Collectors.toList());
    }

}
