package com.github.john.todo_api.service;

import com.github.john.todo_api.exception.CustomGenericException;
import com.github.john.todo_api.exception.NotFoundException;
import com.github.john.todo_api.model.Users;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.john.todo_api.repository.UserRepository;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;


    public Users findById(Long id) {
        Optional<Users> obj = repository.findById(id);
        return obj.orElseThrow(() -> new NotFoundException("Usuário não encontrado."));
    }

    public Users login(Users obj) {
        Optional<Users> user = repository.findByEmailOrName(obj.getEmail(), obj.getName());

        Users foundUser = user.orElseThrow(() -> new NotFoundException("Usuário não encontrado."));

        // PLMDS NÃO VAI FAZER ISSO EM PROD PRA COMPARAR SNEHA, USA BYCRPT KKKKKKKK
        if (!Objects.equals(obj.getPassword(), foundUser.getPassword())) {
            throw new CustomGenericException("Senha incorreta.");
        }
        return foundUser;

    }

    public Users insert(Users obj) {
        try {
            return repository.save(obj);
        } catch (RuntimeException e) {
            throw new CustomGenericException(e.getMessage());
        }
    }

    public Users update(Long id, Users obj) {
        try {
            Users entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new NotFoundException("Usuário não encontrado.");
        }
    }

    public void delete(Long id) {
        Optional<Users> obj = repository.findById(id);
        Users user = obj.orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
        repository.delete(user);
    }

    public void updateData(Users entity, Users obj) {
        entity.setName(obj.getName());
        entity.setPassword(obj.getPassword());
    }

}
