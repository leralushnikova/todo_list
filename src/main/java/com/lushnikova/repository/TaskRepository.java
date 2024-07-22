package com.lushnikova.repository;

import com.lushnikova.dto.TaskDTO;
import com.lushnikova.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Page<TaskDTO> findAllBy(Pageable pageable);
}
