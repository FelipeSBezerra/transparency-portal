package com.devfelipe.transparencyportal.employee.dto;

import com.devfelipe.transparencyportal.employee.domain.enums.EmploymentType;
import com.devfelipe.transparencyportal.employee.domain.model.Employee;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;
import java.time.LocalDate;

public record EmployeeResponseDto(
        Integer employeeId,
        String name,
        EmploymentType employmentType,
        LocalDate employmentStartDate,
        LocalDate employmentEndDate,
        Integer weeklyWorkHours,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
        Instant createdAt,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
        Instant updatedAt
) {}
