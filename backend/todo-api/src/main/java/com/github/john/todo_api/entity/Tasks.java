package com.github.john.todo_api.entity;

import com.github.john.todo_api.enums.StatusTask;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_tasks")
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // Garantir que equals e hashCode usem apenas o campo id
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include  // Igualdade baseada apenas no id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
    @NotEmpty(message = "Campo nome é obrigatório.")
    private String name;
    private String description;
    private StatusTask status;

}
