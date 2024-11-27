package com.github.john.todo_api.service;

import com.github.john.todo_api.dto.TaskDTO;
import com.github.john.todo_api.dto.UserTasksDto;
import com.github.john.todo_api.entity.Tasks;
import com.github.john.todo_api.entity.Users;
import com.github.john.todo_api.enums.StatusTask;
import com.github.john.todo_api.exception.CustomGenericException;
import com.github.john.todo_api.exception.NotFoundException;
import com.github.john.todo_api.mapper.TaskMapper;
import com.github.john.todo_api.repository.TaskRepository;
import com.github.john.todo_api.repository.UserRepository;
import com.github.john.todo_api.specification.TaskSpecification;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository repository;
    @Autowired
    private UserRepository userRepository;




    public TaskDTO findById(Integer id) {
        Tasks obj = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tarefa não encontrada."));

        return TaskMapper.toDTO(obj);
    }

    public List<Tasks> buscarTarefasComFiltro(String name, String description, String status) {
        return repository.findAll(TaskSpecification.comFiltros(name, description, status));
    }

    public List<UserTasksDto> getUserTasks(Long id, StatusTask status) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
        List <Tasks> tasks;
        if (status != null) {
            tasks = repository.findByUserAndStatus(user, status);
        } else {
            tasks = repository.findByUser(user);
        }
        return tasks.stream().map(UserTasksDto::new).toList();
    }

    public Users findByUser(String email, String name) {
        Optional<Users> optional;
        try {
            optional = userRepository.findByEmailOrName(email, name);
        } catch (RuntimeException e) {
            throw new CustomGenericException(e.getMessage());
        }
        return optional.orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
    }

    public TaskDTO insert(TaskDTO obj) {
        try {
            System.out.println(obj.getUserId());
            Users user = userRepository.getReferenceById(obj.getUserId());
            Tasks entity = TaskMapper.toEntity(obj, user);
            repository.save(entity);
            return TaskMapper.toDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new NotFoundException("Tarefa não encontrada.");
        }
        catch (RuntimeException e) {
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

    public String finishTask(Integer id) {
        Tasks entity = repository.findById(id).orElseThrow(() -> new NotFoundException("Tarefa nao encontrada"));
        if(entity.getStatus().equals(StatusTask.FINISH)){
            throw new CustomGenericException("Tarefa ja finalizada.");
        }
        entity.setStatus(StatusTask.FINISH);
        repository.save(entity);
        return "Tarefa finalizada com sucesso";
    }

    public Tasks delete(Integer id) {
        Optional<Tasks> obj = repository.findById(id);
        return obj.orElseThrow(() -> new NotFoundException("Tarefa não encontrada"));
    }

    public void updateData(Tasks entity, Tasks obj) {
        entity.setName(obj.getName());
    }

}
