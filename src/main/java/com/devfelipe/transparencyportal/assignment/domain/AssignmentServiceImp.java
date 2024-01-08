package com.devfelipe.transparencyportal.assignment.domain;

import com.devfelipe.transparencyportal.assignment.domain.model.Assignment;
import com.devfelipe.transparencyportal.assignment.dto.AssignmentMapper;
import com.devfelipe.transparencyportal.assignment.dto.AssignmentRequestDto;
import com.devfelipe.transparencyportal.assignment.dto.AssignmentResponseDto;
import com.devfelipe.transparencyportal.assignment.infra.AssignmentRepository;
import com.devfelipe.transparencyportal.common.domain.BaseServiceImp;
import com.devfelipe.transparencyportal.common.domain.exception.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AssignmentServiceImp extends BaseServiceImp<Assignment, Integer, AssignmentRequestDto, AssignmentResponseDto> implements AssignmentService {

    private final AssignmentMapper assignmentMapper;

    protected AssignmentServiceImp(AssignmentRepository assignmentRepository, AssignmentMapper assignmentMapper) {
        super(Assignment.class, assignmentRepository, assignmentMapper);
        this.assignmentMapper = assignmentMapper;
    }

    @Override
    protected Assignment _createEntityFromDto(AssignmentRequestDto assignmentRequestDto) {
        return assignmentMapper.mapToAssignment(assignmentRequestDto);
    }

    @Override
    protected Assignment _updateEntityFromDto(Integer entityId, AssignmentRequestDto assignmentRequestDto) {
        Assignment savedAssignment = this.findByIdReturnsEntity(entityId);
        _updateData(savedAssignment, assignmentRequestDto);
        return savedAssignment;
    }

    @Override
    protected void _checkDataIntegrityViolationForDeletion(Integer entityId) {
        if (!this.findByIdReturnsEntity(entityId).getEmployees().isEmpty()) {
            throw new DataIntegrityViolationException(
                    String.format("The assignment with id = %d has employees related to him and therefore cannot be deleted", entityId));
        }
    }

    private void _updateData(Assignment savedAssignment, AssignmentRequestDto assignmentRequestDto) {
        savedAssignment.setName(assignmentRequestDto.name());
        savedAssignment.setUpdatedAt(Instant.now());
    }
}
