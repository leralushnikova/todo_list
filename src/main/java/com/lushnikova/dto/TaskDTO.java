package com.lushnikova.dto;

import com.lushnikova.model.enums.Status;

public record TaskDTO(Long id, String description, Status status) {
}
