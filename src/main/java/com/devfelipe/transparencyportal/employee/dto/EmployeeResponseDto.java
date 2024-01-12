package com.devfelipe.transparencyportal.employee.dto;

import com.devfelipe.transparencyportal.assignment.dto.AssignmentMinimalResponseDto;
import com.devfelipe.transparencyportal.common.dto.BaseResponseDto;
import com.devfelipe.transparencyportal.employee.domain.enums.EmploymentType;
import com.devfelipe.transparencyportal.fundingsource.dto.FundingSourceMinimalResponseDto;
import com.devfelipe.transparencyportal.jobtitle.dto.JobTitleMinimalResponseDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class EmployeeResponseDto extends BaseResponseDto<Integer> {

        private Integer id;
        private String name;
        private EmploymentType employmentType;
        private LocalDate employmentStartDate;
        private LocalDate employmentEndDate;
        private Integer weeklyWorkHours;
        private JobTitleMinimalResponseDto jobTitle;
        private FundingSourceMinimalResponseDto fundingSource;
        private AssignmentMinimalResponseDto assignment;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
        private Instant createdAt;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
        private Instant updatedAt;

}
