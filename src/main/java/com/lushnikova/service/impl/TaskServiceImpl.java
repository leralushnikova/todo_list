package com.lushnikova.service.impl;

import com.lushnikova.dto.PageDTO;
import com.lushnikova.dto.TaskDTO;
import com.lushnikova.mapper.TaskMapper;
import com.lushnikova.model.command.TaskCommand;
import com.lushnikova.repository.TaskRepository;
import com.lushnikova.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Transactional
    public Optional<TaskDTO> findById(Long id) {
        log.info("Finding person by id {}", id);
        var task = taskRepository.findById(id);
        if (task.isPresent()) {
            log.info("Found person with id {}", id);
            return task.map(taskMapper::toDTO);
        }
        log.info("Person with id {} not found", id);
        return Optional.empty();
    }

    @Override
    public PageDTO<TaskDTO> findAll(Pageable pageable) {
        var result = taskRepository.findAllBy(pageable);
        return new PageDTO<>(result, pageable);
    }

    @Transactional
    @Override
    public TaskDTO save(TaskCommand personCommand) {
        log.info("Trying to saving person {}", personCommand);
        var personToSave = taskMapper.toEntity(personCommand);
        var person = taskRepository.save(personToSave);
        log.info("Saved person {}", person);
        return taskMapper.toDTO(person);
    }

    @Transactional
    @Override
    public Optional<TaskDTO> update(Long id, TaskCommand personCommand) {
        var optionalPerson = taskRepository.findById(id);
        if (optionalPerson.isPresent()) {
            log.info("Updating person with id {}", id);
            var person = optionalPerson.get();
            taskMapper.update(person, personCommand);
            return Optional.of(taskMapper.toDTO(taskRepository.save(person)));
        }
        log.info("Person with id {} not found", id);
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting person with id {}", id);
        taskRepository.deleteById(id);
    }

}