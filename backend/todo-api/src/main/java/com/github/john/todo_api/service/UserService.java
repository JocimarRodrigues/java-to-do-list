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

    public UserDTO updateProfile(UserDTO obj) {
        try {
            Optional<Users> user = repository.findByEmailOrName(obj.getEmail(), obj.getName()).or(() -> repository.findById(obj.getId()));
            Users foundUser = user.orElseThrow(() -> new NotFoundException("Usuário não encontrado."));
            if (!Objects.equals(obj.getPassword(), foundUser.getPassword())) {
                throw new CustomGenericException("Senha incorreta.");
            }

            updateData(foundUser, obj);
            Users userSaved = repository.save(foundUser);
            return UserMapper.toDTO(userSaved);
        } catch (RuntimeException e) {
            throw new CustomGenericException(e.getMessage());
        }
    }

    public void delete(Long id) {
        Optional<Users> obj = repository.findById(id);
        Users user = obj.orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
        repository.delete(user);
    }

    public void updateData(Users foundUser, UserDTO obj) {
        foundUser.setName(obj.getName());
        foundUser.setPassword(obj.getNewPassword() != null ? obj.getNewPassword() : foundUser.getPassword());
        foundUser.setEmail(obj.getEmail());
    }

}
