package com.devfelipe.transparencyportal.employee.dto;

import com.devfelipe.transparencyportal.employee.domain.enums.EmploymentType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EmployeeRequestDto(
        @NotBlank(message = "The \"name\" field cannot be empty")
        String name,

        @NotNull(message = "The \"employmentType\" field cannot be empty")
        EmploymentType employmentType,

        @NotNull(message = "The \"employmentStartDate\" field cannot be empty")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate employmentStartDate,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate employmentEndDate,

        @NotNull(message = "The \"weeklyWorkHours\" field cannot be empty")
        Integer weeklyWorkHours,

        @NotNull(message = "The \"jobTitleId\" field cannot be empty")
        Integer jobTitleId
) {}
