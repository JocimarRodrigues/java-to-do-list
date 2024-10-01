package com.github.john.todo_api.controller;

import com.github.john.todo_api.model.Tasks;
import com.github.john.todo_api.model.Users;
import com.github.john.todo_api.service.TaskService;
import com.github.john.todo_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/tasks")
public class TaskController {
    @Autowired
    private TaskService service;

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Tasks> findById(@PathVariable Integer id) {
        Tasks obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Tasks> insert(@RequestBody @Valid Tasks obj) {
        obj = service.insert(obj);
        return ResponseEntity.ok().body(obj);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public  ResponseEntity<Tasks> update(@PathVariable Integer id, @RequestBody Tasks obj ) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> delete (@PathVariable Integer id) {
        service.delete(id);
        return  ResponseEntity.ok().body("Usuário com o id " + id + " foi exclúido com sucesso.");
    }
}
