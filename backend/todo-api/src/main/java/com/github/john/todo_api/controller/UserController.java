package com.github.john.todo_api.controller;

import com.github.john.todo_api.model.Users;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.github.john.todo_api.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Users> findById(@PathVariable Long id) {
        Users obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }


    @PostMapping(value = "/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Users> login(@RequestBody Users obj) {
        Users user = service.login(obj);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Users> insert(@RequestBody @Valid Users obj) {
        obj = service.insert(obj);
        return ResponseEntity.ok().body(obj);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public  ResponseEntity<Users> update(@PathVariable Long id, @RequestBody Users obj ) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> delete (@PathVariable Long id) {
        service.delete(id);
        return  ResponseEntity.ok().body("Usuário com o id " + id + " foi exclúido com sucesso.");
    }
}
