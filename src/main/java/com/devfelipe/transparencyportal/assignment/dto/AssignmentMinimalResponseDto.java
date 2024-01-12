package com.devfelipe.transparencyportal.assignment.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AssignmentMinimalResponseDto {

    private Integer assignmentId;
    private String assignmentName;
}
