package com.github.john.todo_api.controller;

import com.github.john.todo_api.dto.TaskDTO;
import com.github.john.todo_api.entity.Tasks;
import com.github.john.todo_api.entity.Users;
import com.github.john.todo_api.mapper.TaskMapper;
import com.github.john.todo_api.service.TaskService;
import com.github.john.todo_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tasks")
public class TaskController {
    @Autowired
    private TaskService service;

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TaskDTO> findById(@PathVariable Integer id) {
        TaskDTO obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public List<Tasks> buscarTarefas(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "status", required = false) String status) {

        return service.buscarTarefasComFiltro(name, description, status);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TaskDTO> insert(@RequestBody @Valid TaskDTO obj) {
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
