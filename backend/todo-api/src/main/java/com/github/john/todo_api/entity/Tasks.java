package com.github.john.todo_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.john.todo_api.enums.StatusTask;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


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
    @Enumerated(EnumType.STRING)
    private StatusTask status;



    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;


    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDate.now();
    }


}
