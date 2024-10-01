package com.github.john.todo_api.model;

import com.github.john.todo_api.enums.StatusTask;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_tasks")
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "tb_user_id")
    private Users user;
    @NotEmpty(message = "Campo nome é obrigatório.")
    private String name;
    private StatusTask status;

}
