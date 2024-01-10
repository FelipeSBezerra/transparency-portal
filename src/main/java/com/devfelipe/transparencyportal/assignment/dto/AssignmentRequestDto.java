package com.devfelipe.transparencyportal.assignment.dto;

import jakarta.validation.constraints.NotBlank;

public record AssignmentRequestDto(
        @NotBlank(message = "The name field cannot be empty")
        String name
) {
}
