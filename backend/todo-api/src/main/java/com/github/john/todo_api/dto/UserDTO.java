package com.github.john.todo_api.dto;

import com.github.john.todo_api.entity.Users;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String password;


    public UserDTO(Users obj) {
        id = obj.getId();
        name = obj.getName();
        email = obj.getEmail();
    }

}
