package com.lushnikova.service;

import com.lushnikova.dto.PageDTO;
import com.lushnikova.dto.TaskDTO;
import com.lushnikova.model.command.TaskCommand;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TaskService {

    Optional<TaskDTO> findById(Long id);

    PageDTO<TaskDTO> findAll(Pageable pageable);

    TaskDTO save(TaskCommand taskCommand);

    Optional<TaskDTO> update(Long id, TaskCommand taskCommand);

    void delete(Long id);
}