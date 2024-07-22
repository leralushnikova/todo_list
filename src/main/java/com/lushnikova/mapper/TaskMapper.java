package com.lushnikova.mapper;


import com.lushnikova.dto.TaskDTO;
import com.lushnikova.model.Task;
import com.lushnikova.model.command.TaskCommand;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskMapper {
    TaskDTO toDTO(Task task);

    Task toEntity(TaskCommand taskCommand);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget Task entity, TaskCommand updateEntity);
}
