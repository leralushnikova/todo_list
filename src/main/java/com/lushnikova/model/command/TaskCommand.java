package com.lushnikova.model.command;

import com.lushnikova.model.enums.Status;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TaskCommand {
    private Long id;
    private String description;
    private Status status;
}
