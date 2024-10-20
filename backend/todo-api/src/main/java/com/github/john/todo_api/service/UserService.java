package com.github.john.todo_api.service;

import com.github.john.todo_api.dto.UserDTO;
import com.github.john.todo_api.dto.UserDetailDTO;
import com.github.john.todo_api.entity.Users;
import com.github.john.todo_api.exception.CustomGenericException;
import com.github.john.todo_api.exception.NotFoundException;

import com.github.john.todo_api.mapper.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.john.todo_api.repository.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<Users> findAll() {
        return repository.findAll();
    }

    public UserDetailDTO findById(Long id) {
        Optional<Users> obj = repository.findById(id);
        Users user = obj.orElseThrow(() -> new NotFoundException("Usuário não encontrado."));
        return UserMapper.toUserDetailDTO(user);
    }

    public UserDTO login(UserDTO obj) {
        Optional<Users> user = repository.findByEmailOrName(obj.getEmail(), obj.getName());
        Users foundUser = user.orElseThrow(() -> new NotFoundException("Usuário não encontrado."));

        if (!Objects.equals(obj.getPassword(), foundUser.getPassword())) {
            throw new CustomGenericException("Senha incorreta.");
        }
        return UserMapper.toDTO(foundUser);

    }

    public UserDTO insert(UserDTO obj) {
        try {
            Users user = UserMapper.toEntity(obj);
            repository.save(user);
            return UserMapper.toDTO(user);
        } catch (RuntimeException e) {
            throw new CustomGenericException(e.getMessage());
        }
    }

    public UserDTO update(Long id, UserDTO obj) {
        try {
            Users entity = repository.getReferenceById(id);
            updateData(entity, obj);
            Users user = repository.save(entity);
            return UserMapper.toDTO(user);
        } catch (EntityNotFoundException e) {
            throw new NotFoundException("Usuário não encontrado.");
        }
    }

    public void delete(Long id) {
        Optional<Users> obj = repository.findById(id);
        Users user = obj.orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
        repository.delete(user);
    }

    public void updateData(Users entity, UserDTO obj) {
        entity.setName(obj.getName());
        entity.setPassword(obj.getPassword());
        entity.setEmail(obj.getEmail());

    }

}
