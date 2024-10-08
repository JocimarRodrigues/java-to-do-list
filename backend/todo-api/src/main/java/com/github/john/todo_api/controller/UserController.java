package com.github.john.todo_api.controller;

import com.github.john.todo_api.dto.UserDTO;
import com.github.john.todo_api.dto.UserDetailDTO;
import com.github.john.todo_api.mapper.UserMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.github.john.todo_api.entity.Users;
import com.github.john.todo_api.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> findAll() {
        List<Users> list = service.findAll();
        List<UserDTO> listDTO = list.stream().map(UserDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UserDetailDTO> findById(@PathVariable Long id) {
        UserDetailDTO userDto = service.findById(id);
        return ResponseEntity.ok().body(userDto);
    }


    @PostMapping(value = "/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Users> login(@RequestBody Users obj) {
        Users user = service.login(obj);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDTO> insert(@RequestBody @Valid UserDTO obj) {
        Users user = service.insert(obj);
//        obj = service.insert(obj);
//        UserDTO userDto = new UserDTO(obj);
        return ResponseEntity.ok().body(UserMapper.toDTO(user));
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public  ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody Users obj ) {
        obj = service.update(id, obj);
        UserDTO userDto = new UserDTO(obj);
        return ResponseEntity.ok().body(userDto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> delete (@PathVariable Long id) {
        service.delete(id);
        return  ResponseEntity.ok().body("Usuário com o id " + id + " foi exclúido com sucesso.");
    }


}
