package com.github.john.todo_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.john.todo_api.enums.StatusTask;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskDTO {

    //private Integer id;
    @JsonIgnore
    private Long usuarioId;
    private String name;
    private String description;
    private StatusTask status;

}
