package com.devfelipe.transparencyportal.assignment.dto;

import com.devfelipe.transparencyportal.common.dto.BaseMinimalResponseDto;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class AssignmentMinimalResponseDto extends BaseMinimalResponseDto {

    private Integer assignmentId;
    private String assignmentName;
}
