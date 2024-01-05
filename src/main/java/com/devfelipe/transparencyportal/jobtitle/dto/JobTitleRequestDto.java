package com.devfelipe.transparencyportal.jobtitle.dto;

import jakarta.validation.constraints.NotBlank;

public record JobTitleRequestDto(
        @NotBlank(message = "The \"name\" field cannot be empty")
        String name
) {
}
