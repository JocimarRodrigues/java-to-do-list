package com.github.john.todo_api.dto;

import com.github.john.todo_api.enums.StatusTask;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record TaskUpdateDTO( @NotNull(message = "O campo 'id' é obrigatório.") Integer id,
                             @NotNull(message = "O campo 'status' é obrigatório.")
                             @Pattern(regexp = "FINISH|PENDING|", message = "Status deve ser um dos valores permitidos.") StatusTask status) {
}
