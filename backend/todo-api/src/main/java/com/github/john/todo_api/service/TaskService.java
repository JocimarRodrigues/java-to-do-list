package com.github.john.todo_api.service;

import com.github.john.todo_api.exception.CustomGenericException;
import com.github.john.todo_api.exception.NotFoundException;
import com.github.john.todo_api.model.Tasks;
import com.github.john.todo_api.model.Users;
import com.github.john.todo_api.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository repository;


    public Tasks findById(Integer id) {
        Optional<Tasks> obj = repository.findById(id);
        return obj.orElseThrow(() -> new NotFoundException("Tarefa não encontrada."));
    }

    public Tasks insert(Tasks obj) {
        try {
            return repository.save(obj);
        } catch (RuntimeException e) {
            throw new CustomGenericException(e.getMessage());
        }
    }

    public Tasks update(Integer id, Tasks obj) {
        try {
            Tasks entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new NotFoundException("Tarefa não encontrada.");
        }
    }

    public Tasks delete(Integer id) {
        Optional<Tasks> obj = repository.findById(id);
        return obj.orElseThrow(() -> new NotFoundException("Tarefa não encontrada"));
    }

    public void updateData(Tasks entity, Tasks obj) {
        entity.setName(obj.getName());
    }

}
