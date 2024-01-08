package com.devfelipe.transparencyportal.assignment.dto;

import com.devfelipe.transparencyportal.assignment.domain.model.Assignment;
import com.devfelipe.transparencyportal.common.dto.BaseMapper;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class AssignmentMapper implements BaseMapper<Assignment, AssignmentResponseDto, AssignmentRequestDto> {

    @Override
    public AssignmentResponseDto mapToResponseDto(Assignment assignment) {
        return new AssignmentResponseDto(
                assignment.getAssignmentId(),
                assignment.getName(),
                assignment.getCreatedAt(),
                assignment.getUpdatedAt()
        );
    }

    public Assignment mapToAssignment(AssignmentRequestDto assignmentRequestDto) {
        return Assignment.builder()
                .name(assignmentRequestDto.name())
                .createdAt(Instant.now())
                .build();
    }

    public AssignmentMinimalResponseDto mapToMinimalResponseDto(Assignment assignment) {
        return new AssignmentMinimalResponseDto(
                assignment.getAssignmentId(),
                assignment.getName()
        );
    }

}
