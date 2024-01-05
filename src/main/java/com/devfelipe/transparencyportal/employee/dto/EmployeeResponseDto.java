package com.devfelipe.transparencyportal.employee.dto;

import com.devfelipe.transparencyportal.common.dto.BaseResponseDto;
import com.devfelipe.transparencyportal.employee.domain.enums.EmploymentType;
import com.devfelipe.transparencyportal.jobtitle.domain.model.JobTitle;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;
import java.time.LocalDate;

public record EmployeeResponseDto(
        Integer id,
        String name,
        EmploymentType employmentType,
        LocalDate employmentStartDate,
        LocalDate employmentEndDate,
        Integer weeklyWorkHours,

        JobTitle jobTitle,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
        Instant createdAt,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
        Instant updatedAt
) implements BaseResponseDto<Integer> {

        @Override
        public Integer getId() {
                return this.id;
        }
}
