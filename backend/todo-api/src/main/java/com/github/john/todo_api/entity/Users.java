package com.github.john.todo_api.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity

@Table(name = "tb_users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 255)
    @NotEmpty(message = "Campo nome é obrigatório.")
    private String name;
    @NotEmpty
    private String email;
    @NotEmpty(message = "Campo senha é obrigatório.")
    private String password;

    @OneToMany( mappedBy = "user" , fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Tasks> tasks = new HashSet<>();

    public Users() {

    }
    public Users(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;

    }
}
