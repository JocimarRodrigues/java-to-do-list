package com.github.john.todo_api.dto;

import com.github.john.todo_api.mapper.TaskMapper;
import com.github.john.todo_api.entity.Users;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class UserDetailDTO {
    private Long id;
    private String name;
    private String email;
    private Set<TaskDTO> tasks;

    public UserDetailDTO(Users obj) {
        id = obj.getId();
        name = obj.getName();
        email = obj.getEmail();
        tasks = obj.getTasks().stream().map(TaskMapper::toDTO).collect(Collectors.toSet());
    }

}
